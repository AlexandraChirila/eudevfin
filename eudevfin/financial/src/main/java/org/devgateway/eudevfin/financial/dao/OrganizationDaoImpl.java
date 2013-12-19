/**
 * 
 */
package org.devgateway.eudevfin.financial.dao;

import org.devgateway.eudevfin.financial.Organization;
import org.devgateway.eudevfin.financial.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Alex
 *
 */
@Component
@Lazy(value=false)
public class OrganizationDaoImpl extends AbstractDaoImpl<Organization, OrganizationRepository> {

	@Autowired
	private OrganizationRepository repo;
	
	@Override
	OrganizationRepository getRepo() {
		return repo;
	}

	@Override
	@ServiceActivator(inputChannel="saveOrganizationChannel")
	public Organization save(Organization o) {
		return super.save(o);
	}
}
