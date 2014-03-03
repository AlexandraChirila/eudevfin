/**
 * 
 */
package org.devgateway.eudevfin.dim.desktop.components.util;

import org.devgateway.eudevfin.common.service.PagingHelper;
import org.devgateway.eudevfin.financial.FinancialTransaction;
import org.devgateway.eudevfin.financial.service.FinancialTransactionService;
import org.devgateway.eudevfin.ui.common.components.util.ListGeneratorInterface;
import org.springframework.data.domain.PageRequest;

/**
 * @author Alex
 *
 */
public class GeneralSearchListGenerator implements ListGeneratorInterface<FinancialTransaction> {

	private static final long serialVersionUID = 2851842899904434912L;
	private String searchString;
	private FinancialTransactionService txService;
	
	
	public GeneralSearchListGenerator(String searchString,
			FinancialTransactionService txService) {
		super();
		this.searchString = searchString;
		this.txService = txService;
	}




	/* (non-Javadoc)
	 * @see org.devgateway.eudevfin.dim.desktop.components.config.ListGeneratorInterface#getResultsList(int, int)
	 */
	@Override
	public PagingHelper<FinancialTransaction> getResultsList(int pageNumber,
			int pageSize) {
		if ( this.searchString != null && this.searchString.length() > 1 )
			return PagingHelper.createPagingHelperFromPage(this.txService.findByGeneralSearchPageable(this.searchString,null, new PageRequest(pageNumber-1, pageSize)));
		else
			return null;
	}




	public String getSearchString() {
		return searchString;
	}




	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	

}
