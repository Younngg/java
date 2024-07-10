package org.zerock.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private PasswordEncoder passwordEncoder;

	public CustomUserDetailsService() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername: " + username);

		UserDetails userDetails = User.builder()
			.username("user1")
			.password(passwordEncoder.encode("1111"))
			.authorities("ROLE_USER")
			.build();

		return userDetails;
	}
}
