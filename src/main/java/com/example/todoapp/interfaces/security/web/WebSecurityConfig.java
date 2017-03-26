package com.example.todoapp.interfaces.security.web;

import com.example.todoapp.interfaces.security.facade.UserDetailsServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceFacade userDetailsServiceFacade;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
        // それ以外は匿名アクセス禁止
        .anyRequest().authenticated();
		http.formLogin()
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/")
			.failureUrl("/login?error")
			.permitAll();
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true)
			.permitAll();
		// TODO:ログアウト時にクッキー削除もした方が良いかも
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsServiceFacade)
				.passwordEncoder(this.userDetailsServiceFacade.getPasswordEncoder());
	}
}
