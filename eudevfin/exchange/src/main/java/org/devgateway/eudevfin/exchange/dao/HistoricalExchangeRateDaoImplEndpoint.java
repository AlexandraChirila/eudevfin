/*
 * Copyright (c) 2013 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *    mpostelnicu
 */

/**
 * 
 */
package org.devgateway.eudevfin.exchange.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.devgateway.eudevfin.common.dao.AbstractDaoImpl;
import org.devgateway.eudevfin.common.spring.integration.NullableWrapper;
import org.devgateway.eudevfin.exchange.common.domain.HistoricalExchangeRate;
import org.devgateway.eudevfin.exchange.common.service.HistoricalExchangeRateService;
import org.devgateway.eudevfin.exchange.repository.HistoricalExchangeRateRepository;
import org.devgateway.eudevfin.exchange.service.ExternalExchangeQueryService;
import org.joda.money.CurrencyUnit;
import org.joda.money.ExchangeRate;
import org.joda.money.IllegalCurrencyException;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

/**
 * @author mihai 
 * Endpoint for the spring integration service to access exchange
 *         rates Do NOT use this on the client side!
 * @see HistoricalExchangeRateService
 */
@Component
@Lazy(value=false)
public class HistoricalExchangeRateDaoImplEndpoint extends AbstractDaoImpl<HistoricalExchangeRate,Long, HistoricalExchangeRateRepository> {

	@Autowired
	private HistoricalExchangeRateRepository repo;

	@Autowired
	ExternalExchangeQueryService exchangeQueryService;

	@Autowired
	HistoricalExchangeRateService service;

	protected static Logger logger = Logger
			.getLogger(HistoricalExchangeRateDaoImplEndpoint.class);

	/**
	 * @see HistoricalExchangeRateService#findOne(Long)
	 * @param id
	 * @return
	 */
	@ServiceActivator(inputChannel = "getHistoricalExchangeRateByIdChannel")
	public NullableWrapper<HistoricalExchangeRate> findOne(Long id) {
		return newWrapper(repo.findOne(id));
	}

	/**
	 * @see HistoricalExchangeRateService#save(HistoricalExchangeRate)
	 * @param u
	 * @return
	 */
	@ServiceActivator(inputChannel = "createHistoricalExchangeRateChannel")
	public NullableWrapper<HistoricalExchangeRate> saveHistoricalExchangeRate(
			HistoricalExchangeRate u) {
		return newWrapper(repo.save(u));		
	}

	/**
	 * @see HistoricalExchangeRateService#deleteRatesForDate(LocalDateTime)
	 * @param date
	 * @return
	 */
	@ServiceActivator(inputChannel = "findRatesForDateChannel")
	public Iterable<HistoricalExchangeRate> findRatesForDate(LocalDateTime date) {
		Iterable<HistoricalExchangeRate> findByDate = repo.findByDate(date);
		return findByDate;
	}

	/**
	 * @see HistoricalExchangeRateService#fetchRatesForDate(LocalDateTime)
	 * @param date
	 * @return
	 */
	@ServiceActivator(inputChannel = "fetchRatesForDateChannel")
	public int fetchRatesForDate(LocalDateTime date) {

		int savedRates = 0;

		// rates exist already for the given date, currently we skip any more
		// rates fetching.
		// we can parameterize this l8r
		if (service.findRatesForDate(date).iterator().hasNext())
			return savedRates;

		
		LinkedHashMap<String, Object> mapFromJson = exchangeQueryService
				.getExchangeRatesForDate(date);

		CurrencyUnit baseUnit = CurrencyUnit.of((String) mapFromJson.get("base"));
		@SuppressWarnings("unchecked")
		LinkedHashMap<String, Number> rates = (LinkedHashMap<String, Number>) mapFromJson
				.get("rates");

		Iterator<String> iterator = rates.keySet().iterator();
		while (iterator.hasNext()) {
			String currency = (String) iterator.next();
			CurrencyUnit counterUnit = null;
			try {
				counterUnit = CurrencyUnit.of(currency);
			} catch (IllegalCurrencyException e) {
				logger.warn("Unkown currency " + currency
						+ ". Will not import the exchange rate!");
				continue;
			}
			HistoricalExchangeRate her = new HistoricalExchangeRate();
			her.setDate(date);

			her.setRate(ExchangeRate.of(baseUnit, counterUnit, new BigDecimal(
					rates.get(currency).toString())));
			service.save(her);
			savedRates++;
		}
		return savedRates;
	}

	@Override
	protected HistoricalExchangeRateRepository getRepo() {
		return repo;		
	}

	
	/**
	 * @see HistoricalExchangeRateService#findByInvertedSource(String, Pageable)
	 * @param source
	 * @param locale
	 * @param pageable
	 * @return
	 */
	@ServiceActivator(inputChannel = "findHistoricalExchangeRateByInvertedSourceChannel")
	public Page<HistoricalExchangeRate> findByInvertedSource(String source, @Header("pageable") Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findBySourceNot(source, pageable);
	}
	
	
	/**
	 * @see HistoricalExchangeRateService#delete(HistoricalExchangeRate)
	 * @param e 
	 */
	@Override
	@ServiceActivator(inputChannel = "deleteHistoricalExchangeRate")
	public void delete(HistoricalExchangeRate e) {
		super.delete(e);
	}
	
}
