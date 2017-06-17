package io.redspark.controller;

import static io.redspark.controller.ControllerConstants.USER;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.redspark.controller.dto.UserDTO;
import io.redspark.domain.User;
import io.redspark.exception.WebException;
import io.redspark.repository.UserRepository;

@RestController
@RequestMapping(value=USER)
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/{id}", method=GET)
	public UserDTO get(@PathVariable("id") final Long id){
		User user = userRepository.findOne(id);
		
		if(user == null){
			throw new WebException(HttpStatus.NOT_FOUND, "user.not.found");
		}
		
		return new UserDTO(user);
	}
	
}
