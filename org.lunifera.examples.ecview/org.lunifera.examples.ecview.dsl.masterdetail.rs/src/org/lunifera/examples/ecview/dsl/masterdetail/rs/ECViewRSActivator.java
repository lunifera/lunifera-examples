package org.lunifera.examples.ecview.dsl.masterdetail.rs;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.eclipse.ecf.core.ContainerConnectException;
import org.eclipse.ecf.core.ContainerCreateException;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.core.IContainerManager;
import org.eclipse.ecf.core.identity.IDCreateException;
import org.eclipse.ecf.core.identity.IDFactory;
import org.eclipse.ecf.core.util.ECFException;
import org.eclipse.ecf.remoteservice.IRemoteService;
import org.eclipse.ecf.remoteservice.IRemoteServiceContainerAdapter;
import org.eclipse.ecf.remoteservice.IRemoteServiceReference;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecp.ecview.common.model.core.YView;
import org.eclipse.emf.ecp.ecview.extension.model.extension.YTextField;
import org.lunifera.examples.ecview.api.service.IECViewModelService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.util.tracker.ServiceTracker;

public class ECViewRSActivator implements BundleActivator {

	private static BundleContext context;
	private static ECViewRSActivator INSTANCE;

	public static final String ROSGI_SERVICE_HOST = "r-osgi://localhost:9278";

	private ServiceTracker containerManagerServiceTracker;
	private IContainer container;

	public static BundleContext getContext() {
		return context;
	}

	public static ECViewRSActivator getInstance() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		ECViewRSActivator.context = bundleContext;
		ECViewRSActivator.INSTANCE = this;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		ECViewRSActivator.context = null;
		ECViewRSActivator.INSTANCE = null;
		if (container != null) {
			container.disconnect();
			container = null;
		}
		if (containerManagerServiceTracker != null) {
			containerManagerServiceTracker.close();
			containerManagerServiceTracker = null;
		}
	}

	@SuppressWarnings("unchecked")
	private IContainerManager getContainerManagerService() {
		if (containerManagerServiceTracker == null) {
			containerManagerServiceTracker = new ServiceTracker(context,
					IContainerManager.class.getName(), null);
			containerManagerServiceTracker.open();
		}
		return (IContainerManager) containerManagerServiceTracker.getService();
	}

	public void setModel(YView view) {
		try {
			IContainerManager containerManager = getContainerManagerService();
			container = containerManager.getContainerFactory().createContainer(
					"ecf.r_osgi.peer");
			IRemoteServiceContainerAdapter containerAdapter = (IRemoteServiceContainerAdapter) container
					.getAdapter(IRemoteServiceContainerAdapter.class);
			IRemoteServiceReference[] helloReferences = containerAdapter
					.getRemoteServiceReferences(
							IDFactory.getDefault().createID(
									container.getConnectNamespace(),
									ROSGI_SERVICE_HOST),
							IECViewModelService.class.getName(), null);
			if (helloReferences == null) {
				System.err
						.println("Remoteservice IECViewService NOT available!");
			}
			// 4. Get remote service for reference
			IRemoteService remoteService = containerAdapter
					.getRemoteService(helloReferences[0]);
			// 5. Get the proxy
			IECViewModelService proxy = (IECViewModelService) remoteService
					.getProxy();

			try {
				ByteArrayOutputStream fos = new ByteArrayOutputStream();
				ResourceSet rs = new ResourceSetImpl();
				Resource resource = rs
						.createResource(URI.createFileURI("test"));
				resource.getContents().add((EObject) view);

				for (Iterator<EObject> iterator = EcoreUtil.getAllContents(
						(EObject) view, true); iterator.hasNext();) {
					EObject child = iterator.next();
					if (child instanceof YTextField) {
						YTextField field = (YTextField) child;
						if (field.getDatadescription() != null) {
							resource.getContents().add(
									(EObject) field.getDatadescription());
						}
					}
				}

				resource.save(fos, null);

				String content = fos.toString();
				proxy.setModel(content);
				fos.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (ContainerCreateException e) {
			e.printStackTrace();
		} catch (ContainerConnectException e) {
			e.printStackTrace();
		} catch (IDCreateException e) {
			e.printStackTrace();
		} catch (ECFException e) {
			e.printStackTrace();
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}

	}
}
