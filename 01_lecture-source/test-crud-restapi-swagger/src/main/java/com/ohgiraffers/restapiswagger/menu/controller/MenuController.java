package com.ohgiraffers.restapiswagger.menu.controller;

import com.ohgiraffers.restapiswagger.menu.common.ResponseMessage;
import com.ohgiraffers.restapiswagger.menu.model.dto.MenuDTO;
import com.ohgiraffers.restapiswagger.menu.model.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
@Tag(name = "Spring Boot Swagger 연동 (메뉴 관련 기능)")
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "메뉴 전체조회")
    @GetMapping("/list")
    public ResponseEntity<ResponseMessage> findAllMenu() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<MenuDTO> menuList = menuService.findAllMenu();

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("menuList", menuList);

        System.out.println("resMap = " + resMap);

        return ResponseEntity.ok().headers(headers).body(new ResponseMessage(200,"조회 성공!", resMap));
    }

    @Operation(summary = "메뉴 상세 조회", description = "메뉴 코드로 메뉴 상세조회하기",
            parameters = {@Parameter(name="menuCode", description = "사용자화면에서 넘어 온 메뉴코드(PK)")})
    @GetMapping("/{menuCode}")
    public ResponseEntity<ResponseMessage> findMenuByCode(@PathVariable int menuCode) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        MenuDTO foundMenu = menuService.findMenuByCode(menuCode);

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("menu", foundMenu);

        System.out.println("resMap = " + resMap);
        
        return ResponseEntity.ok().headers(headers).body(new ResponseMessage(200, "상세 조회 성공!", resMap));
    }

    @Operation(summary = "메뉴 등록")
    @PostMapping("/regist")
    public ResponseEntity<ResponseMessage> registMenu(@RequestBody MenuDTO newMenu) {

        menuService.registMenu(newMenu);

        return ResponseEntity.created(URI.create("/menu/list")).body(new ResponseMessage(201, "등록 성공!", null));
    }

    @Operation(summary = "메뉴 수정")
    @PutMapping("/modify")
    public ResponseEntity<ResponseMessage> updateMenu(@RequestBody MenuDTO modifyMenu) {

        menuService.modifyMenu(modifyMenu);

        return ResponseEntity.created(URI.create("/menu/list")).body(new ResponseMessage(201, "수정 성공!", null));
    }

    @Operation(summary = "메뉴 삭제")
    @DeleteMapping("/delete/{menuCode}")
    public ResponseEntity<?> deleteMenuByCode(@PathVariable int menuCode) {

        menuService.deleteMenuByCode(menuCode);

        return ResponseEntity.noContent().build();
    }
}
