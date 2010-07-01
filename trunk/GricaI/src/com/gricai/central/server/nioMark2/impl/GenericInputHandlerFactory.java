package com.gricai.central.server.nioMark2.impl;

import com.gricai.central.server.nioMark2.InputHandler;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: Mar 18, 2007
 * Time: 5:57:45 PM
 */
public class GenericInputHandlerFactory implements InputHandlerFactory
{
	private final Class<? extends InputHandler> handlerClass;

	public GenericInputHandlerFactory (Class<? extends InputHandler> handlerClass)
	{
		this.handlerClass = handlerClass;
	}
	@Override
	public InputHandler newHandler() throws IllegalAccessException, InstantiationException
	{
		return handlerClass.newInstance();
	}
}
