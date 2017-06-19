package com.example.learnldap.domain.person;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class PersonDTO {

	private @NonNull String username;
	private @NonNull String fullname;
	private @NonNull String lastName;
	private @NonNull String email;
	private String phone;
	private String description;

	public PersonDTO( Person person ) {
		this.username = person.getUsername();
		this.fullname = person.getFullname();
		this.lastName = person.getLastName();
		this.email = person.getEmail();
		this.phone = person.getPhone();
		this.description = person.getDescription();
	}

	@JsonIgnore
	public Person getPerson() {
		Person person = new Person();
		person.setCompany( "people" );
		person.setUsername( this.username );
		person.setFullname( this.fullname );
		person.setLastName( this.lastName );
		person.setEmail( this.email );
		person.setPhone( this.phone );
		person.setDescription( this.description );
		return person;
	}

}
