package com.example.rest.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data //lombok colocando todos os meus getters e setters
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NAME",nullable = false)
	@Size(max=50)
	private String name;
	
	@Column(name="EMAIL",length=50, nullable = false, unique=true)
	private String email;
	
	@Column(name="CPF",length=11, nullable = false, unique=true)
	private String cpf;
	
	@Column(name="BIRTHDATE",nullable=false)
	private LocalDate data;
	

	
}
