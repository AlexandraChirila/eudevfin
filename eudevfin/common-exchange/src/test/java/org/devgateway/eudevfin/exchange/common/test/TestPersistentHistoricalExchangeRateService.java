/*
 * Copyright (c) 2013 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *    aartimon
 */

package org.devgateway.eudevfin.exchange.common.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/financialContext.xml",
		"classpath:META-INF/commonFinancialContext.xml" })
public class TestPersistentHistoricalExchangeRateService {

	/**
	 * Creates an exhange rate locally, no S-I
	 */
	@Test
	public void testCreateHistoricalExchangeRate() {
		
	}
}
