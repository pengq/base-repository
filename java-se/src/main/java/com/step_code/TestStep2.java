package com.step_code;

import org.junit.Test;
/**
 * 有n步台阶，一次只能上1步或2步，共有多少种走法
 * 循环迭代
 *   (1)先到达f(x-2)，然后从f(x-2)直接跨2步
 *   (2)先到达f(x-1)，然后从f(x-1)跨1步
 *   f(x) = two + one
 *   f(x) = f(x-2) + f(x-1)
 *     two = f(x-2); one = f(x-1)
 */
public class TestStep2 {
	@Test
	public void test(){
		long start = System.currentTimeMillis();
		System.out.println(loop(100));//165580141
		long end = System.currentTimeMillis();
		System.out.println(end-start);//<1ms
	}

	public long loop(int n){
		if(n<1){
			throw new IllegalArgumentException(n + "不能小于1");
		}
		if(n==1 || n==2){
			return n;
		}

		long one = 2;//初始化为走到第二级台阶的走法
		long two = 1;//初始化为走到第一级台阶的走法
		long sum = 0;

		for(int i=3; i<=n; i++){
			//最后跨2步 + 最后跨1步的走法
			sum = two + one;
			two = one;
			one = sum;
		}
		return sum;
	}
}