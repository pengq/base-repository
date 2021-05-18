package com.offer_code;

import org.junit.Test;

public class test1 {
	/**
	 * 赋值=，最后计算
	 * =右边的从左到右加载值依次压入操作数栈
	 * 实际先算哪个，看运算符优先级
	 * 自增、自减操作都是直接修改变量的值，不经过操作数栈
	 * 最后的赋值之前，临时结果也是存储在操作数栈中
	 */
	@Test
	public void test1() {
		int i = 1;
		i = i++;
		int j = i++;
		//      2       4   4
		int k = i++ + ++i * i++;
		System.out.println("i=" + i);
		System.out.println("j=" + j);
		System.out.println("k=" + k);

	}
}
