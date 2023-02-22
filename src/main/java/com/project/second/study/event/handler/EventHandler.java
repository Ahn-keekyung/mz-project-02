package com.project.second.study.event.handler;

import com.project.second.study.model.dto.User;
import com.project.second.study.model.dto.UserAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventHandler {
    @EventListener // 이벤트 리스너로 등록이 되고, 매개변수에 이벤트 클래스를 정의하면 해당 이벤트가 발생했을 때 수신해서 처리를 할 수 있습니다.
    public void sendSms(User event) {
        log.info("가입 축하 메세지 전송 완료. 이름은? : " + event.getName());
    }

    @EventListener
    public void makeCoupon(User event) {
        log.info("쿠폰 전송 완료. 이름은? : " + event.getName());
    }

    @EventListener
    @Async("usrAsyncExecutor")
    public void sendSmsAsync(UserAsync event) {
        log.info(" 1 ");
        log.info("SEND-Thread.currentThread().getName() = " + Thread.currentThread().getName());
        log.info(" 2 ");
        log.info("SEND-Thread.currentThread() = " + Thread.currentThread());
        log.info(" 3 ");
        log.info("비동기 가입 축하 메세지 전송 완료. 이름은? : " + event.getName());
        log.info(" ============ 1 ============ ");
    }

    @EventListener
    @Async("defaultAsyncExecutor")
    public void makeCouponAsync(UserAsync event) {
        log.info(" 4 ");
        log.info("MAKE-Thread.currentThread().getName() = " + Thread.currentThread().getName());
        log.info(" 5 ");
        log.info("MAKE-Thread.currentThread() = " + Thread.currentThread());
        log.info(" 7 ");
        log.info("비동기 쿠폰 전송 완료. 이름은? : " + event.getName());
        log.info(" ============ 2 ============ ");
    }

}
