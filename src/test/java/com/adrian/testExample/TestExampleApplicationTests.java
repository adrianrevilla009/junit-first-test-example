package com.adrian.testExample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestExampleApplicationTests {

	private Calculator underTest = new Calculator();

	class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}

	@Test
	void addTest() {
		// given
		int a = 10;
		int b = 15;
		// when
		int sum = underTest.add(a, b);
		// then
		assertThat(sum).isEqualTo(25);
	}

}
