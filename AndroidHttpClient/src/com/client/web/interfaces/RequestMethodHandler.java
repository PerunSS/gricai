package com.client.web.interfaces;

import com.client.web.beans.HTTPConnectionPostRequest;

public interface RequestMethodHandler
{
	public String doPost(HTTPConnectionPostRequest req);
	public String doGet();
}
