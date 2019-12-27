package com.baidu.mall.config;

import com.baidu.mall.convert.String2DateConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /*
    * 自定义日期参数转换器，将前端字符串格式数据转换成日期
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(new String2DateConverter());
    }
}
