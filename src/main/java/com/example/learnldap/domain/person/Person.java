package com.example.learnldap.domain.person;

import java.io.Serializable;

import javax.naming.Name;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entry( objectClasses = { "inetOrgPerson", "organizationalPerson", "person", "top" } )
final public class Person implements Serializable {

	private static final long serialVersionUID = 1259808223363806053L;

	@Id
	@JsonIgnore
	private Name dn;

	@NotEmpty
	@Attribute( name = "mail" )
	private String email;

	@NotEmpty
	@Attribute( name = "uid" )
	@DnAttribute( value = "uid", index = 1 )
	private String username;
	
	@JsonIgnore
	@Transient
    @DnAttribute(value = "ou", index = 0)
    private String company = "people";

	@NotEmpty
	@Attribute( name = "cn" )
	private String fullname;

	@NotEmpty
	@Attribute( name = "sn" )
	private String lastName;

	private String description;

	@Attribute( name = "telephoneNumber" )
	private String phone;

}
