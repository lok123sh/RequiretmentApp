package com.personal.resourcingapplication.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OfferEntity {
	@NotBlank(message = "jobTitle can not be empty or Null")
	private String jobTitle;
	private String startDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	private int numberOfApplication=0;
}
