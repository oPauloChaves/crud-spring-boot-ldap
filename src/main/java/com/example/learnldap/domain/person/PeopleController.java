package com.example.learnldap.domain.person;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping( "/people" )
public class PeopleController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping( value = { "", "/" } )
	public String list( @ModelAttribute( "filter" ) PersonFilterDTO filter, Model mv ) {
		/*String username = filter.getUsername();
		if (username == null) {
			username = "";
		}
		Iterable<Person> list = personRepository.findByUsernameLike( username );*/
		Iterable<Person> list = personRepository.findAll();

		mv.addAttribute( "listPeople", list );
		return "list";
	}

	@GetMapping( "/new" )
	public String newPerson( Model mv ) {
		mv.addAttribute( "person", new PersonDTO() );
		return "new";
	}

	@GetMapping( "/{username}" )
	public String editPerson( Model mv, @PathVariable( "username" ) String username ) {
		Person person = personRepository.findByUsername( username );
		mv.addAttribute( "person", new PersonDTO( person ) );
		return "edit";
	}

	@PostMapping( value = { "", "/" } )
	public String add( @ModelAttribute( "person" ) PersonDTO personDTO, Model mv, BindingResult result ) {
		if ( !result.hasErrors() ) {
			personRepository.save( personDTO.getPerson() );
			mv.addAttribute( "msgSuccess", "New user successfully added!" );
			mv.addAttribute( "person", new PersonDTO() );
		}

		return "new";
	}

	@PutMapping( "/{username}" )
	public String update( @PathVariable( "username" ) String username, @ModelAttribute( "person" ) PersonDTO personDTO, Model mv, BindingResult result ) throws InvalidNameException {
		if ( !result.hasErrors() ) {
			Person person = personRepository.findByUsername( username );
			person.setFullname( personDTO.getFullname() );
			person.setLastName( personDTO.getLastName() );
			person.setPhone( personDTO.getPhone() );
			person.setEmail( personDTO.getEmail() );
			person.setDescription( personDTO.getDescription() );

			person = personRepository.save( person );
			mv.addAttribute( "msgSuccess", "User successfully updated!" );
		}

		return "edit";
	}

	@DeleteMapping( "/{username}" )
	public String delete( @PathVariable( "username" ) String username, RedirectAttributes attributes ) {
		Person person = personRepository.findByUsername( username );
		personRepository.delete( person );

		attributes.addFlashAttribute( "msgSuccess", "The user was deleted!" );
		return "redirect:/people";
	}

}
