package com.mapper;

import com.pojo.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pq
 */
@Mapper
public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return List<user>
     */
	List<user> queryUserList();


}
