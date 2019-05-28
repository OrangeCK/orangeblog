package com.ck.orangeblogweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ck
 * @date 2018/11/20 15:39
 * Description  : swagger2的配置类
 */
/**
 * 注解开启 swagger2 功能
 */
@EnableSwagger2
/**
 * 注解标示,这是一个配置类,@Configuation注解包含了@Component注解,可以不用在使用@Component注解标记这是个bean了
 */
@Configuration
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbk5hbWUiOiJjaGVua2FuZyIsImV4cCI6MTU2MDMyMDA0NX0.0kRuzrmHWdMZqK5GaDbiasn3o1QYKjxTfrt0wXQaHQQ";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                "eyJsb2dpbk5hbWUiOiJjaGVua2FuZyIsInBlcm1MaXN0IjpbIi9vcmFuZ2VibG9nL2VtcGxveWVlL2VtcGxveWVlUGFnZUxpc3QiLCIvb3JhbmdlYmxvZy9pbWFnZS9kZWxldGVJbWFnZUJsb" +
                "2ciLCIvb3JhbmdlYmxvZy9hbGlPc3MvdXBsb2FkVG9Pc3MiLCIvdXBsb2FkL3VwbG9hZEltZyIsIi91cGxvYWQvZGVsZXRlVXBsb2FkSW1nIiwiL29yYW5nZWJsb2cvZW1wbG95ZWUvc2F2ZUVt" +
                "cGxveWVlIiwiL29yYW5nZWJsb2cvaW1hZ2UvaW1hZ2VEZXRhaWwiLCIvdXBsb2FkL2Rvd25sb2FkSW1nIiwiL29yYW5nZWJsb2cvaW1hZ2Uvc2F2ZUltYWdlQmxvZyIsIi9vcmFuZ2VibG9nL2V" +
                "tcGxveWVlL2Rpc2FibGVFbXBsb3llZSIsIi9vcmFuZ2VibG9nL2ltYWdlL2ltYWdlQmxvZ1BhZ2VMaXN0Il0sImV4cCI6MTU2MDMzMjI5N30" +
                ".LIb6REoJgVfDFd_FHt_DRjex6Aa2Srj9ic8exaLYAeI";
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("服务访问token").modelRef(new ModelRef("string"))
                .parameterType("header").required(true).defaultValue(token).hidden(true).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                //调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
                .apiInfo(apiInfo())
                .select()
                //控制暴露出去的路径下的实例
                //如果某个接口不想暴露,可以使用以下注解
                //@ApiIgnore 这样,该接口就不会暴露在 swagger2 的页面下
                .apis(RequestHandlerSelectors.basePackage("com.ck.orangeblogservice.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    /**
     * 构建 api文档的详细信息函数
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 测试使用 Swagger2 构建RESTful API")
                //创建人
                .contact("Kang")
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
}
