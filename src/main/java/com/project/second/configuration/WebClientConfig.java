package com.project.second.configuration;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        /**
         * tcpConfiguration로 Connect Timeout을 설정 했다면 option으로 변경을 권장한다.
         * tcpConfiguration는 사용이 중지될 예정이며, option을 하면 기존의 방식과 동일한 설정을 할 수 있다.
         */
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(
                        conn -> conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                );

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        return WebClient.builder()
                .clientConnector(connector)
                .baseUrl("http://127.0.0.1:32000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url","http://127.0.0.1:32000"))
                .build();

        /**
         * WebClient를 생성하는 방법은 create(), option을 추가할 수있는 build()를 사용한 생성 두가지가 있다.
         *
         * 적용가능한 option List
         * uriBuilderFactory    : base url을 커스텀한 UriBuilderFactory
         * defaultHeader        : 모든 요청에 사용할 헤더
         * defaultCookie        : 모든 요청에 사용할 쿠키
         * defaultRequest       : 모든 요청을 커스텀할 Consumer
         * filter               : 모든 요청에 사용할 클라이언트 필터
         * exchangeStrategies   : HTTP 메시지 reader & writer 커스터마이징
         * clientConnector      : HTTP 클라이언트 라이브러리 세팅
         *
         * 빌드한 뒤부터 WebClient는 불변이다.
         * WebClient를 Singleton으로 사용할 때 기존 설정과 다른 사용을 하고 싶을때
         * 같은 WebClient 인스턴스지만 mutate()를 이용해 서로 다른 설정 값을 가지는 요청을 사용할 수 있다.
         */

    }
}
