package com.controller;


import com.mapper.UserMapper;
import com.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MybatisController {
	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/query")
	@ResponseBody
	public List<user> queryUserList() {
		List<user> users = userMapper.queryUserList();
		return users;
	}
}
