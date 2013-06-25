/*******************************************************************************
 * Copyright (c) 2012 Rushan R. Gilmullin and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Rushan R. Gilmullin - initial API and implementation
 *******************************************************************************/

package org.lunifera.examples.vaaclipse.demo1.e4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ecf.core.ContainerCreateException;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.core.IContainerManager;
import org.eclipse.ecf.remoteservice.IRemoteServiceContainerAdapter;
import org.eclipse.ecf.remoteservice.IRemoteServiceRegistration;
import org.lunifera.examples.ecview.api.service.IECViewModelService;
import org.lunifera.examples.vaaclipse.demo1.e4.user.UserCounter;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Demo1Activator implements BundleActivator {
	private static Demo1Activator instance;

	public static BundleContext context;
	private File demoHome;
	private File srcStore;

	private UserCounter userCounter;
	private Date startTime;

	private ServiceTracker containerManagerServiceTracker;
	private IContainer container;
	private IRemoteServiceRegistration serviceRegistration;

	private ECViewModelRemoteService ecViewModelService;

	public void start(BundleContext context) throws Exception {
		instance = this;
		Demo1Activator.context = context;

		upackProjects();

		this.userCounter = new UserCounter();
		this.startTime = new Date();

		registerRemoteService();
	}

	/**
	 * Registers the remote service.
	 * 
	 * @throws ContainerCreateException
	 */
	private void registerRemoteService() throws ContainerCreateException {
		// Create R-OSGi Container
		IContainerManager containerManager = getContainerManagerService();
		container = containerManager.getContainerFactory().createContainer(
				"ecf.r_osgi.peer");
		// Get remote service container adapter
		IRemoteServiceContainerAdapter containerAdapter = (IRemoteServiceContainerAdapter) container
				.getAdapter(IRemoteServiceContainerAdapter.class);
		// Register remote service

		ecViewModelService = new ECViewModelRemoteService();
		serviceRegistration = containerAdapter.registerRemoteService(
				new String[] { IECViewModelService.class.getName() },
				ecViewModelService, null);
		System.out.println("IECViewModel RemoteService registered");
	}

	/**
	 * Delegate may register.
	 * 
	 * @param delegate
	 */
	public void register(IECViewModelService delegate) {
		ecViewModelService.addDelegate(delegate);
	}

	private void upackProjects() throws Exception {
		demoHome = FileUtils.getFile(FileUtils.getUserDirectory(),
				"/.vaaclipsedemo");

		if (!demoHome.isFile()) // prevent that there are user file with this
								// name
		{
			if (!demoHome.exists())
				demoHome.mkdir();

			Bundle resourcesBundle = Platform
					.getBundle("org.lunifera.examples.vaaclipse.demo1.e4");

			// create the src root with unique name (to prevent the intersection
			// with the user data in case he have own folder .cassandra in user
			// home)
			File srcRoot = FileUtils.getFile(demoHome,
					"63048fd1-69d0-4eb8-be75-bb33964f821c");
			String version = resourcesBundle.getVersion().toString();
			srcStore = FileUtils.getFile(srcRoot, version);

			if (!srcStore.exists()) {
				srcStore.mkdir();

				URL url = resourcesBundle
						.getEntry("filesystem/filesystem_to_unzip.zip");
				InputStream inputStream = url.openConnection().getInputStream();
				try {
					extractFolder(srcStore, inputStream);
				} finally {
					inputStream.close();
				}
			}
		} else
			throw new RuntimeException(
					"There is the file with name .cassandra in user home");
	}

	public static void extractFolder(File destPath, InputStream inputStream)
			throws ZipException, IOException {
		int BUFFER = 2048;
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(
				inputStream));

		ZipEntry entry;
		// Process each entry
		while ((entry = zis.getNextEntry()) != null) {
			// grab a zip file entry
			String currentEntry = entry.getName();
			File destCatalog = new File(destPath, currentEntry);
			// destFile = new File(newPath, destFile.getName());
			File destinationParent = destCatalog.getParentFile();

			// create the parent directory structure if needed
			destinationParent.mkdirs();

			if (!entry.isDirectory()) {
				int count;
				byte data[] = new byte[BUFFER];
				// write the files to the disk
				FileOutputStream fos = new FileOutputStream(destCatalog);
				BufferedOutputStream dest = new BufferedOutputStream(fos,
						BUFFER);
				while ((count = zis.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}
		}
	}

	/**
	 * Returns the image with the given name.
	 * 
	 * @param image
	 * @return
	 */
	public static File findImage(String image) {
		URL url = context.getBundle().getEntry("img/" + image);
		InputStream inputStream = null;
		try {
			File result = new File(FileUtils.getUserDirectoryPath() + "/"
					+ UUID.randomUUID().toString());
			inputStream = url.openConnection().getInputStream();
			FileOutputStream out = new FileOutputStream(result);
			while (true) {
				int value = inputStream.read();
				if (value == -1) {
					break;
				}
				out.write(value);
			}

			out.close();
			inputStream.close();

			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	public void stop(BundleContext context) throws Exception {
		this.context = null;
		instance = null;

		if (container != null) {
			container.disconnect();
			container = null;
		}
		if (containerManagerServiceTracker != null) {
			containerManagerServiceTracker.close();
			containerManagerServiceTracker = null;
		}

		if (serviceRegistration != null) {
			serviceRegistration.unregister();
		}
		this.context = null;
	}

	private IContainerManager getContainerManagerService() {
		if (containerManagerServiceTracker == null) {
			containerManagerServiceTracker = new ServiceTracker(context,
					IContainerManager.class.getName(), null);
			containerManagerServiceTracker.open();
		}
		return (IContainerManager) containerManagerServiceTracker.getService();
	}

	public static Demo1Activator getInstance() {
		return instance;
	}

	public BundleContext getBundleContext() {
		return context;
	}

	public File getHomeDirectory() {
		return demoHome;
	}

	public File getSrcStore() {
		return srcStore;
	}

	public UserCounter getUserCounter() {
		return userCounter;
	}

	public Date getStartTime() {
		return startTime;
	}
}
