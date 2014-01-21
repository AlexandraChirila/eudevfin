/**
 * 
 */
package org.devgateway.eudevfin.financial;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 * @author Alex
 *
 */
@Entity
@Audited
@DiscriminatorValue("Sector")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class SectorCategory extends Category {

	public SectorCategory() {
		super();
	}

}
