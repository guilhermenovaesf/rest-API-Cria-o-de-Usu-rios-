package com.example.rest.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.rest.model.dto.ErrorFormDto;

@RestControllerAdvice
public class ErrorHandler {

	@Autowired
	private MessageSource messageSource;
	
	//errorFormDto tem a funçao de representar o erro de formulário.
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorFormDto> handle(MethodArgumentNotValidException exception){
		List<ErrorFormDto> dto =new ArrayList<>();
		
		List<FieldError> fieldErrors =exception.getBindingResult().getFieldErrors(); //obtendo erros que acontecem na requisição
		fieldErrors.forEach(e -> {
			String errorMessage = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorFormDto error = new ErrorFormDto(e.getField(), errorMessage);
			dto.add(error);
		});
		return dto;
		
		
	}
}
