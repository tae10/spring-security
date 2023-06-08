package org.edupoll.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class SecurityConfig {
	
	
	@Bean
	public UserDetailsService users(DataSource dataSource) {
		var userDetailsService = new JdbcUserDetailsManager(dataSource);
		/*
		 * 디폴트 스키마와 테이블 구성이 다르면 query 를 바꿔주면 됨.
		 */
		userDetailsService.setUsersByUsernameQuery("SELECT ID, PASSWORD, ENABLED FROM USERS WHERE ID=?");
		userDetailsService.setAuthoritiesByUsernameQuery("SELECT USER_ID, ROLE FROM USER_ROLES WHERE USER_ID=?");
		return userDetailsService;
	}
	
	
	/*
	@Bean
	public UserDetailsService users() {
		//
		UserDetails u1 = User.builder().username("aaaa").password("{noop}1111").roles("user").build();
		UserDetails u2 = User.builder().username("bbbb").password("{noop}1111").roles("user", "manager").build();
		UserDetails u3 = User.builder().username("cccc").password("{noop}1111").roles("user", "manager", "admin")
				.build();
		// 임의의유저 추가 . role을 user, manager, admin 이거 세개 부여
		var userDetailsService =  new InMemoryUserDetailsManager(u1, u2, u3);
		return userDetailsService;
	}
	*/

	
	
	
	
	
	
	/* 
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception { http .csrf(t -> t.disable()) .authorizeHttpRequests(t -> t
	 * .requestMatchers("/login", "/login-task").permitAll()
	 * .requestMatchers("/admin/**").hasAuthority("ROLE_admin")
	 * .requestMatchers("/manager/**").hasRole("manager")
	 * .requestMatchers("/manager/**").hasRole("manager")
	 * .anyRequest().authenticated()
	 * 
	 * ) .formLogin( form ->
	 * form.loginPage("/login").loginProcessingUrl("/login-task") ); return
	 * http.build(); }
	 */
}
