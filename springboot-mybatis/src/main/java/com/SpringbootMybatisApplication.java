package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pq
 */
@SpringBootApplication
//全局扫描mapper文件夹(能扫描到所有子模块) mapper是dao接口的所在包名,以后必须创建的接口实现类的包名都叫mapper,
@MapperScan("com.**.mapper")
public class SpringbootMybatisApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootMybatisApplication.class, args);
    }

}
