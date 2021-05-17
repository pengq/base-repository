package com.singleton_code.test;


import com.singleton_code.single.Singleton1;

public class TestSingleton1 {

	public static void main(String[] args) {
		Singleton1 s = Singleton1.INSTANCE;
		System.out.println(s);
	}

}
