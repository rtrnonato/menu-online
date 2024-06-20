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
		return user.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
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
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return userRepository.save(newObj);
	}
	
	public void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setPhone(obj.getPhone());
		newObj.setPassword(obj.getPassword());
	}
}