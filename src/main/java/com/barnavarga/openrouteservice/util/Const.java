package com.barnavarga.openrouteservice.util;

import com.barnavarga.openrouteservice.model.response.Pair;
import okhttp3.MediaType;

/**
 * @author bvarga and created on 2020. 06. 28..
 */
public class Const
{
	public static class Header
	{
		public static final String AUTHORIZATION = "Authorization";
		public static final Pair<String, String> CONTENT_TYPE = new Pair<>("Content-Type", "application/json; charset=utf-8");
	}

	public static class Request
	{
		public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
		public static final String QUERY_PARAMETER_API_KEY = "api_key";
	}

	public static class Connection
	{
		public static final int CONNECTION_TIMEOUT = 30;
		public static final int READ_TIMEOUT = 60;
		public static final int WRITE_TIMEOUT = 60;
	}
}
