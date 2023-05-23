package com.cafe.jwt;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

/*
samuelkawuma
5:36:19 PM
Apr 3, 2023
*/
@EnableWebSecurity
@Configuration
public class SecurityConfig {
	

     private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods(GET, POST, PUT, DELETE)
                        .allowedHeaders("*")
                        .allowedOriginPatterns("*")
                        .allowCredentials(true);
            }
        };
    }
	
	@Autowired
	private  JwtFilter jwtRequestFilter;
	
	
	

  @Primary
@Bean
public UserDetailsService  CustomerUserDetailService ()
{

    return new CustomerUserDetailService ();
}

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
         
        authProvider.setUserDetailsService(CustomerUserDetailService ());
        authProvider.setPasswordEncoder(passwordEncoder());
     
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @ConditionalOnClass
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }






@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
    .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
    //.cors()
    .and()
    .csrf() 
    .disable()
    
    .authorizeHttpRequests()

            .requestMatchers("/user/login","/user/signup","/user/forgotPassword","user/login1").permitAll() 
           .requestMatchers("/user/get","/user/getall").hasRole("Admin")
           .requestMatchers("/category/add","/category/get","/category/update").hasRole("Admin")
             //.requestMatchers("/user/changePassword").hasAnyRole("Admin","User")
             //.requestMatchers(HttpMethod.PUT,"/user/update").hasAnyRole("Admin","User")
            .anyRequest()
            .authenticated()
            .and()
            // .headers().httpStrictTransportSecurity().disable()
            // .and()
            .exceptionHandling()
            .and()
            
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}





    
}
