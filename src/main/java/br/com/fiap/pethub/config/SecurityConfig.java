package br.com.fiap.pethub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
				.oauth2Login(form -> form.loginPage("/login").defaultSuccessUrl("/pet").permitAll())
				.logout( logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"))
				.build();
	}
}
