package com.example.learnldap;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableLdapRepositories
@EnableSpringDataWebSupport
public class WebConfiguration {

}
