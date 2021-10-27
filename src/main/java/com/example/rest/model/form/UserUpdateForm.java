package com.example.rest.model.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserUpdateForm {
	
	@NotBlank(message ="Nome não pode estar em branco")
	private String name;
	
	@Email(message = "Endereço de e-mail inválido")
	private String email;

}
