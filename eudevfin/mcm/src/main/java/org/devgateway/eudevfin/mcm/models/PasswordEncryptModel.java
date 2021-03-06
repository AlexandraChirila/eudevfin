/*******************************************************************************
 * Copyright (c) 2014 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *******************************************************************************/
/**
 * 
 */
package org.devgateway.eudevfin.mcm.models;

import org.apache.wicket.model.IWrapModel;
import org.devgateway.eudevfin.auth.common.util.DigestUtils;
import org.devgateway.eudevfin.ui.common.models.WrappingModel;

/**
 * @author mihai
 * 
 * Proxy model that encrypts a password text using the {@link DigestUtils#passwordEncrypt(String)}
 * 
 */
public class PasswordEncryptModel extends WrappingModel<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5932617372627965237L;

	/**
	 * @param originalModel
	 */
	public PasswordEncryptModel(IWrapModel<String> originalModel) {
		super(originalModel);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.model.IModel#getObject()
	 */
	@Override
	public String getObject() {
		 return originalModel.getObject();	        
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.model.IModel#setObject(java.lang.Object)
	 */
	@Override
	public void setObject(String object) {
		  originalModel.setObject(DigestUtils.passwordEncrypt(object));
	}

}
