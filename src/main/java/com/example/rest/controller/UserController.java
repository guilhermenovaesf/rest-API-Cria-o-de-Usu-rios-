package com.example.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.model.dto.UserDto;
import com.example.rest.model.form.UserForm;
import com.example.rest.model.form.UserUpdateForm;
import com.example.rest.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
@Autowired
UserService userService;
	
@RequestMapping(method= RequestMethod.GET)
public List<UserDto> list(){
	return userService.findAllUsers();
}

@RequestMapping(value="/{id}",method=RequestMethod.GET)
public UserDto findById(@PathVariable(value="id")Long id) {
	return userService.findUserById(id);
}

@RequestMapping(method= RequestMethod.POST)
@ResponseStatus(code =HttpStatus.CREATED)
public UserDto register(@RequestBody @Valid UserForm userForm) {
	return userService.createUser(userForm);
}
@RequestMapping(value="/{id}",method=RequestMethod.PUT)
public UserDto updateById(@RequestBody UserUpdateForm form,@PathVariable(value="id") Long id) {
	return userService.updateById(form, id);
}

@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
public void deleteById(@PathVariable(value="id") Long id) {
	userService.deleteById(id);
}
}
