package com.boot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		//Basic Authentication without logout
		//The user would never be able to logout
		http
		.authorizeRequests()
//		.antMatchers("/home", "/login", "/register").permitAll()
//		.antMatchers("/public/**").permitAll()
		
		.antMatchers("/signin").permitAll()
		
		//Role based authorization
//		.antMatchers("/public/**").hasRole("NORMAL")
//		.antMatchers("/user/**").hasRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
//		.httpBasic();
		.formLogin()
		.loginPage("/signin")
		.loginProcessingUrl("/dologin")
		.defaultSuccessUrl("/user/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("Rajat").password("1234").roles("NORMAL");
//		auth.inMemoryAuthentication().withUser("Rohit").password("1234").roles("ADMIN");
		
		
		auth.inMemoryAuthentication().withUser("Rajat").password(passwordEncoder().encode("1234")).roles("NORMAL");
		auth.inMemoryAuthentication().withUser("Rohit").password(passwordEncoder().encode("1234")).roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		//No Encription-Decription would be there for password
//		return NoOpPasswordEncoder.getInstance();
		
		return new BCryptPasswordEncoder(10);
	}
	
}
