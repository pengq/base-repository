package com.pq3;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class TestCompletableFuture {
	@Test
	public void test1() {
		CompletableFuture<Void> runAsync = CompletableFuture.runAsync(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("runAsync");
			}
		});
		CompletableFuture.allOf(runAsync).join();
		System.out.println("runAsync.isDone() :" + runAsync.isDone());
	}

	/*
	   1、变换结果
	   public <U> CompletionStage<U> thenApply(Function<? super T,? extends U> fn);
	public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn);
	public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn,Executor executor);
	输入是上一个阶段计算后的结果，返回值是经过转化后结果
	 */
	@Test
	public void test2() {
		CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 1)
				.thenApply(i -> i + 1)
				.thenApply(i -> i * i).whenComplete((r, e) -> System.out.println(r));
		String result = CompletableFuture.supplyAsync(() -> {
			return "Hello ";
		}).thenApplyAsync(v -> v + "world").join();
		System.out.println(result);

	}

	/**
	 * 2、消费结果
	 * public CompletionStage<Void> thenAccept(Consumer<? super T> action);
	 * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
	 * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
	 * 这只是针对结果进行消费，入参是Consumer，没有返回值
	 */
	@Test
	public void test3() {
		CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
			return "Hello ";
		}).thenAccept(v -> {
			System.out.println("consumer: " + v);
		});
	}

	/**
	 * 结合两个CompletionStage的结果，进行转化后返回
	 * public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
	 * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
	 * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn,Executor executor);
	 * 需要上一阶段的返回值，并且other代表的CompletionStage也要返回值之后，
	 * 把这两个返回值，进行转换后返回指定类型的值。
	 * 说明：同样，也存在对两个CompletionStage结果进行消耗的一组方法
	 */
	@Test
	public void test4() {

		String result = CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello";
		}).thenCombine(CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "world";
		}),(s1,s2)->{return s1 + " " + s2;}).join();
		System.out.println(result);
	}
	/**
	 * 5、运行时出现了异常，可以通过exceptionally进行补偿
	 */
	@Test
	public void test5() {
		String result = CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(true) {
				throw new RuntimeException("exception test!");
			}

			return "Hi Boy";
		}).exceptionally(e->{
			System.out.println(e.getMessage());
			return "Hello world!";
		}).join();
		System.out.println(result);
	}
}


