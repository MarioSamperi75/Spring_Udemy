package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//		add our users for in memory authentication (HARDCODED)
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		
//		auth.inMemoryAuthentication()
//			.withUser(users.username("Mario").password("Mario123").roles("EMPLOYEE"))
//			.withUser(users.username("Ellen").password("Ellen123").roles("EMPLOYEE", "MANAGER"))
//			.withUser(users.username("Sofia").password("Sofia123").roles("EMPLOYEE", "ADMIN"));
		
		
		// use jdbc authentication instead of hardcoded data!
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			//.anyRequest().authenticated()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
		.and()
		.formLogin()
			.loginPage("/showMyLoginPage")
			.loginProcessingUrl("/authenticateTheUser")
			.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		}
	
	

}
