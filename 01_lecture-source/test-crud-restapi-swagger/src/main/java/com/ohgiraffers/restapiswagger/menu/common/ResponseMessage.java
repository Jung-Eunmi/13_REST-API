package com.ohgiraffers.restapiswagger.menu.common;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseMessage {

    private int code;
    private String message;
    private Map<String, Object> results;

}
