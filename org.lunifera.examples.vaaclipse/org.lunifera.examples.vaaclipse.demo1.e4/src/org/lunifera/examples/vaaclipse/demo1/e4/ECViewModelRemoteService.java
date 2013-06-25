package org.lunifera.examples.vaaclipse.demo1.e4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecp.ecview.common.model.core.YView;
import org.lunifera.examples.ecview.api.service.IECViewModelService;

public class ECViewModelRemoteService implements IECViewModelService {

	private List<IECViewModelService> delegates = new ArrayList<IECViewModelService>();

	@Override
	public void setModel(String viewModel) {
		try {
			for (IECViewModelService service : delegates
					.toArray(new IECViewModelService[delegates.size()])) {
				ResourceSet rs = new ResourceSetImpl();
				Resource resource = rs
						.createResource(URI.createFileURI("test"));
				resource.load(new ByteArrayInputStream(viewModel.getBytes()),
						null);
				YView view = (YView) resource.getContents().get(0);
				service.delegateSetModel(view);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void addDelegate(IECViewModelService delegate) {
		if (!delegates.contains(delegate)) {
			delegates.add(delegate);
		}
	}

	@Override
	public void delegateSetModel(YView view) {
		// nothing to do
	}

}
