package com.pq.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pq.springcloud.service.PaymentFallbackService;
import com.pq.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Slf4j
// 全局的服务降级方法，有自定义的就优先自定义的
@DefaultProperties(defaultFallback = "payment_global_FallbackMethod")
public class OrderHystirxController {
	@Resource
	private PaymentHystrixService paymentHystrixService;

	@GetMapping("/consumer/payment/hystrix/ok/{id}")
	public String paymentInfo_OK(@PathVariable("id") Integer id) {
		String result = paymentHystrixService.paymentInfo_OK(id);
		return result;
	}

	@GetMapping("/consumer/payment/hystrix/timeout/{id}")
	/*@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
	})*/
	@HystrixCommand //加了@DefaultProperties属性注解，并且没有写具体方法名字，就用统一全局的
	public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
		String result = paymentHystrixService.paymentInfo_TimeOut(id);
		return result;
	}

	public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
		return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
	}

	public String payment_global_FallbackMethod() {
		return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
	}

}

