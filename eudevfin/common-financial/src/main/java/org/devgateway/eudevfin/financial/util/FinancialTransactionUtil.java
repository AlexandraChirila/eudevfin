package org.devgateway.eudevfin.financial.util;

import java.util.Locale;

import org.devgateway.eudevfin.common.spring.integration.NullableWrapper;
import org.devgateway.eudevfin.financial.FinancialTransaction;
import org.devgateway.eudevfin.financial.Organization;
import org.devgateway.eudevfin.financial.service.CurrencyMetadataService;
import org.joda.money.CurrencyUnit;

public final class FinancialTransactionUtil {

	/**
	 * Gets the {@link CurrencyUnit} based on the {@link Locale} using
	 * {@link Locale#getISO3Country()}
	 * 
	 * @param locale
	 * @return
	 */
	public static CurrencyUnit getCurrencyForCountryIso(String countryIso) {
		CurrencyUnit currencyUnit = null;
		if (countryIso != null)
			currencyUnit = CurrencyUnit.ofCountry(countryIso);
		return currencyUnit;
	}

	/**
	 * @param locale
	 * @param currencyMetadataService
	 * @return a not null
	 *         {@link FinancialTransactionUtil#getCurrencyForLocale(Locale)} or
	 *         else the
	 *         {@link FinancialTransactionUtil#getDefaultCurrency(CurrencyMetadataService)}
	 */
	public static CurrencyUnit getCurrencyForCountryIsoOrDefault(String countryIso,
			CurrencyMetadataService currencyMetadataService) {
		CurrencyUnit currencyUnit = getCurrencyForCountryIso(countryIso);
		if (currencyUnit != null)
			return currencyUnit;
		else
			return getDefaultCurrency(currencyMetadataService);
	}

	/**
	 * Initialize a previously newly created transaction
	 * 
	 * @param transaction
	 *            the previously created transaction
	 * @param currencyMetadataService
	 * @param organization
	 */
	public static void initializeFinancialTransaction(FinancialTransaction transaction,
			CurrencyMetadataService currencyMetadataService, Organization organization, String countryIso) {

		transaction.setCurrency(getCurrencyForCountryIsoOrDefault(countryIso, currencyMetadataService));
		transaction.setExtendingAgency(organization);
	}

	/**
	 * @param currencyMetadataService
	 * @return
	 */
	public static CurrencyUnit getDefaultCurrency(CurrencyMetadataService currencyMetadataService) {
		NullableWrapper<CurrencyUnit> defaultCurrencyUnitNW = currencyMetadataService
				.findByCode(CurrencyConstants.DEFAULT_CURRENCY_CODE_REQ);
		if (defaultCurrencyUnitNW.isNull())
			return CurrencyUnit.of("EUR");
		else
			return defaultCurrencyUnitNW.getEntity();
	}
}