package com.project.second.study.controller;

import com.project.second.study.service.StudyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(tags = "스터디")
public class StudyController {

    private final StudyService service;

    @ApiOperation(value = "[Study] 스터디")
//    @ApiImplicitParam(name = "coinName", value = "상세검색할 코인 이름", defaultValue = "KRW-BTC")
    @GetMapping(value = "/study")
    public String study(){
        return "200 / OK";
    }
}
