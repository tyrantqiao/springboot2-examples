package com.tyrantqiao.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.ServletException;

/**
 * @author tyrantqiao [tyrantqiao@gmail.com]
 * @see <a href="https://github.com/tyrantqiao">qiao's github</a>
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * {@code roles()} will add prefix "ROLE_". Beside this, It does the same thing as {@code authorities()}
	 *
	 * By the way {@code auth.jdbcAuthentication} can use sql.
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("user").password("admin").roles("USER")
				.and()
				.withUser("admin").password("password").authorities("USER", "ADMIN");
	}

	/**
	 * 设定拦截器并定义在帐号密码验证前拦截
	 *
	 * @param http
	 * @throws ServletException
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		ValidateFilter validateFilter = new ValidateFilter();
//		validateFilter.setFailureHandler(failHandler);
//		validateFilter.setSecurityProperties(securityProperties);
//		validateFilter.afterPropertiesSet();

//		String redirectUrl = "user/login";

//		http
//				.formLogin().loginPage("/user/login")
//				.loginProcessingUrl("/user/login/form")
//				.and()
//				.authorizeRequests()
//				.antMatchers("/user/login").permitAll()
//				.anyRequest().authenticated()
//				.and()
//				.httpBasic();
	}
}
