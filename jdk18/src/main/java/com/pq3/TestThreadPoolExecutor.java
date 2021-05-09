package com.pq3;

import org.junit.Test;

import java.util.concurrent.*;

public class TestThreadPoolExecutor {
	/**
	 * corePoolSize:指定了线程池中的线程数量，它的数量决定了添加的任务是开辟新的线程去执行，
	 * 还是放到workQueue任务队列中去；
	 * <p>
	 * maximumPoolSize:指定了线程池中的最大线程数量，这个参数会根据你使用的workQueue任务队列的类型，
	 * 决定线程池会开辟的最大线程数量；
	 * <p>
	 * keepAliveTime:当线程池中空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；
	 * <p>
	 * unit:keepAliveTime的单位
	 * <p>
	 * workQueue:任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交队列、有界任务队列、
	 * 无界任务队列、优先任务队列几种；
	 * <p>
	 * threadFactory:线程工厂，用于创建线程，一般用默认即可；
	 * <p>
	 * handler:拒绝策略；当任务太多来不及处理时，如何拒绝任务；
	 */
	private static ExecutorService pool;

	/**
	 * 可以看到，当任务队列为SynchronousQueue，创建的线程数大于maximumPoolSize时，直接执行了拒绝策略抛出异常。
	 * <p>
	 * 使用SynchronousQueue队列，提交的任务不会被保存，总是会马上提交执行。
	 * 如果用于执行任务的线程数量小于maximumPoolSize，则尝试创建新的进程，
	 * 如果达到maximumPoolSize设置的最大值，则根据你设置的handler执行拒绝策略。
	 * 因此这种方式你提交的任务不会被缓存起来，而是会被马上执行，在这种情况下，
	 * 你需要对你程序的并发量有个准确的评估，才能设置合适的maximumPoolSize数量，
	 * 否则很容易就会执行拒绝策略；
	 */
	@Test
	public void test1() {
		//maximumPoolSize设置为2 ，拒绝策略为AbortPolic策略，直接抛出异常
		pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
		for (int i = 0; i < 3; i++) {
			pool.execute(new ThreadTask());
		}
	}

	/**
	 * 通过运行的代码我们可以看出PriorityBlockingQueue它其实是一个特殊的无界队列，
	 * 它其中无论添加了多少个任务，线程池创建的线程数也不会超过corePoolSize的数量，
	 * 只不过其他队列一般是按照先进先出的规则处理任务，
	 * 而PriorityBlockingQueue队列可以自定义规则根据任务的优先级顺序先后执行。
	 */
	@Test
	public void test2() {
		//优先任务队列
		pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

		for (int i = 0; i < 20; i++) {
			pool.execute(new ThreadTask1(i));
		}
	}

	/**
	 * 1、AbortPolicy策略：该策略会直接抛出异常，阻止系统正常工作；
	 * <p>
	 * 2、CallerRunsPolicy策略：如果线程池的线程数量达到上限，该策略会把任务队列中的任务放在调用者线程当中运行；
	 * <p>
	 * 3、DiscardOledestPolicy策略：该策略会丢弃任务队列中最老的一个任务，也就是当前任务队列中最先被添加进去的，马上要被执行的那个任务，并尝试再次提交；
	 * <p>
	 * 4、DiscardPolicy策略：该策略会默默丢弃无法处理的任务，不予任何处理。当然使用此策略，业务场景中需允许任务的丢失；
	 * <p>
	 * 内置的策略均实现了RejectedExecutionHandler接口，也可以自己扩展RejectedExecutionHandler接口，定义自己的拒绝策略
	 */
	@Test
	public void test3() {
//自定义拒绝策略
		pool = new ThreadPoolExecutor(1, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
				Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.out.println(r.toString() + "执行了拒绝策略");

			}
		});

		for (int i = 0; i < 10; i++) {
			pool.execute(new ThreadTask3());
		}
	}

	/**
	 * 线程池中线程就是通过ThreadPoolExecutor中的ThreadFactory，线程工厂创建的。
	 * 通过自定义ThreadFactory，可以按需要对线程池中创建的线程进行一些特殊的设置，
	 * 如命名、优先级等，
	 */
	@Test
	public void test4() {
//自定义线程工厂
		pool = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
				new ThreadFactory() {
					public Thread newThread(Runnable r) {
						System.out.println("线程" + r.hashCode() + "创建");
						//线程命名
						Thread th = new Thread(r, "threadPool" + r.hashCode());
						return th;
					}
				}, new ThreadPoolExecutor.CallerRunsPolicy());

		for (int i = 0; i < 10; i++) {
			pool.execute(new ThreadTask());
		}
	}
}

class ThreadTask implements Runnable {

	public ThreadTask() {

	}

	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
}

class ThreadTask1 implements Runnable {
	int i;

	public ThreadTask1(int i) {
		this.i = i;
	}
	public void run() {
		System.out.println(Thread.currentThread().getName() + "--" + i);
	}
}

class ThreadTask3 implements Runnable {
	public void run() {
		try {
			//让线程阻塞，使后续任务进入缓存队列
			Thread.sleep(1000);
			System.out.println("ThreadName:" + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
