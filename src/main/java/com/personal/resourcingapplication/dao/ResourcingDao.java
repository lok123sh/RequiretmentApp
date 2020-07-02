package com.personal.resourcingapplication.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.personal.resourcingapplication.entity.ApplicationEntity;
import com.personal.resourcingapplication.entity.OfferEntity;

@Repository
public class ResourcingDao {
	private static Map<String,OfferEntity> offerMap = new HashMap<>();
	private static Map<String,ApplicationEntity> applicationMap = new HashMap<>();
	
	public static OfferEntity saveOffer(OfferEntity offer) {
		return offerMap.put(offer.getJobTitle(), offer);
	}
	
	public static OfferEntity getOfferByName(String offerName) {
		return offerMap.get(offerName);
	}
	
	public static List<OfferEntity> getOffers() {
		return offerMap.values().stream().collect(Collectors.toList());
	}

	public static ApplicationEntity saveApplication(ApplicationEntity entity) {
		String applicationId = createUniqueId(entity.getOffer(),entity.getEmail());
		ApplicationEntity applicationEntity =  applicationMap.put(applicationId, entity);
		if(applicationEntity==null) {
			String offerName = entity.getOffer();
			OfferEntity offerEntity = offerMap.get(offerName);
			offerEntity.setNumberOfApplication(offerEntity.getNumberOfApplication()+1);
			offerMap.remove(offerName);
			offerMap.put(offerName, offerEntity);
		}
		return applicationEntity;
	}

	public static List<ApplicationEntity> getApplicationByJobOffer(String offerName) {
		return applicationMap.values().stream().filter(application->application.getOffer().equals(offerName))
				.collect(Collectors.toList());
	}

	public static List<ApplicationEntity> getApplicationByUser(String email) {
		return applicationMap.values().stream().filter(application->application.getEmail().equals(email))
				.collect(Collectors.toList());
	}
	
	private static String createUniqueId(String offerName,String emailName) {
		return emailName+"::"+offerName;
	}

}
