package com.cs.question.api.common.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser.NullAuthenticationProvider;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private RestDeniedHandler restDeniedHandler;

  @Autowired
  private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

  @Autowired
  private AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter;

  @Override
  public void configure(WebSecurity web) throws Exception {
      web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
        .httpBasic()
          .disable()
        .csrf()
          .disable()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .exceptionHandling()
          .authenticationEntryPoint(restAuthenticationEntryPoint)
          .accessDeniedHandler(restDeniedHandler)
          .and()
        .headers().frameOptions().disable()
          .and()
        .authorizeRequests()
          .antMatchers(HttpMethod.OPTIONS, "/**")
            .permitAll()
          .antMatchers("/counselor/*").authenticated()
          .antMatchers("/counselor/login").permitAll()
          .antMatchers("/**").permitAll()
          .and()
        .formLogin()
          .disable()
        .addFilterBefore(authenticationTokenProcessingFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.authenticationProvider(new NullAuthenticationProvider());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }
}
