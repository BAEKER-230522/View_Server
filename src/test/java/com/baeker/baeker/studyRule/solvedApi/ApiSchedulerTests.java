package com.baeker.baeker.studyRule.solvedApi;


import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Transactional
public class ApiSchedulerTests {
    @SpyBean
    private ApiScheduler apiScheduler;

    @Test
    @DisplayName("문제풀이 수 테스트")
    void solvedCountSchedulerTest() throws IOException, ParseException {
        await().atMost(4, TimeUnit.SECONDS).untilAsserted(() -> {
            verify(apiScheduler, atLeast(2)).solvedCountSchedule();
        });
    }

    @Test
    @DisplayName("티어별 문제풀이 테스트")
    void tierSchedulerTest() throws IOException, ParseException {
        await().atMost(4, TimeUnit.SECONDS).untilAsserted(() -> {
            verify(apiScheduler, atLeast(2)).tierSchedule();
        });
    }
}
