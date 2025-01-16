package com.ohgiraffers.restapiswagger.menu.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "메뉴 정보 관련 DTO")
public class MenuDTO {

    @Schema(description = "메뉴 번호(PK)")
    private int menuCode;
    @Schema(description = "메뉴 이름")
    private String menuName;
    @Schema(description = "메뉴 가격")
    private int menuPrice;
    @Schema(description = "카테고리번호(FK)")
    private int categoryCode;
    @Schema(description = "판매여부", example = "Y, N")
    private String orderableStatus;

}
