package com.singleton_code.test;

import com.singleton_code.single.Singleton4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



public class TestSingleton4 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/*Singleton4 s1 = Singleton4.getInstance();
		Singleton4 s2 = Singleton4.getInstance();
	
		System.out.println(s1 == s2);
		System.out.println(s1);
		System.out.println(s2);*/
		
		Callable<Singleton4> c = new Callable<Singleton4>() {

			@Override
			public Singleton4 call() throws Exception {
				return Singleton4.getInstance();
			}
		};
		
		ExecutorService es = Executors.newFixedThreadPool(2);
		Future<Singleton4> f1 = es.submit(c);
		Future<Singleton4> f2 = es.submit(c);
		
		Singleton4 s11 = f1.get();
		Singleton4 s22 = f2.get();
		
		System.out.println(s11 == s22);
		System.out.println(s11);
		System.out.println(s22);
		
		es.shutdown();
		
	}

}
