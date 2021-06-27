package com.pojo;

import lombok.Data;

/**
 * 实体
 *
 * @author pq
 */
@Data
public class user {
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 姓名
	 */
	private String name;
}
