/**
 * 
 */
package org.devgateway.eudevfin.common.spring;

/**
 * @author mihai
 * 
 * SpEL stuff that reads from spring property fields
 * See the bean named commonProperties defined in commonContext.xml
 * Also see the file common.properties
 */
public final class SpringPropertyExpressions {
	public static final String SELECT2_PAGE_SIZE="#{commonProperties['select2.pageSize']}";
	public static final String EUDEVFIN_REPORTING_YEAR_START="#{commonProperties['eudevfin.reportingYear.start']}";
	public static final String EUDEVFIN_REPORTING_YEAR_END="#{commonProperties['eudevfin.reportingYear.end']}";
	public static final String SI_DEFAULT_REPLY_TIMEOUT="#{commonProperties['si.defaultReplyTimeout']}";
}
