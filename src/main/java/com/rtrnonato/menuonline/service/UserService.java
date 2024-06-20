package com.rtrnonato.menuonline.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rtrnonato.menuonline.domain.User;
import com.rtrnonato.menuonline.dto.UserDTO;
import com.rtrnonato.menuonline.repository.UserRepository;
import com.rtrnonato.menuonline.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();	
	}
	
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id);
		if (user == null) {
			throw new ObjectNotFoundException("object not found");		
		}
		 
		if (!user.isPresent()) {
	        throw new ObjectNotFoundException("Object not found");
	    }
		return user.get();
	}
	
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getPhone(), objDto.getPassword());
	}
	
	public void delete(String id) {
		findById(id);	
		userRepository.deleteById(id);
	}
}