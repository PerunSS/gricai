package com.gricai.central.server.nio.impl;

import com.gricai.central.server.nio.InputHandler;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: Mar 18, 2007
 * Time: 5:47:51 PM
 */
public interface InputHandlerFactory
{
	public InputHandler newHandler() throws IllegalAccessException, InstantiationException;
}
