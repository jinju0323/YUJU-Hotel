package kr.project.yuju_hotel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration  // 스프링 실행시 설정파일 읽어들이기 위한 어노테이션
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("YUJU-Hotel Swagger")
                .description("YUJU-Hotel REST API")
                .version("1.0.0");
    }
}
