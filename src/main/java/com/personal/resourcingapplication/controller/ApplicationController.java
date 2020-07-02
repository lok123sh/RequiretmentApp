package com.personal.resourcingapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.resourcingapplication.entity.ApplicationEntity;
import com.personal.resourcingapplication.service.ResourcingService;

@RestController
@RequestMapping("/application")
public class ApplicationController {
	@Autowired ResourcingService resourceService;

	@PostMapping
	public ResponseEntity saveApplication(@Valid @RequestBody ApplicationEntity entity) {
		resourceService.applyApplication(entity);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	@GetMapping("/byUser/{email}")
	public ResponseEntity<List<ApplicationEntity>> getApplicationByUser(@PathVariable("email") String email){
		List<ApplicationEntity> entity = resourceService.getApplicationByUser(email);
		return new ResponseEntity<>(entity,HttpStatus.OK);
	}
	
	@GetMapping("/byOffer/{jobOffer}")
	public ResponseEntity<List<ApplicationEntity>> getApplicationByJobOffer(@PathVariable("jobOffer") String jobOffer){
		List<ApplicationEntity> entity = resourceService.getApplicationByOfferName(jobOffer);
		return new ResponseEntity<>(entity,HttpStatus.OK);
	}
}
