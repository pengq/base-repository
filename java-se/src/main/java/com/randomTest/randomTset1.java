package com.randomTest;


import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class randomTset1 {
	@Test
	public void test() {
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int i1 = random.nextInt(10);
			System.out.println(i1);
		}
	}

	@Test
	public void test1() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		for (int i = 0; i < 10; i++) {
			int i1 = random.nextInt(10);
			System.out.println(i1);
		}
	}
}