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
package org.devgateway.eudevfin.mcm.util;

import org.devgateway.eudevfin.auth.common.domain.PersistedUserGroup;
import org.devgateway.eudevfin.auth.common.service.PersistedUserGroupService;
import org.devgateway.eudevfin.common.service.PagingHelper;
import org.devgateway.eudevfin.ui.common.components.util.ListGeneratorInterface;
import org.springframework.data.domain.PageRequest;

/**
 * @author mihai
 *
 */
public class PersistedUserGroupListGenerator implements
		ListGeneratorInterface<PersistedUserGroup> {

	private static final long serialVersionUID = -6936731401076768620L;
	private PersistedUserGroupService userGroupService;
	private String searchString;
	
	public PersistedUserGroupListGenerator(PersistedUserGroupService userGroupService,String searchString) {
		this.userGroupService=userGroupService;
		this.searchString=searchString;
	}

	/* (non-Javadoc)
	 * @see org.devgateway.eudevfin.ui.common.components.util.ListGeneratorInterface#getResultsList(int, int)
	 */
	@Override
	public PagingHelper<PersistedUserGroup> getResultsList(int pageNumber,
			int pageSize) {
		return PagingHelper.createPagingHelperFromPage(userGroupService.findByGeneralSearchPageable(searchString,null, new PageRequest(pageNumber-1, pageSize)));		
	}

}
