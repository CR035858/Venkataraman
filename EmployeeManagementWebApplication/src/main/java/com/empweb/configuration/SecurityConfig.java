package com.empweb.configuration;

import org.springframework.beans.factory.annotation.Autowired;







import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//import com.empweb.security.CustomAuthenticationProvider;
import com.empweb.serviceImplementation.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
/*	private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    } */

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}
	
	 @Bean
	 public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	 }
	

		/*
		 * @Autowired private CustomAuthenticationProvider customAuthenticationProvider;
		 * 
		 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
		 * throws Exception { auth.authenticationProvider(customAuthenticationProvider);
		 * }
		 */

	//@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
	
		
		http.authorizeHttpRequests((configurer) -> configurer.requestMatchers("/employees/**").permitAll()
				.requestMatchers("/users/**").permitAll().requestMatchers("/").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/roles/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/employees/**").hasAnyRole( "ADMIN").requestMatchers("/").permitAll()
				.requestMatchers(HttpMethod.POST,"employees").hasAuthority("ADMIN")
		        .requestMatchers(HttpMethod.PUT,"employees").hasAuthority("ADMIN")
		        .requestMatchers(HttpMethod.DELETE,"employees/*").hasAuthority("ADMIN")
				.anyRequest().authenticated());
				/*.formLogin((form) -> form.loginPage("/login").loginProcessingUrl("/authenticateTheUser").permitAll());*/
				return http.build();
		
	}
	



}
