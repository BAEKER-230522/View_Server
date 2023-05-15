package com.baeker.baeker.msaController;

import com.baeker.baeker.base.request.Rq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final Rq rq;

    //-- welcome page redirect --//
    @GetMapping("/")
    public String home() {
        log.info("홈페이지 요청 확인");

        if (rq.isLogin())
            log.info("로그인 성공");

        return "test/login";
    }

    //-- Config Server Test --//
    @Value("${custom.con}")
    private String configStr;

    @GetMapping("test")
    @ResponseBody
    public String test() {
        return configStr;
    }
}
