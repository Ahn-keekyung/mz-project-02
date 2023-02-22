package com.project.second.study.service;

import com.project.second.study.model.dto.User;
import com.project.second.study.model.dto.UserAsync;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudyService {
    private final ApplicationEventPublisher publisher;

    public void join(String name) {
        log.info("회원 추가 완료! 회원 이름은? : " + name);

        final var user = User.builder()
                        .name(name)
                        .build();

        publisher.publishEvent(user);
    }

    public void joinAsync(String name) {
        log.info("비동기 회원 추가 완료! 회원 이름은? : {}" , name);

        final var userAsync = UserAsync.builder()
                        .name(name)
                        .build();

        publisher.publishEvent(userAsync);
    }
}
