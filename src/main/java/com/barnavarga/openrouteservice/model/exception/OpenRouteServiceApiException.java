package com.barnavarga.openrouteservice.model.exception;

import com.barnavarga.openrouteservice.model.enums.ErrorType;
import lombok.Getter;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
@Getter
public class OpenRouteServiceApiException extends OpenRouteServiceException
{
	private ErrorType errorType;

	public OpenRouteServiceApiException(final ErrorType errorType)
	{
		super(errorType.getMessage());
		this.errorType = errorType;
	}

	public OpenRouteServiceApiException(final ErrorType errorType, final String message)
	{
		super(message);
		this.errorType = errorType;
	}

	public OpenRouteServiceApiException(final ErrorType errorType, final String message, final Throwable cause)
	{
		super(message, cause);
		this.errorType = errorType;
	}
}
