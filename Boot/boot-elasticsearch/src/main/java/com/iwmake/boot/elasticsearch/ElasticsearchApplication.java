package com.iwmake.boot.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot与Elasticsearch6.x整合
 * 查看测试包下 ESTest
 * @see {@link com.iwmake.boot.elasticsearch.ESTest}
 * @author Dylan
 * @since 2020-11-16
 */
@SpringBootApplication
public class ElasticsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }
}
