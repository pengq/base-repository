package com.pq.springcloud.controller;

import com.pq.springcloud.entities.CommonResult;
import com.pq.springcloud.entities.Payment;
import com.pq.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
public class OrderController {

	//public static final String PaymentSrv_URL = "http://localhost:8001";
	// 通过在eureka上注册过的微服务名称调用
	public static final String PaymentSrv_URL = "http://SCLOUD-PAYMENT-SERVICE";

	@Autowired
	private RestTemplate restTemplate;

	//可以获取注册中心上的服务列表
	@Resource
	private DiscoveryClient discoveryClient;
	@Resource
	private LoadBalancer loadBalancer;

	@GetMapping("/consumer/payment/create") //客户端用浏览器是get请求，但是底层实质发送post调用服务端8001
	public CommonResult create(Payment payment) {
		return restTemplate.postForObject(PaymentSrv_URL + "/payment/create", payment, CommonResult.class);
	}


	@GetMapping("/consumer/payment/get/{id}")
	public CommonResult getPayment(@PathVariable Long id) {
		return restTemplate.getForObject(PaymentSrv_URL + "/payment/get/" + id, CommonResult.class, id);
	}

	@GetMapping("/consumer/payment/getForEntity/{id}")
	public CommonResult getPayment2(@PathVariable Long id) {
		ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PaymentSrv_URL + "/payment/get/" + id, CommonResult.class, id);
		if (forEntity.getStatusCode().is2xxSuccessful()) {
			return forEntity.getBody();
		} else {
			return new CommonResult(444, "失败");
		}

	}

	@GetMapping("/consumer/payment/lb")
	public String getPaymentLB() {
		List<ServiceInstance> instances = discoveryClient.getInstances("SCLOUD-PAYMENT-SERVICE");

		if (instances == null || instances.size() <= 0) {
			return null;
		}
		ServiceInstance serviceInstance = loadBalancer.instances(instances);
		URI uri = serviceInstance.getUri();

		return restTemplate.getForObject(uri + "/payment/lb", String.class);
	}
}
