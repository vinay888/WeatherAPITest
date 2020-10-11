package APIClasses;

import java.util.HashMap;

import Resources.HTTP_METHODS;

public interface IApiBuilder
{
	//public void buildAPIParameters();
	
	/**
	 * returns HTTP_Method type for the API 
	 */
	public HTTP_METHODS getHttpMethod();

	/**
	 * returns Base_URI for the API 
	 */
	public String getEndpoint();
	
	/**
	 * returns Base_PATH for the API 
	 */
	public String getBasePath();

	/**
	 * returns path parameter map for the API 
	 */
	public HashMap<String, String> getPathParams();

	/**
	 * returns query parameter map for the API 
	 */
	public HashMap<String, String> getQueryParams();

	/**
	 * returns headers map for the API 
	 */
	public HashMap<String, String> getHeaders();

	/**
	 * returns String body for the post API 
	 */
	public String getBodyString();
}
