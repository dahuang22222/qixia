package com.changjianghuyu.qixia.web.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringMvcConfigurationInitializer extends WebMvcConfigurerAdapter{
    @Value("${MultipartFile.imagePath}")
    String imagePath;

    @Value("${MultipartFile.imageRequestPath}")
    String imageRequestPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //上传的图片在imagePath目录下，访问路径如下:http://localhost:8008/image/icon_yxgl@2x.png
        //其中image表示访问的前缀。"file:imagePath"是文件真实的存储路径
        registry.addResourceHandler(imageRequestPath+"**").addResourceLocations("file:"+imagePath);
        //file:/opt/plate/指向本地图片路径地址
        super.addResourceHandlers(registry);
    }
}