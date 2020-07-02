package com.personal.resourcingapplication.common;

public class AlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 6849269830787211350L;

	public AlreadyExistsException(String message) {
		super(message);
	}

}
