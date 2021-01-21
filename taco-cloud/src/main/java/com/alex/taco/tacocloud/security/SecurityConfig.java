package com.alex.taco.tacocloud;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/design", "/orders")
			.access("hasRole('ROLE_USER')")
			.antMatchers("/", "/**").access("permitAll")
			.and()
			.formLogin()
			.loginPage("/login")
			.and()
			.logout()
			.logoutSuccessUrl("/")
			.and()
			.csrf()
			.ignoringAntMatchers("/h2-console/**")
			.and()
			.headers()
			.frameOptions()
			.sameOrigin();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new StandardPasswordEncoder("53cr3t");
	}

	@Override
  	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService)
      		.passwordEncoder(encoder());
  	}

	// 在内存中存储用户
	/*
	@Override
  	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  		auth.inMemoryAuthentication()
        	.withUser("alex")
          	.password("blademaster")
          	.authorities("ROLE_USER")
        	.and()
        	.withUser("woody")
          	.password("bullseye")
          	.authorities("ROLE_USER");
	}
	*/

	/*
	@Autowired
  	DataSource dataSource;
  
  	@Override
  	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication()
        	.dataSource(dataSource);
	}
	*/

	/*
	// 使用JDBC认证，并定制查询的表结构和语句
	@Override
  	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication()
    		.dataSource(dataSource)
        	.usersByUsernameQuery("select username, password, enabled from Users where username=?")
        	.authoritiesByUsernameQuery("select username, authority from UserAuthorities where username=?");
  	}
  	*/

  	/*
  	// 使用JDBC认证，并定制查询的表结构和语句、使用密码加密
  	@Override
  	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication()
        	.dataSource(dataSource)
        	.usersByUsernameQuery( "select username, password, enabled from Users where username=?")
        	.authoritiesByUsernameQuery("select username, authority from UserAuthorities where username=?")
        	.passwordEncoder(new StandardPasswordEncoder("53cr3t"));
    
  	}
  	*/
}