package com.gricai.central.server.nioMark2.impl;

import com.gricai.central.server.nioMark2.InputHandler;

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
