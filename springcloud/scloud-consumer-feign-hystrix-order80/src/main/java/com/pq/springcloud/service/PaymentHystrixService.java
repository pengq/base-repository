package com.pq.springcloud.service;

import com.pq.springcloud.entities.CommonResult;
import com.pq.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "SCLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService
{
	@GetMapping("/payment/hystrix/ok/{id}")
	String paymentInfo_OK(@PathVariable("id") Integer id);

	@GetMapping("/payment/hystrix/timeout/{id}")
	String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
