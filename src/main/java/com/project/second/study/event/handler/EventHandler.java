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
        log.info("sendSms 시작");
        timeSleep();
        log.info("sendSms 끝");
    }

    @EventListener
    public void makeCoupon(User event) {
        log.info("makeCoupon 시작");
        timeSleep();
        log.info("makeCoupon 끝");
    }

    @EventListener
    @Async("usrAsyncExecutor")
    public void sendSmsAsync(UserAsync event) {
        log.info("sendSmsAsync 시작");
        timeSleep();
        log.info("sendSmsAsync 끝");
    }

    @EventListener
    @Async("defaultAsyncExecutor")
    public void makeCouponAsync(UserAsync event) {
        log.info("makeCouponAsync 시작");
        timeSleep();
        log.info("makeCouponAsync 끝");
    }

    public void timeSleep(){
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
