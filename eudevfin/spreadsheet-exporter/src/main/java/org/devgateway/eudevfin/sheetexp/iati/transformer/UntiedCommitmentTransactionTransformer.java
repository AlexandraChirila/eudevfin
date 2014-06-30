/**
 * 
 */
package org.devgateway.eudevfin.sheetexp.iati.transformer;

import java.util.Map;

import org.devgateway.eudevfin.financial.CustomFinancialTransaction;
import org.devgateway.eudevfin.sheetexp.iati.domain.IatiActivity;
import org.joda.money.BigMoney;

/**
 * @author alexandru-m-g
 *
 */
public class UntiedCommitmentTransactionTransformer extends
		TiedCommitmentTransactionTransformer {

	/**
	 * @param ctx
	 * @param iatiActivity
	 * @param paramsMap
	 */
	public UntiedCommitmentTransactionTransformer(
			final CustomFinancialTransaction ctx, final IatiActivity iatiActivity,
			final Map<String, Object> paramsMap) {
		super(ctx, iatiActivity, paramsMap);
	}

	@Override
	protected BigMoney findTxMoney() {
		return this.getCtx().getAmountsUntied();
	}

	@Override
	protected String getTiedStatusCode() {
		return "5";
	}
	
	

}
