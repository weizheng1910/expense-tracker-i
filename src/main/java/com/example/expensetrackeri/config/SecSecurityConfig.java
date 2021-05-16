package com.example.expensetrackeri.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("!https")
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

	public SecSecurityConfig() {
        super();
    }
    
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/api/**").permitAll()/* This must be accessible at all times */
        .antMatchers("/newAccount").permitAll()
        .antMatchers("/register").permitAll()
        .antMatchers("/dist/**").permitAll()
        .antMatchers("/anonymous*").anonymous()
        .antMatchers(HttpMethod.GET, "/index*", "/static/**", "/*.js", "/*.json", "/*.ico").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/").permitAll()
        .loginProcessingUrl("/perform_login")
        .defaultSuccessUrl("/tracker",true)
        .failureUrl("/noEntry")
        .and()
        .logout()
        .logoutUrl("/perform_logout")
        .deleteCookies("JSESSIONID");
        // @formatter:on
    }
    
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	// In memory users
    	/*
    	auth.inMemoryAuthentication()
        .withUser("user1").password(bCryptPasswordEncoder().encode("user1Pass")).roles("USER")
        .and()
        .withUser("user2").password(bCryptPasswordEncoder().encode("user2Pass")).roles("USER")
        .and()
        .withUser("admin").password(bCryptPasswordEncoder().encode("admin0Pass")).roles("ADMIN");
        */
    	// Retrieve users from database
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }


   

}