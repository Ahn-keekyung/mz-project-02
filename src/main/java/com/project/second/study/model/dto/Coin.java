package com.project.second.study.model.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel(value = "코인 마켓, 이름 정보")
@Api
public class Coin {

    @ApiModelProperty(value = "마켓")
    private String market;

    @ApiModelProperty(value = "한국 이름")
    private String korean_name;

    @ApiModelProperty(value = "영어 이름")
    private String english_name;

}
