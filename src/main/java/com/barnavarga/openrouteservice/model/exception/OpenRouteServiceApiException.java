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
	private String description;

	public OpenRouteServiceApiException(final ErrorType errorType, final String description)
	{
		super(errorType.getMessage() + "\n" + description);
		this.errorType = errorType;
		this.description = description;
	}

	public OpenRouteServiceApiException(final ErrorType errorType, final String message, final String description)
	{
		super(message + "\n" + description);
		this.errorType = errorType;
		this.description = description;
	}

	public OpenRouteServiceApiException(final ErrorType errorType, final String message, final String description, final Throwable cause)
	{
		super(message + "\n" + description, cause);
		this.errorType = errorType;
		this.description = description;
	}
}
