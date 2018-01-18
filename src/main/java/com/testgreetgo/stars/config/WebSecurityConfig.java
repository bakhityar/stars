package com.testgreetgo.stars.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    //отключаем token csrf
    http.csrf().disable();

    //все неавторизованные входящие запросы направляем на /login
    http
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout().permitAll();

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    //отправляем в вебИгнор файлы разметки
    web.ignoring().antMatchers( "/vendor/**", "/app.css", "/favicon.png");
  }

  @Configuration
  protected static class AuthenticationConfiguration extends
      GlobalAuthenticationConfigurerAdapter {

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
      //захардкоденный логин и пароль + Роль
      auth
          .inMemoryAuthentication()
          .withUser("user").password("password").roles("ROLE");
    }

  }
}
