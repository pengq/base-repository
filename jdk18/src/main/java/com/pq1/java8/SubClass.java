package com.pq1.java8;

import com.pq.java8.MyFun;


public class SubClass /*extends MyClass*/ implements MyFun, MyInterface {

	@Override
	public String getName() {
		return MyInterface.super.getName();
	}

	@Override
	public Integer getValue(Integer num) {
		return null;
	}
}
