package com.komal.students.security;

import com.komal.students.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
   /*     http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/student/list", "/student/addStudent", "/student/saveStudent", "/student/403").hasAnyAuthority("USER", "ADMIN")
                .antMatchers( "/student/updateStudent", "/student/deleteStudent" ).hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login").successForwardUrl("/student/list").permitAll()
                .and().logout().logoutSuccessUrl("/login").permitAll()
                .and().exceptionHandling().accessDeniedPage("/student/403")
                .and().cors()
                .and().csrf().disable();*/
//                .and().cors().and().csrf();
        
        http.authorizeRequests()
        .antMatchers("/student/list", "/student/addStudent", "/student/saveStudent").hasAnyAuthority("ADMIN", "USER")
        .antMatchers("/student/updateStudent/{id}", "/student/deleteStudent/{id}").hasAuthority("ADMIN")
        .anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login")
		.defaultSuccessUrl("/student/list").permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
		.and().exceptionHandling().accessDeniedPage("/student/403").and().csrf().and().cors().disable();

        
    }
}
