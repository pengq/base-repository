package com.pq1.java8;

import com.pq1.java8.Employee.Status;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TestStreamAPI4 {

	List<Employee> emps = Arrays.asList(
			new Employee(102, "李四", 79, 6666.66, Status.BUSY),
			new Employee(101, "张三", 18, 9999.99, Status.FREE),
			new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
			new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(104, "赵六", 8, 7777.77, Status.FREE),
			new Employee(105, "田七", 38, 5555.55, Status.BUSY)
	);

	//3.parallelStream并行流

	@Test
	public void test1() {
		emps.parallelStream().map(obj -> obj.getId()+" ").forEach(System.out::print);
		System.out.println();
		emps.stream().map(obj -> obj.getId()+" ").forEach(System.out::print);

	}
	@Test
	public void tes2() {
		List<Integer> listFor = new ArrayList<>();
		List<Integer> listParallel = new ArrayList<>();

		IntStream.range(0, 1000).forEach(listFor::add);
		IntStream.range(0, 1000).parallel().forEach(listParallel::add);

		System.out.println("listFor size :" + listFor.size());
		System.out.println("listParallel size :" + listParallel.size());

	}
	@Test
	public void tes3() {

		IntStream.range(0, 10).forEach(System.out::println);


	}


}
