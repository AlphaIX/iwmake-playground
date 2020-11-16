package com.iwmake.boot.elasticsearch.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author Dylan
 * @since 2020-11-17
 */
//@Configuration
public class ESConfig {

    /**
     * 解决部分ES版本，启动 netty引起的 issue
     */
    @PostConstruct
    void init(){
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }
}
