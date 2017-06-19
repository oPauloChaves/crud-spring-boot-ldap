package com.example.learnldap.domain.person;

import org.springframework.data.ldap.repository.LdapRepository;

public interface PersonRepository extends LdapRepository<Person> {

	Person findByUsername( String username );
	
	Iterable<Person> findByUsernameLike( String username );

}