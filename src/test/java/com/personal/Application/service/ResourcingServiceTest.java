package com.personal.Application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.personal.resourcingapplication.common.AlreadyExistsException;
import com.personal.resourcingapplication.common.NotExistException;
import com.personal.resourcingapplication.entity.ApplicationEntity;
import com.personal.resourcingapplication.entity.OfferEntity;
import com.personal.resourcingapplication.service.ResourcingServiceImpl;

public class ResourcingServiceTest {
	
	@InjectMocks ResourcingServiceImpl resourcingService;
	
	@BeforeEach
	  void setUp() {
	    MockitoAnnotations.initMocks(this);
	  }
	
	@Test
	public void test_createOffer_success() {
		OfferEntity entity = new OfferEntity();
		entity.setJobTitle("developer");
		resourcingService.createOffer(entity);
		assertEquals(0, entity.getNumberOfApplication());
	}
	
	@Test
	public void test_createOffer_Duplicate() {
		test_createOffer_success();
		OfferEntity entity = new OfferEntity();
		entity.setJobTitle("developer");
		assertThrows(AlreadyExistsException.class, ()->resourcingService.createOffer(entity));
	}
	
	@Test
	public void test_getAllOffer() {
		test_createOffer_success();
		List<OfferEntity> offerEntity = resourcingService.getOfferEntities();
		assertNotNull(offerEntity.get(0));
	}
	
	@Test
	public void test_getOfferByName_success() {
		test_createOffer_success();
		OfferEntity entity = resourcingService.getOfferByName("developer");
		assertNotNull(entity);
	}
	
	@Test
	public void test_getOfferByName_NotExists() {
		assertThrows(NotExistException.class, ()->resourcingService.getOfferByName("developer"));
	}
	
	@Test
	public void test_applyApplication_success() {
		test_createOffer_success();
		ApplicationEntity entity = getDummyApplication();
		resourcingService.applyApplication(entity);
		OfferEntity offerEntity = resourcingService.getOfferByName("developer");
		assertEquals(1, offerEntity.getNumberOfApplication());
	}
	@Test
	public void test_applyApplication_AlreadyExistsException() {
		test_applyApplication_success();
		ApplicationEntity entity = getDummyApplication();
		assertThrows(AlreadyExistsException.class, ()->resourcingService.applyApplication(entity));
	}
	@Test
	public void test_applyApplication_InvalidStstus() {
		test_createOffer_success();
		ApplicationEntity entity = getDummyApplication();
		entity.setApplicationStatus("INVALID");
		assertThrows(RuntimeException.class, ()->resourcingService.applyApplication(entity));
	}
	
	@Test
	public void test_getApplicationByOfferName_success() {
		test_applyApplication_success();
		List<ApplicationEntity> entity =  resourcingService.getApplicationByOfferName("developer");
		assertNotNull(entity.get(0));
		
	}
	@Test
	public void test_getApplicationByUser_success() {
		test_applyApplication_success();
		List<ApplicationEntity> entity =  resourcingService.getApplicationByUser("lokesh@gmail.com");
		assertNotNull(entity.get(0));
		
	}

	private ApplicationEntity getDummyApplication() {
		ApplicationEntity entity = new ApplicationEntity();
		entity.setApplicationStatus("APPLIED");
		entity.setEmail("lokesh@gmail.com");
		entity.setOffer("developer");
		entity.setResume("I am a developer");
		return entity;
	}
}
