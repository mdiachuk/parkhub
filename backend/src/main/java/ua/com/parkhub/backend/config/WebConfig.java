package ua.com.parkhub.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * CORS
     * @param registry
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
    //TODO very necessary @Beans or @Components configuration

    //https://www.baeldung.com/spring-mvc-tutorial

}
