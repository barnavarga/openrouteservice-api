package com.barnavarga.openrouteservice.model.exception;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
public class OpenRouteServiceProcessException extends OpenRouteServiceException
{
	public OpenRouteServiceProcessException(final String message)
	{
		super(message);
	}

	public OpenRouteServiceProcessException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
