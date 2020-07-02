package com.personal.resourcingapplication.service;

import java.util.List;

import com.personal.resourcingapplication.entity.ApplicationEntity;
import com.personal.resourcingapplication.entity.OfferEntity;

public interface ResourcingService {

	public void createOffer(OfferEntity entity);
	public OfferEntity getOfferByName(String offerName);
	public List<OfferEntity> getOfferEntities();
	public void applyApplication(ApplicationEntity entity);
	public List<ApplicationEntity> getApplicationByOfferName(String offerName);
	public List<ApplicationEntity> getApplicationByUser(String email);

}
