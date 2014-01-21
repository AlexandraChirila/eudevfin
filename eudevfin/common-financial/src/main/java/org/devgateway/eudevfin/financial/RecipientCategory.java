/**
 * 
 */
package org.devgateway.eudevfin.financial;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 * @author Alex
 * @deprecated Area should be used directly instead of this
 *
 */
@Entity
@Audited
@DiscriminatorValue("Recipient")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
public class RecipientCategory extends Category {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7973525314051632466L;
	
	
	/**
	 * Country or area
	 */
	@ManyToOne
	Area area;

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
}
