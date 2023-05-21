package com.baeker.baeker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BaekerApplicationTests {

	@Value("${custom.con}")
	private String str;

	@Test
	void contextLoads() {
		assertThat(str).isEqualTo("connection success!");
	}

}
