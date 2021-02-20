package com.fire.assessment.service.exception;


public class PersonException extends RuntimeException
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
	public PersonException(String message)
	{
		super(message);
	}
	
	public PersonException(Exception e)
	{
		super(e);
	}

}
