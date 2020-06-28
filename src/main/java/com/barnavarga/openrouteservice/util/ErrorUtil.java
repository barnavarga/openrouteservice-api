package com.barnavarga.openrouteservice.util;

import com.barnavarga.openrouteservice.model.enums.ErrorType;

import java.util.Arrays;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
public class ErrorUtil
{
	public static ErrorType findErrorTypeByStatusCode(final int statusCode)
	{
		return Arrays.stream(ErrorType.values()).filter(et -> et.getStatusCode() == statusCode).findFirst().orElse(ErrorType.UNEXPECTED_ERROR);
	}
}
