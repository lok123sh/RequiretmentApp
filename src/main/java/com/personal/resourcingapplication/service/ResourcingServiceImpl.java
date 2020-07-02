package com.personal.resourcingapplication.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.resourcingapplication.common.AlreadyExistsException;
import com.personal.resourcingapplication.common.AppConstants;
import com.personal.resourcingapplication.common.NotExistException;
import com.personal.resourcingapplication.dao.ResourcingDao;
import com.personal.resourcingapplication.entity.ApplicationEntity;
import com.personal.resourcingapplication.entity.OfferEntity;

@Service
public class ResourcingServiceImpl implements ResourcingService{
	private static final List<String> validStatus = Arrays.asList("APPLIED","INVITED","REJECTED","HIRED");

	@Override
	public void createOffer(OfferEntity entity) {
		OfferEntity offerEntity = ResourcingDao.saveOffer(entity);
		if(offerEntity!=null) {
			throw new AlreadyExistsException(AppConstants.JOB_ALREADY_EXISTS);
		}
	}

	@Override
	public OfferEntity getOfferByName(String offerName) {
		OfferEntity offerEntity = ResourcingDao.getOfferByName(offerName);
		if(offerEntity==null) {
			throw new NotExistException(AppConstants.JOB_NOT_EXISTS);
		}
		return offerEntity;
	}

	@Override
	public List<OfferEntity> getOfferEntities() {
		return ResourcingDao.getOffers();
	}

	@Override
	public void applyApplication(ApplicationEntity entity) {
		if(ResourcingDao.getOfferByName(entity.getOffer())==null) {
			throw new NotExistException(AppConstants.APPLICATION_NOT_EXISTS);
		}
		if(!validStatus.contains(entity.getApplicationStatus())) {
			throw new RuntimeException(AppConstants.INVALID_STATUS+validStatus);
		}
		ApplicationEntity applicationEntity = ResourcingDao.saveApplication(entity);
		if(applicationEntity!=null) {
			throw new AlreadyExistsException(AppConstants.APPLICATION_ALREADY_APPLIED);
		}
	}

	@Override
	public List<ApplicationEntity> getApplicationByOfferName(String offerName) {
		List<ApplicationEntity> entity= ResourcingDao.getApplicationByJobOffer(offerName);
		if(entity.isEmpty()) {
			throw new NotExistException(AppConstants.NO_APPLICATION_FOR_OFFER+offerName);
		}
		return entity;
	}

	@Override
	public List<ApplicationEntity> getApplicationByUser(String email) {
		List<ApplicationEntity> entity= ResourcingDao.getApplicationByUser(email);
		if(entity.isEmpty()) {
			throw new NotExistException(AppConstants.NO_APPLICATION_FOR_USER);
		}
		return entity;
	}

}
