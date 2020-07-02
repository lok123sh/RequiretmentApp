package com.personal.resourcingapplication.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ApplicationEntity {
	@NotBlank(message = "offer can not be empty or Null")
	private String offer;
	@NotBlank(message= "email can not be empty or Null")
	@Email(message = "email is not valid")
	private String email;
	@NotBlank(message = "resume can not be empty or Null")
	private String resume;
	@NotBlank(message = "applicationStatus can not be empty or Null")
	private String applicationStatus;
}
