package com.client.web.interfaces;

import org.apache.http.HttpResponse;

public interface ExecutableHttpRequest {
	
	public HttpResponse executeRequest(String requestID,String resourceID,String applicationID, String clientID,String resourceTypeID,String relativeResourceLocation);
										
}
