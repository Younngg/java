package org.zerock.security.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.zerock.security.security.CustomUserDetailsService;
import org.zerock.security.security.handler.Custom403Handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

	private final DataSource dataSource;
	private final CustomUserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("---------------configure--------------");

		http.authorizeHttpRequests(authorizeRequests ->
			authorizeRequests
				.requestMatchers("member/login", "/error").permitAll()
				.anyRequest().authenticated()
			).formLogin(form -> form
			.loginPage("/member/login")
			.permitAll()
		);

		http.csrf(auth -> auth.disable());

		http.rememberMe(rememberMe -> rememberMe
			.key("12345678")
			.tokenRepository(persistentTokenRepository())
			.userDetailsService(userDetailsService)
			.tokenValiditySeconds(60*60*24*30)
		);

		http.exceptionHandling(exception -> exception
			.accessDeniedHandler(accessDeniedHandler())
		);

		http.oauth2Login(oauth2 -> oauth2
			.loginPage("/member/login")
		);

		return http.build();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new Custom403Handler();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		log.info("----------------web configure----------------");

		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

}
