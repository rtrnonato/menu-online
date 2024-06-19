package com.rtrnonato.menuonline.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rtrnonato.menuonline.domain.User;
import com.rtrnonato.menuonline.dto.UserDTO;
import com.rtrnonato.menuonline.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
	 List<User> users = userService.findAll();
	 List<UserDTO> usersDTO = users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	 return ResponseEntity.ok().body(usersDTO);
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = userService.findById(id);
	    return ResponseEntity.ok().body(new UserDTO(obj));
	}
}