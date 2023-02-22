package com.project.second.study.controller;

import com.project.second.study.service.StudyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(tags = "스터디")
public class StudyController {

    private final StudyService service;

    @ApiOperation(value = "[Spring Event Study] 회원가입")
    @ApiImplicitParam(
        name = "name"
        , value = "가입할 이름"
        , required = true
        , dataType = "string"
        , paramType = "path"
        , defaultValue = "안기경")
    @GetMapping(value = "/register/{name}")
    public String register(
            @PathVariable(name = "name") String name
    ){
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        service.join(name);

        stopwatch.stop();
        log.info("stopwatch.prettyPrint() = " + stopwatch.getTotalTimeSeconds());

        String returnMessage = stopwatch.getTotalTimeSeconds() + " seconds";

        return returnMessage;
    }

    @ApiOperation(value = "[Spring Event Study] 회원가입 Async")
    @ApiImplicitParam(
        name = "name"
        , value = "가입할 이름"
        , required = true
        , dataType = "string"
        , paramType = "path"
        , defaultValue = "안기경")
    @GetMapping(value = "/register/async/{name}", produces = "application/json;charset=UTF-8")
    public String registerAsync(
            @PathVariable(name = "name") String name
    ){
        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        service.joinAsync(name);

        stopwatch.stop();
        log.info("stopwatch.prettyPrint() = " + stopwatch.getTotalTimeSeconds());

        String returnMessage = stopwatch.getTotalTimeSeconds() + " seconds";

        return returnMessage;
    }
}
