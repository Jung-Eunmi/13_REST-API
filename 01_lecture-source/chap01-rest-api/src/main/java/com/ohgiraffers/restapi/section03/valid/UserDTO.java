package com.ohgiraffers.restapi.section03.valid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    /* @Valid - 유효성 검사 관련 어노테이션 */

    /* comment.
    *   @Null : null 만 허용한다.
    *   @NotNull : null 은 허용하지 않는다.("" 빈 문자열은 허용)
    *   @NotEmpty : null 과 "" 를 허용하지 않는다. 단 " " 는 가능 (스페이스바도 문자열에 속함)
    *   @NotBlank : null, "", " " 를 허용하지 않는다.
    *   @Email : 이메일 형식을 검사한다.
    *   @Patten(regexp = ) : 정규표현식을 설정한다.
    *       ex) regexp = "[a-z1-8]{6-10}" <- 영어 숫자 포함 6 ~ 10 이내
    *   @Size(min=, max=) : 길이 제한
    *   @Max, @Min(value=) : value 이상 이하 값
    *   @Positive : 값을 양수로 제한한다.
    *   @Future : 현재보다 미래 날짜
    *   @Past : 현재보다 과거 날짜 */

    private int no;

    @NotNull(message = "아이디를 반드시 입력해주세요.")
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    private String id;

    private String pwd;

    @NotNull(message = "이름을 반드시 입력해주세요.")
    @Size(min=2, message = "이름은 2글자 이상이어야 합니다.")
    private String name;

    @Past(message = "등장")
    private LocalDate enrollDate;

}