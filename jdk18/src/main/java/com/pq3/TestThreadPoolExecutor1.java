package com.pq3;

import org.junit.Test;

import java.util.concurrent.*;

public class TestThreadPoolExecutor1 {
	/**
	 * ThreadPoolExecutor扩展主要是围绕beforeExecute()、afterExecute()和terminated()三个接口实现的，
	 * <p>
	 * 1、beforeExecute：线程池中任务运行前执行
	 * <p>
	 * 2、afterExecute：线程池中任务运行完毕后执行
	 * <p>
	 * 3、terminated：线程池退出后执行
	 */
	private static ExecutorService pool;

	@Test
	public void test1() {
//实现自定义接口
		pool = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5),
				new ThreadFactory() {
					public Thread newThread(Runnable r) {
						System.out.println("线程" + r.hashCode() + "创建");
						//线程命名
						Thread th = new Thread(r, "threadPool" + r.hashCode());
						return th;
					}
				}, new ThreadPoolExecutor.CallerRunsPolicy()) {

			protected void beforeExecute(Thread t, Runnable r) {
				System.out.println("准备执行：" + ((ThreadTask4) r).getTaskName());
			}

			protected void afterExecute(Runnable r, Throwable t) {
				System.out.println("执行完毕：" + ((ThreadTask4) r).getTaskName());
			}

			protected void terminated() {
				System.out.println("线程池退出");
			}
		};

		for (int i = 0; i < 10; i++) {
			pool.execute(new ThreadTask4("Task" + i));
		}
		pool.shutdown();
	}

}

class ThreadTask4 implements Runnable {
	private String taskName;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public ThreadTask4(String name) {
		this.setTaskName(name);
	}

	public void run() {
		//输出执行线程的名称
		System.out.println("TaskName" + this.getTaskName() + "---ThreadName:" + Thread.currentThread().getName());
	}
}