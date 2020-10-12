package APIClasses;

import java.util.HashMap;

import com.relevantcodes.extentreports.LogStatus;

import UtilityClasses.ExtentReportListner;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIHandler
{
	/**
	 * Method to return the response for the API
	 * @param builder - IApiBuilder object for the specific calling API 
	 * @return - response for the HTTP request of given API
	 */
	public static Response getRequestResponse(IApiBuilder builder)
	{
		Response response = null;
		try
		{
			HashMap<String, String> headers = new HashMap<String, String>();
			HashMap<String, String> pathParams = new HashMap<String, String>();
			HashMap<String, String> queryParams = new HashMap<String, String>();
			String baseURI = new String();
			String basePath = new String();
			String body = new String();
			
			// assign values
			baseURI = builder.getEndpoint();
			basePath = builder.getBasePath();
			headers = builder.getHeaders();
			pathParams = builder.getPathParams();
			queryParams = builder.getQueryParams();
			body = builder.getBodyString();
			
			// if base URI is null exit
			if(baseURI.equals("") || baseURI == null)
				return null;
			
			// Set request params like : Endpoint, basePath, params, headers
			RestAssured.baseURI = baseURI + ((basePath != null) ? basePath : "");			
			RequestSpecification request = RestAssured.given();
			
			if(headers != null && headers.size() > 0)
				request.headers(headers);
			if(pathParams != null && pathParams.size() > 0)
				request.pathParams(pathParams);
			if(queryParams != null && queryParams.size() > 0)
				request.params(queryParams);
			if(body != null && !body.equals("") && body.length() > 0)
				request.body(body); // can be sent in POJO or String or JASON format
			
			// get request response
			switch(builder.getHttpMethod())
			{
				case GET :
						response = request.get();
						break;
				case POST : 
						response = request.post();
						break;
				case PUT :
						response = request.put();
						break;
				case DELETE :
						response = request.delete();
						break;
			}
		}
		catch (Exception e)
		{
			ExtentReportListner.test.log(LogStatus.FATAL, e.getStackTrace().toString());
		}
		
		return response;
	}
}
