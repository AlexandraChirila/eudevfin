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
package org.devgateway.eudevfin.importing.metadata.mapping;

import liquibase.exception.SetupException;

import org.apache.commons.lang.StringUtils;
import org.devgateway.eudevfin.financial.dao.CategoryDaoImpl;
import org.devgateway.eudevfin.importing.metadata.exception.InvalidDataException;
import org.devgateway.eudevfin.metadata.common.domain.Area;
import org.devgateway.eudevfin.metadata.common.domain.Category;
import org.devgateway.eudevfin.metadata.common.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alex
 *
 */
public class AreaMapper extends AbstractMapper<Area> {
	
	@Autowired
	CategoryDaoImpl categDao;
	
	
	
	public AreaMapper() {
		super();
		try {
			this.setUp();
		} catch (final SetupException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Area instantiate() {
		return new Area();
	}
	
	public Area __factory(final String type){
		if ("country".equals(type)) {
			return new Country();
		} else {
			return new Area();
		}
	}
	
	public void __incomeGroup(final Area newArea, final String incomeGroupCode) {
		if ( StringUtils.isNotEmpty(incomeGroupCode) ) {
			final Category incomeGroup	= this.categDao.findByCodeAndClass(incomeGroupCode, Category.class, false).getEntity();
			if ( incomeGroup != null ) {
				newArea.setIncomeGroup(incomeGroup);
			} else {
				throw new InvalidDataException(
						String.format("Found null income group category for code %s for area with code %s", 
								incomeGroupCode, newArea.getCode() )
				);
			}
		}
	}
	
	public void __geographyCategory(final Area newArea, final String geography) {
		if ( StringUtils.isNotEmpty(geography) ) {
			final String categCode	= "GEOGRAPHY##" + geography.toUpperCase();
			final Category geographyCategory	= this.categDao.findByCodeAndClass(categCode, Category.class, false).getEntity();
			if ( geographyCategory != null ) {
				newArea.setGeographyCategory(geographyCategory);
			}else {
				throw new InvalidDataException(
						String.format("Found null geography category for code %s for area with code %s", 
								categCode, newArea.getCode() )
				);
			}
		}
	}
	
}
