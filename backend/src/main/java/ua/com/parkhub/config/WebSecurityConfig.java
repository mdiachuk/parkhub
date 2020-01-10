package ua.com.parkhub.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;



@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests().antMatchers("/api/login").permitAll().and().csrf().disable();
//    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/api/login");
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and().csrf().disable();
    }

//    @Value("${frontUrl}")
//    private String frontUrl;
//
////    @Autowired
////    OAuth2ClientContext oauth2ClientContext;
//
//    @Bean
//    public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
//        FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
//        registration.setFilter(filter);
//        registration.setOrder(-100);
//        return registration;
//    }
//
//    @Bean
//    @ConfigurationProperties("google.client")
//    public AuthorizationCodeResourceDetails google() {
//        return new AuthorizationCodeResourceDetails();
//    }
//
//    @Bean
//    @ConfigurationProperties("google.resource")
//    public ResourceServerProperties googleResource() {
//        return new ResourceServerProperties();
//    }
//
////    private Filter ssoFilter() {
////        OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter(
////                "/login/google");
////        OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(google(), oauth2ClientContext);
////        googleFilter.setRestTemplate(googleTemplate);
////        googleFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/oauthSuccess"));//redirect to frontURL
////        UserInfoTokenServices tokenServices = new UserInfoTokenServices(googleResource().getUserInfoUri(),
////                google().getClientId());
////        tokenServices.setRestTemplate(googleTemplate);
////        googleFilter.setTokenServices(tokenServices);
////        SecurityContextHolder.getContext();
////        return googleFilter;
////    }
//
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.cors()
////                .and().antMatcher("/**").authorizeRequests().antMatchers("/","/home","/login**", "/webjars/**", "/error**","/manager/parking").permitAll().anyRequest()
////                .authenticated().and().exceptionHandling()
////                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/")).and().logout()
////                .logoutSuccessUrl("/").permitAll().and().csrf().disable()
////                .addFilterBefore(ssoFilter(), UsernamePasswordAuthenticationFilter.class);
////    }
//
//
//
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("/api/login","/api/login/google");
//    }
//
//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    }



}
