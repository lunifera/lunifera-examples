/*
* generated by Xtext
*/
package org.lunifera.examples.ecview.dsl;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class MasterDetailDSLStandaloneSetup extends MasterDetailDSLStandaloneSetupGenerated{

	public static void doSetup() {
		new MasterDetailDSLStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}
