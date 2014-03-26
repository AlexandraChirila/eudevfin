/**
 * 
 */
package org.devgateway.eudevfin.financial.repository;

import java.util.Collection;
import java.util.List;

import org.devgateway.eudevfin.auth.common.domain.PersistedUserGroup;
import org.devgateway.eudevfin.financial.CustomFinancialTransaction;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Alex
 *
 */
public interface CustomFinancialTransactionRepository extends
		PagingAndSortingRepository<CustomFinancialTransaction, Long> {
	
	Page<CustomFinancialTransaction> findByDraft(Boolean draft, Pageable pageable );
	
	Page<CustomFinancialTransaction> findByDraftAndPersistedUserGroup(Boolean draft,PersistedUserGroup persistedUserGroup, Pageable pageable );
	
	List<CustomFinancialTransaction> findByReportingYearBetweenAndDraftFalse(LocalDateTime start, LocalDateTime end);
	
	List<CustomFinancialTransaction> findByReportingYearBetweenAndDraftFalseAndFormTypeNotIn(LocalDateTime start,
			LocalDateTime end, Collection<String> notFormType);

	@Query ("select distinct year(ctx.reportingYear) from CustomFinancialTransaction ctx ")
	List<Integer> findDistinctReportingYears();

}
