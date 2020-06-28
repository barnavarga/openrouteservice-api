package com.barnavarga.openrouteservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
@Getter
@AllArgsConstructor
public enum ErrorType
{
	INCORRECT_REQUEST(400, "The request is incorrect and therefore can not be processed."),
	UNAUTHORIZED(401, "Unauthorized"),
	FORBIDDEN(403, "Forbidden"),
	ERROR_IN_REQUEST(404, "An element could not be found. If possible, a more detailed error code is provided."),
	NOT_SUPPORTED_HTTP_METHOD(405, "The specified HTTP method is not supported. For more details, refer to the EndPoint documentation."),
	TOO_BIG_REQUEST(413, "The request is larger than the server is able to process, the data provided in the request exceeds the capacity limit."),
	UNEXPECTED_ERROR(500, "An unexpected error was encountered and a more detailed error code is provided."),
	NOT_SUPPORTED_FUNCTIONALITY(501, "Indicates that the server does not support the functionality needed to fulfill the request."),
	UNAVAILABLE_SERVER(503, "The server is currently unavailable due to overload or maintenance.");

	private int statusCode;
	private String message;
}
