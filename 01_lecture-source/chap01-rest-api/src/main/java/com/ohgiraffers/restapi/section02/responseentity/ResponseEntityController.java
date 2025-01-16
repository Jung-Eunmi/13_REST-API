package com.ohgiraffers.restapi.section02.responseentity;

import lombok.Getter;
import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entity")
public class ResponseEntityController {

    /* comment.
    *   ResponseEntity 란?
    *    - 결과 데이터와 HTTP 상태 코드를 직접 제어할 수 있는 클래스이다.
    *      내부에 HttpStatus, HttpHeaders, HttpBody 를 포함한다. */

    private List<UserDTO> users;

    /* 임시 DB 에서 조회해 온 값 설정 */
    public ResponseEntityController() {
        users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "너구리", LocalDate.now()));
        users.add(new UserDTO(2, "user02", "pass02", "푸바오", LocalDate.now()));
        users.add(new UserDTO(3, "user03", "pass03", "러바오", LocalDate.now()));

    }

    /* 전체 조회 */
    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {

        HttpHeaders headers = new HttpHeaders();
        // 응답 할 데이터의 양식 지정, 기본값 = json
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);



        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    /* 상세 조회 */
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        UserDTO foundUser = users.stream()
                                 .filter(user -> user.getNo() == userNo)
                                 .collect(Collectors.toList())
                                 .get(0);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);
                            // 상태코드 200 번 -> 정상 조회
        return ResponseEntity.ok().headers(headers).body(new ResponseMessage(200, "조회성공!", responseMap));
    }

    /* 등록 */
    /* comment.
    *   form 태그로 데이터 전달 받는 것과 javaScript 로 데이터 전달 받는 것이 다르다. */
    @PostMapping("/user/regist")
    public ResponseEntity<?> regist(@RequestBody UserDTO newUser) {

        System.out.println("Json 데이터 @RequestBody 로 잘 들어오는지 확인 : " + newUser);

        // List 유저번호
        int lastNo = users.get(users.size() - 1).getNo();
        newUser.setNo(lastNo + 1);
                            // 상태코드 201 번 -> 등록 관련(자원 생성 관련)
        return ResponseEntity.created(URI.create("/entity/users/" + users.get(users.size() -1).getNo())).build();
    }

    /* 수정 */
    @PutMapping("/users/{userNo}")
    public ResponseEntity<?> modifyUser(@PathVariable int userNo, @RequestBody UserDTO modifyInfo) {

        // 회원 정보 수정을 위한 유저 특정하기
        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).collect(Collectors.toList()).get(0);

        // id, pwd, name 수정하기
        foundUser.setId(modifyInfo.getId());
        foundUser.setPwd(modifyInfo.getPwd());
        foundUser.setName(modifyInfo.getName());

        return ResponseEntity.created(URI.create("/entity/users/" + userNo)).build();
    }

    /* 삭제 */
    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> removeUser(@PathVariable int userNo) {

        // 삭제하기 위한 유저 특정하기
        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).collect(Collectors.toList()).get(0);

        // 특정한 유저 객체 배열에서 삭제
        users.remove(foundUser);
                            // 상태코드 204 번 -> 자원 삭제 관련
        return ResponseEntity.noContent().build();
    }

}
