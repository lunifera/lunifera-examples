/**
 * 
 */
package org.lunifera.examples.vaaclipse.demo1.e4.processor;

import org.lunifera.examples.vaaclipse.demo1.e4.Demo1Activator;

import org.eclipse.e4.core.di.annotations.Execute;

/**
 * @author rushan
 *
 */
public class UsersCountProcessor
{
	@Execute
	public void incUserCount()
	{
		Demo1Activator.getInstance().getUserCounter().increment();
	}
}
