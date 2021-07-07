package com.jvm;

public class TestJVM {
	// java -Dstr=123 TestJVM
	//尚硅谷 JVM【归档】 https://www.cnblogs.com/huangwenjie/p/13405145.html
	public static void main(String[] args) {
		String str = System.getProperty("str");
		if (str == null) {
			System.out.println("xxxxx");
		} else {
			System.out.println(str);
		}
		System.gc();
	}
}
