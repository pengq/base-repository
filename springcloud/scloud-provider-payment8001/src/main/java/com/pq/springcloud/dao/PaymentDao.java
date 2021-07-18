package com.pq.springcloud.dao;

import com.pq.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper  //import org.apache.ibatis.annotations.Mapper;
public interface PaymentDao
{
	public int create(Payment payment);

	public Payment getPaymentById(@Param("id") Long id);
}