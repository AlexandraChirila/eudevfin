/**
 * 
 */
package org.devgateway.eudevfin.financial.repository;

import org.devgateway.eudevfin.metadata.common.domain.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Alex
 *
 */
public interface OrganizationRepository extends
		PagingAndSortingRepository<Organization, Long> {
	
	
	@Query(" select distinct(trn.parent) from OrganizationTranslation trn where trn.locale=?1 AND lower(trn.name) like %?2% ")
	Page<Organization> findByTranslationLocaleAndTranslationNameContaining(String locale, String searchString,Pageable pageable);

	@Query(" select distinct(trn.parent) from OrganizationTranslation trn "
			+ "where lower(trn.name) like %?1% or lower(trn.donorName) like %?1%")
	Page<Organization> findByTranslationNameContaining(String searchString,Pageable pageable);
	
	Organization findByCodeAndDonorCode(String code, String donorCode);

    @Query(" select distinct org from CustomFinancialTransaction ctx join ctx.extendingAgency org")
    Page<Organization> findUsedOrganization(Pageable page);

    @Query(" select distinct org from OrganizationTranslation trn, CustomFinancialTransaction ctx join ctx.extendingAgency org " +
            "where trn.parent = org.id AND trn.locale=?1 AND lower(trn.name) like %?2% ")
    Page<Organization> findUsedOrganizationByTranslationsNameIgnoreCase(String locale, String term, Pageable page);
}
