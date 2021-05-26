package ConcurrencyTest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
class Foo {
	/**Semaphore(信号量)：是一种计数器，用来保护一个或者多个共享资源的访问。
	 * 如果线程要访问一个资源就必须先获得信号量。
	 * 如果信号量内部计数器大于0，信号量减1，然后允许共享这个资源；否则，如果信号量的计数器等于0，信号量将会把线程置入休眠直至计数器大于0.当信号量使用完时，必须释放。
	 * Semaphore
	 * void acquire() ：从信号量获取一个许可，如果无可用许可前将一直阻塞等待，
	 * void acquire(int permits) ：获取指定数目的许可，如果无可用许可前也将会一直阻塞等待
	 * boolean tryAcquire()：从信号量尝试获取一个许可，如果无可用许可，直接返回false，不会阻塞
	 * boolean tryAcquire(int permits)： 尝试获取指定数目的许可，如果无可用许可直接返回false
	 * boolean tryAcquire(int permits, long timeout, TimeUnit unit)： 在指定的时间内尝试从信号量中获取许可，如果在指定的时间内获取成功，返回true，否则返回false
	 * void release()： 释放一个许可，别忘了在finally中使用，注意：多次调用该方法，会使信号量的许可数增加，达到动态扩展的效果，如：初始permits为1， 调用了两次release，最大许可会改变为2
	 * int availablePermits()： 获取当前信号量可用的许可
	 */
	public Semaphore seam_first_two = new Semaphore(0);

	public Semaphore seam_two_second = new Semaphore(0);

	public Foo() {

	}

	public void first(Runnable printFirst) throws InterruptedException {
		printFirst.run();
		seam_first_two.release();
	}

	public void second(Runnable printSecond) throws InterruptedException {
		seam_first_two.acquire();
		printSecond.run();
		seam_two_second.release();
	}

	public void third(Runnable printThird) throws InterruptedException {
		seam_two_second.acquire();
		printThird.run();
	}
	public static void main(String[] args) {
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		// 只能5个线程同时访问
		final Semaphore semp = new Semaphore(5);
		// 模拟20个客户端访问
		for (int index = 0; index < 20; index++) {
			final int NO = index;
			Runnable run = new Runnable() {
				public void run() {
					try {
						// 获取许可
						semp.acquire();
						System.out.println("Accessing: " + NO);
						//模拟实际业务逻辑
						Thread.sleep((long) (Math.random() * 10000));
						// 访问完后，释放
						semp.release();
					} catch (InterruptedException e) {
					}
				}
			};
			exec.execute(run);
		}

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println(semp.getQueueLength());
		// 退出线程池
		exec.shutdown();
	}
}