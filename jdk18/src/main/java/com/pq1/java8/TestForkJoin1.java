package com.pq1.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;

/**
 * 这是一个用来让你更加熟悉parallelStream的原理的实力
 * 1. 当需要处理递归分治算法时，考虑使用ForkJoinPool。
 * 2. 仔细设置不再进行任务划分的阈值，这个阈值对性能有影响。
 * 3. Java 8中的一些特性会使用到ForkJoinPool中的通用线程池。在某些场合下，需要调整该线程池的默认的线程数量。
 */
public class TestForkJoin1 {
	public static void main(String[] args) throws Exception {
		System.out.println("Hello World!");
		// 构造一个10000个元素的集合
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			list.add(i);
		}
		// 统计并行执行list的线程
		Set<Thread> threadSet = new CopyOnWriteArraySet<>();
		// 并行执行
		list.parallelStream().forEach(integer -> {
			Thread thread = Thread.currentThread();
			// System.out.println(thread);
			// 统计并行执行list的线程
			threadSet.add(thread);
		});
		System.out.println("threadSet一共有" + threadSet.size() + "个线程");
		System.out.println("系统一个有" + Runtime.getRuntime().availableProcessors() + "个cpu");
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			list1.add(i);
			list2.add(i);
		}
		Set<Thread> threadSetTwo = new CopyOnWriteArraySet<>();
		CountDownLatch countDownLatch = new CountDownLatch(2);
		Thread threadA = new Thread(() -> {
			list1.parallelStream().forEach(integer -> {
				Thread thread = Thread.currentThread();
				// System.out.println("list1" + thread);
				threadSetTwo.add(thread);
			});
			countDownLatch.countDown();
		});
		Thread threadB = new Thread(() -> {
			list2.parallelStream().forEach(integer -> {
				Thread thread = Thread.currentThread();
				// System.out.println("list2" + thread);
				threadSetTwo.add(thread);
			});
			countDownLatch.countDown();
		});

		threadA.start();
		threadB.start();
		countDownLatch.await();
		System.out.print("threadSetTwo一共有" + threadSetTwo.size() + "个线程");

		System.out.println("---------------------------");
		System.out.println(threadSet);
		System.out.println(threadSetTwo);
		System.out.println("---------------------------");
		threadSetTwo.addAll(threadSet);
		System.out.println(threadSetTwo);
		System.out.println("threadSetTwo一共有" + threadSetTwo.size() + "个线程");
		System.out.println("系统一个有" + Runtime.getRuntime().availableProcessors() + "个cpu");
	}
}