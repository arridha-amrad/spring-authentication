package com.arridhaamrad.authentication.securities;

import com.arridhaamrad.authentication.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
   @Autowired
   private UserServices userServices;

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
           .authorizeRequests().antMatchers("/api/auth/**").permitAll()
           .anyRequest().fullyAuthenticated()
           .and().httpBasic();
   }

   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder(){
      return new BCryptPasswordEncoder();
   }

   @Override
   public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
      authenticationManagerBuilder.userDetailsService(userServices).passwordEncoder(bCryptPasswordEncoder());
   }

   @Bean
   @Override
   protected AuthenticationManager authenticationManager() throws Exception {
      return super.authenticationManager();
   }

}
