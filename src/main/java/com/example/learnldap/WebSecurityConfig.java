package com.example.learnldap;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${guser.ldap.dc}")
	private String ldapDC;
	
	@Value("${guser.ldap.url}")
	private String ldapURL;
	
	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		/*http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/","/logout").permitAll()
				.antMatchers("/api").hasRole("USER")
				.antMatchers("/api/admin").hasRole("ADMIN")
				.anyRequest().fullyAuthenticated()
				.and()
			.formLogin();*/
		
		http
			.authorizeRequests()
				.anyRequest().fullyAuthenticated()
				.and()
				.csrf().disable()
			.formLogin();
	}
	
	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
		auth
			.ldapAuthentication()
				.userDnPatterns( "uid={0},ou=people" )
				.groupSearchBase( "ou=groups" )
				.contextSource(contextSource())
				.passwordCompare()
					.passwordEncoder( new LdapShaPasswordEncoder() )
					.passwordAttribute( "userPassword" );
	}
	
	@Bean
	public DefaultSpringSecurityContextSource contextSource() {
		return  new DefaultSpringSecurityContextSource(Arrays.asList(ldapURL), ldapDC);
	}
	
}
