package com.pq1.java8;

import org.junit.Test;

import java.lang.reflect.Method;

public class TestAnnotations {
	@Test
	public  void test() throws Exception {
		Class<TestAnnotations> testAnnotationsClass = TestAnnotations.class;
		Method show = testAnnotationsClass.getMethod("show",String.class);
		myAnnotation[] annotationsByType = show.getAnnotationsByType(myAnnotation.class);
		for (myAnnotation myAnnotation : annotationsByType) {
			System.out.println(myAnnotation);
			System.out.println(myAnnotation.value());
		}
	}
	@myAnnotation("111")
	@myAnnotation("222")
	public void show(String str){
	}
}
