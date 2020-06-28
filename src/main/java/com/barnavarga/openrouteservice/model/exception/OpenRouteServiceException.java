package com.barnavarga.openrouteservice.model.exception;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
public abstract class OpenRouteServiceException extends Exception
{
	public OpenRouteServiceException(final String message)
	{
		super(message);
	}

	public OpenRouteServiceException(final String message, final Throwable cause)
	{
		super(message, cause);
	}
}
