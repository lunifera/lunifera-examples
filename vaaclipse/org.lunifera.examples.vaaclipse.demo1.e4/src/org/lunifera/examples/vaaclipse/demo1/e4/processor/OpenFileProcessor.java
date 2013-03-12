/**
 * 
 */
package org.lunifera.examples.vaaclipse.demo1.e4.processor;

import org.lunifera.examples.vaaclipse.demo1.e4.Demo1Activator;
import org.lunifera.examples.vaaclipse.demo1.e4.constants.Demo1Constants;

import org.apache.commons.io.FileUtils;
import java.io.File;
import javax.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.extensions.EventUtils;
import org.eclipse.e4.ui.model.application.ui.basic.MInputPart;
import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.semanticsoft.e4extension.service.EPartServiceExt;

/**
 * @author rushan
 *
 */
public class OpenFileProcessor
{
	private static final String fs = System.getProperty("file.separator");
	
	@Inject
	IEclipseContext context;
	
	@Inject
	EventBroker eventBroker;
	
	@Inject
	EPartServiceExt partServiceExt;
	
	private EventHandler openFileHandler = new EventHandler() {

		public void handleEvent(Event event)
		{
			File file = (File) event.getProperty(EventUtils.DATA);
			openFile(file);
		}
	};
	
	private void openFile(File file)
	{
		String path = file.getAbsolutePath();
		MInputPart part = partServiceExt.openUri(path);
		int lastLsIndex = path.lastIndexOf(fs);
		if (lastLsIndex > 0)
			part.setLabel(path.substring(lastLsIndex + 1));	
		
		String pathStr = path;
		int projectTreeRootIndex = path.indexOf(".vaaclipsedemo");
		if (projectTreeRootIndex > -1)
		{
			int i = path.indexOf(fs, projectTreeRootIndex);
			pathStr = projectTreeRootIndex > -1 ? path.substring(i + 1) : path;
		}
		
		eventBroker.send(Demo1Constants.CONSOLE_LOG, "Open file: " + pathStr);
	}
	
	@Execute
	public void execute()
	{
		eventBroker.subscribe(Demo1Constants.OPEN_FILE, openFileHandler);
		
		File srcStore = Demo1Activator.getInstance().getSrcStore();
		String projectName = "org.lunifera.examples.vaaclipse.demo1.e4";
//		File welcome = FileUtils.getFile(srcStore, projectName, "data", "Welcome.html");
//		openFile(welcome);
	}
}
