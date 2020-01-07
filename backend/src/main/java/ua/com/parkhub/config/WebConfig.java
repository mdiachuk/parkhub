package ua.com.parkhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {



    /**
     * CORS
     * @param registry
     */
    public void addCorsMappings(CorsRegistry registry) {

        //registry.addMapping("/**");
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS")
        .allowedHeaders("*")
        .allowedOrigins("http://localhost:4200")
        .allowCredentials(true);

    }

    //TODO very necessary @Beans or @Components configuration

    //https://www.baeldung.com/spring-mvc-tutorial

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:8080"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS"));
//        configuration.setAllowedHeaders(Arrays.asList("x-requested-with"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
    @Bean

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
