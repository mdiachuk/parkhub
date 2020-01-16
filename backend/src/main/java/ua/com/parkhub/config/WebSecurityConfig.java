package ua.com.parkhub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.com.parkhub.filters.JwtAuthenticationFilter;

import javax.servlet.Filter;


@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${frontUrl}")
    private String frontUrl;


    @Autowired
    OAuth2ClientContext oauth2ClientContext;

    @Bean
    public FilterRegistrationBean<OAuth2ClientContextFilter> registerOauth2ClientFilter(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    @ConfigurationProperties("google.client")
    public AuthorizationCodeResourceDetails createGoogleResourceDetails() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("google.resource")
    public ResourceServerProperties createGoogleResourceServerProperties() {
        return new ResourceServerProperties();
    }

    private Filter createSsoFilter() {
        OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter(
                "/api/login/google");
        OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(createGoogleResourceDetails(), oauth2ClientContext);
        googleFilter.setRestTemplate(googleTemplate);
        googleFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/api/oauthSuccess"));
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(createGoogleResourceServerProperties().getUserInfoUri(),
                createGoogleResourceDetails().getClientId());
        tokenServices.setRestTemplate(googleTemplate);
        googleFilter.setTokenServices(tokenServices);
        SecurityContextHolder.getContext();
        return googleFilter;
    }


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/cancel", "/signup/user", "/home", "/signup/manager", "/cabinet", "/manager/parking",
                        "/manager/cabinet", "/manager/cabinet/", "/admin", "/admin/", "/parkings/**", "/booking", "/userPage", "/login",
                        "/forgot-password", "/reset-password", "/verify-email", "/phone-number");
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.requestMatchers()
                .antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/login**", "/api/logout**", "/api/signup/manager", "/api/signup/user", "/api/user/password/reset", "/api/user/verify", "/api/user/token/**", "/api/home",
                        "/api/user/token/refresh", "/api/user/token","/api/signup/user", "/api/cancel", "/api/oauthJwtToken",
                        "/api/login/google", "/api/parkings/{id}", "/api/booking","/api/oauthSuccess").permitAll()
                .antMatchers(HttpMethod.GET, "/api/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/admin/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/ticketlist").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/ticket/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/ticketlistcounter").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/manager/parking").hasAnyRole("MANAGER")
                .antMatchers(HttpMethod.GET, "/api/user/**").hasAnyRole("MANAGER", "ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/api/user/**").hasAnyRole("MANAGER", "ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/api/user/password").hasAnyRole("MANAGER", "ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/api/manager/cabinet").hasAnyRole("MANAGER")
                .antMatchers(HttpMethod.GET, "/api/manager/cabinet/").hasAnyRole("MANAGER")
                .antMatchers(HttpMethod.PUT, "/api/manager/cabinet/").hasAnyRole("MANAGER")
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and()
                .csrf().disable()
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(createSsoFilter(), JwtAuthenticationFilter.class);
    }



}
