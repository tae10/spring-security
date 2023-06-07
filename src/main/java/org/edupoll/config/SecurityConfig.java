package org.edupoll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsService users() {
		UserDetails u1 = User.builder().username("aaaa").password("{noop}1111").roles("user").build();
		UserDetails u2 = User.builder().username("bbbb").password("{noop}1111").roles("user", "manager").build();
		UserDetails u3 = User.builder().username("cccc").password("{noop}1111").roles("user", "manager", "king")
				.build();
		// 임의의유저 추가 role 을 user,manager,admin 이거 세게 부여

		return new InMemoryUserDetailsManager(u1, u2, u3);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		/*
		 * http .csrf(t -> t.disable()) .authorizeHttpRequests(t -> t
		 * .requestMatchers("/login", "/login-task").permitAll()
		 * .requestMatchers("/admin/**").hasAuthority("ROLE_admin")
		 * .requestMatchers("/manager/**").hasRole("manager")
		 * .anyRequest().authenticated() )
		 */
		
		http.csrf().disable(); // 토큰검증 안한다는거
		http.authorizeHttpRequests(t -> t.requestMatchers("/auth", "/login").permitAll());
		http.authorizeHttpRequests(t -> t.requestMatchers("/admin/**").hasAuthority("ROLE_king"));
		http.authorizeHttpRequests(t -> t.requestMatchers("/manager/**").hasAnyRole("manager"));

		// 모은요청은 인증을 받아야 처리된다, 기본설정한다고생각하셈 authenticated 말고 permitAll 하면 인증안되고 다들어가짐
		http.authorizeHttpRequests(t -> t.anyRequest().authenticated());

		// /auth 라는 로그인페이지 경로만 허락해준거
		http.formLogin(form -> form.loginPage("/auth").loginProcessingUrl("/login"));

		return http.build();
	}

}
