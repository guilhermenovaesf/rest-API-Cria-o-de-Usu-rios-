package com.example.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest.model.User;
import com.example.rest.model.dto.UserDto;
import com.example.rest.model.form.UserForm;
import com.example.rest.model.form.UserUpdateForm;
import com.example.rest.repository.UserRepository;
//MEU BUSINESSS NO BEEHOME

@Service
public class UserService {

	@Autowired
	UserRepository  userRepository;
	
	public UserDto createUser (UserForm form) {
		User user = convertToBusiness(form);
		user =userRepository.save(user);
		return convertToDto(user);
				
	}
	
	public List<UserDto> findAllUsers(){
		List<User> all =userRepository.findAll();
		return convertListToDto(all);
	}
	
	public UserDto findUserById(Long id) {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			return convertToDto(optional.get());		
		}
		return null;
	}
	
	private User convertToBusiness(UserForm form) {
		User user =new User();
		user.setName(form.getName());
		user.setEmail(form.getEmail());
		user.setCpf(form.getCpf());
		user.setData(form.getBirthDate());
		return user;
	}
	private UserDto convertToDto(User user){
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setCpf(user.getCpf());
		dto.setBirthDate(user.getData());
		return dto;
	}
	
	private static List<UserDto> convertListToDto(List<User>users){
		return users.stream().map(UserDto::new).collect(Collectors.toList());
	}
	
	//vou precisar criar uma nova dto(no caso um form), pois só vou permitir a atualização do nome e do email, não do cpf nem data de nascimento
	public UserDto updateById(UserUpdateForm form, Long id) {
		Optional<User> op = userRepository.findById(id);
		
		if(op.isPresent()) {
			User obj =op.get();
			if(form.getName() != null) {
				obj.setName(form.getName());
			}
			if(form.getEmail()!= null) {
				obj.setEmail(form.getEmail());
			}
			userRepository.save(obj);
			return convertToDto(obj);
		}
		return null;
	}
	
	public void deleteById(Long id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
		}
	}
}
