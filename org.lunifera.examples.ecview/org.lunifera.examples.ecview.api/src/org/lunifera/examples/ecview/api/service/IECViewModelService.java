package org.lunifera.examples.ecview.api.service;

import org.eclipse.emf.ecp.ecview.common.model.core.YView;

public interface IECViewModelService {

	/**
	 * Sets the given view.
	 * 
	 * @param viewModel
	 */
	public void setModel(String viewModel);

	
	/**
	 * For delegates
	 * 
	 * @param view
	 */
	public void delegateSetModel(YView view);
	
}
