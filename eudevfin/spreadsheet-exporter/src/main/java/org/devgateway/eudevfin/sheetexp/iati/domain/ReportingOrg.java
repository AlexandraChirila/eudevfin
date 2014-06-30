/**
 * 
 */
package org.devgateway.eudevfin.sheetexp.iati.domain;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

/**
 * @author alexandru-m-g
 *
 */
@XStreamConverter(value=ToAttributedValueConverter.class, strings={"value"})
public class ReportingOrg {
	@XStreamAsAttribute
	private String type;
	@XStreamAsAttribute
	private String ref;
	private String value;
	public String getType() {
		return this.type;
	}
	public void setType(final String type) {
		this.type = type;
	}
	public String getRef() {
		return this.ref;
	}
	public void setRef(final String ref) {
		this.ref = ref;
	}
	public String getValue() {
		return this.value;
	}
	public void setValue(final String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return this.getValue();
	}
	
	
	
	
}