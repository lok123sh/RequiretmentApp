package com.personal.resourcingapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.resourcingapplication.entity.OfferEntity;
import com.personal.resourcingapplication.service.ResourcingService;

@RestController
@Validated
@RequestMapping("/offer")
public class OfferController {
	
	@Autowired ResourcingService resourcingService;
	
	@GetMapping("/{offerName}")
	public ResponseEntity<OfferEntity> getAllOfferById(@PathVariable("offerName") String offerName){
		OfferEntity entity = resourcingService.getOfferByName(offerName);
		return new ResponseEntity<>(entity,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<OfferEntity>> getAllOffers(){
		List<OfferEntity> offerEntitities = resourcingService.getOfferEntities();
		return new ResponseEntity<List<OfferEntity>>(offerEntitities, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity saveOffer(@Valid @RequestBody OfferEntity entity) {
		 resourcingService.createOffer(entity);
		 return new ResponseEntity(HttpStatus.CREATED);
	}

}
