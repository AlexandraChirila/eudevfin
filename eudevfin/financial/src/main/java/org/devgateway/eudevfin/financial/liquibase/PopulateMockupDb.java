package org.devgateway.eudevfin.financial.liquibase;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.devgateway.eudevfin.common.liquibase.MockupDbPopulator;
import org.devgateway.eudevfin.financial.Category;
import org.devgateway.eudevfin.financial.FinancialTransaction;
import org.devgateway.eudevfin.financial.Organization;
import org.devgateway.eudevfin.financial.dao.CategoryDaoImpl;
import org.devgateway.eudevfin.financial.dao.FinancialTransactionDaoImpl;
import org.devgateway.eudevfin.financial.dao.OrganizationDaoImpl;
import org.devgateway.eudevfin.financial.util.FinancialConstants;
import org.joda.money.BigMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class PopulateMockupDb implements MockupDbPopulator {
	
	public static int NUM_OF_TX	= 50;
	
	
	@Autowired
	private FinancialTransactionDaoImpl txDao;
	
	@Autowired
	private OrganizationDaoImpl orgDao;

	@Autowired
	private CategoryDaoImpl catDao;
	
	@Override
	public void populate() {
		
		createOrganizations();
		createSectors();
		createTypesOfFlow();
		createTypesOfAid();
		Random r = new Random();
		
		for (int i=1; i<=NUM_OF_TX; i++ ) {
			FinancialTransaction tx 	= new FinancialTransaction();
			tx.setCommitments(BigMoney.parse("EUR " + Math.ceil(Math.random()*100000)));
			Organization org = null;
			Category sector = null;
			Category typeOfFlow = null;
			Category typeOfAid = null;
			
			List<Organization> listOrgs = orgDao.findAllAsList();
			List<Category> listSec = catDao.findByTagsCode(FinancialConstants.SUBSECTORS_TAG);
			List<Category> listTof = catDao.findByTagsCode(FinancialConstants.TYPEOFFLOW_TAG);
			List<Category> listToa = catDao.findByTagsCode(FinancialConstants.TYPEOFAID_TAG);
			int orgRandomIndex=r.nextInt(listOrgs.size());
			int secRandomIndex=r.nextInt(listSec.size());
			int tofRandomIndex=r.nextInt(listTof.size());
			int toaRandomIndex=r.nextInt(listToa.size());
			
			org = listOrgs.get(orgRandomIndex);
			sector = listSec.get(secRandomIndex);
			typeOfFlow = listTof.get(tofRandomIndex);
			typeOfAid = listToa.get(toaRandomIndex);
			
			tx.setReportingOrganization( org );
			tx.setLocale("en");
			tx.setDescription("CDA Test Transaction " + i + " en");
			tx.setLocale("ro");
<<<<<<< Upstream, based on origin/master
			tx.setDescription("CDA Test Transaction " + i + " ro"); 
=======
			tx.setDescription("CDA Test Transaction " + i + " ro");
>>>>>>> bdd7b3e added new common module, for commonly used utils, moved generic code for liquibase jpa invocation to it, and contexthelper
			tx.setSector(sector);
			tx.setTypeOfFlow(typeOfFlow);
			tx.setTypeOfAid(typeOfAid);
			txDao.save(tx);
		}
	}
	private void createTypesOfAid() {
		//First create the tags that define a Sector
		Category typeOfAidTag = new Category();
		typeOfAidTag.setName("Type of Aid Tag");
		typeOfAidTag.setCode(FinancialConstants.TYPEOFAID_TAG);
		catDao.save(typeOfAidTag);
		
		Category toa1 = new Category();
		toa1.setCode("A");
		toa1.setLocale("en");
		toa1.setName("Budget support en");
		toa1.setLocale("ro");
		toa1.setName("Budget support ro");
		toa1.setTags(new HashSet<Category>());
		toa1.getTags().add(typeOfAidTag);
		catDao.save(toa1);

		Category toa2 = new Category();
		toa2.setCode("B");
		toa2.setLocale("en");
		toa2.setName("Core contributions and pooled programmes and funds en");
		toa2.setLocale("ro");
		toa2.setName("Core contributions and pooled programmes and funds ro");
		toa2.setTags(new HashSet<Category>());
		toa2.getTags().add(typeOfAidTag);
		catDao.save(toa2);

		Category toa3 = new Category();
		toa3.setCode("C");
		toa3.setLocale("en");
		toa3.setName("Project-type interventions en");
		toa3.setLocale("ro");
		toa3.setName("Project-type interventions ro");
		toa3.setTags(new HashSet<Category>());
		toa3.getTags().add(typeOfAidTag);
		catDao.save(toa3);

		Category toa4 = new Category();
		toa4.setCode("D");
		toa4.setLocale("en");
		toa4.setName("Experts and other technical assistance en");
		toa4.setLocale("ro");
		toa4.setName("Experts and other technical assistance ro");
		toa4.setTags(new HashSet<Category>());
		toa4.getTags().add(typeOfAidTag);
		catDao.save(toa4);

		Category toa5 = new Category();
		toa5.setCode("E");
		toa5.setLocale("en");
		toa5.setName("Scholarships and student costs in donor countries en");
		toa5.setLocale("ro");
		toa5.setName("Scholarships and student costs in donor countries ro");
		toa5.setTags(new HashSet<Category>());
		toa5.getTags().add(typeOfAidTag);
		catDao.save(toa5);

		Category toa6 = new Category();
		toa6.setCode("F");
		toa6.setLocale("en");
		toa6.setName("Debt relief en");
		toa6.setLocale("ro");
		toa6.setName("Debt relief ro");
		toa6.setTags(new HashSet<Category>());
		toa6.getTags().add(typeOfAidTag);
		catDao.save(toa6);

		Category toa7 = new Category();
		toa7.setCode("G");
		toa7.setLocale("en");
		toa7.setName("Administrative costs not included elsewhere en");
		toa7.setLocale("ro");
		toa7.setName("Administrative costs not included elsewhere ro");
		toa7.setTags(new HashSet<Category>());
		toa7.getTags().add(typeOfAidTag);
		catDao.save(toa7);

		Category toa8 = new Category();
		toa8.setCode("H");
		toa8.setLocale("en");
		toa8.setName("Other in-donor expenditures en");
		toa8.setLocale("ro");
		toa8.setName("Other in-donor expenditures ro");
		toa8.setTags(new HashSet<Category>());
		toa8.getTags().add(typeOfAidTag);
		catDao.save(toa8);

	}
	private void createTypesOfFlow() {
		//First create the tags that define a Sector
		Category typeOfFlowTag = new Category();
		typeOfFlowTag.setName("Type of Flow Tag");
		typeOfFlowTag.setCode(FinancialConstants.TYPEOFFLOW_TAG);
		catDao.save(typeOfFlowTag);
		
		Category tof1 = new Category();
		tof1.setCode("11");
		tof1.setLocale("en");
		tof1.setName("ODA Grant en");
		tof1.setLocale("ro");
		tof1.setName("ODA Grant ro");
		tof1.setTags(new HashSet<Category>());
		tof1.getTags().add(typeOfFlowTag);
		catDao.save(tof1);

		Category tof2 = new Category();
		tof2.setCode("12");
		tof2.setLocale("en");
		tof2.setName("ODA Grant-like en");
		tof2.setLocale("ro");
		tof2.setName("ODA Grant-like ro");
		tof2.setTags(new HashSet<Category>());
		tof2.getTags().add(typeOfFlowTag);
		catDao.save(tof2);

		Category tof3 = new Category();
		tof3.setCode("13");
		tof3.setLocale("en");
		tof3.setName("ODA Loan en");
		tof3.setLocale("ro");
		tof3.setName("ODA Loan ro");
		tof3.setTags(new HashSet<Category>());
		tof3.getTags().add(typeOfFlowTag);
		catDao.save(tof3);

		Category tof4 = new Category();
		tof4.setCode("19");
		tof4.setLocale("en");
		tof4.setName("ODA equity investment en");
		tof4.setLocale("ro");
		tof4.setName("ODA equity investment ro");
		tof4.setTags(new HashSet<Category>());
		tof4.getTags().add(typeOfFlowTag);
		catDao.save(tof4);

		Category tof5 = new Category();
		tof5.setCode("14");
		tof5.setLocale("en");
		tof5.setName("OOF loan en");
		tof5.setLocale("ro");
		tof5.setName("OOF loan ro");
		tof5.setTags(new HashSet<Category>());
		tof5.getTags().add(typeOfFlowTag);
		catDao.save(tof5);
		
	}

	private void createSectors() {

		//First create the tags that define a Sector
		Category sectorsTag = new Category();
		sectorsTag.setName("Sectors Tag");
		sectorsTag.setCode(FinancialConstants.SECTORS_TAG);
		catDao.save(sectorsTag);
		
		Category subsectorsTag = new Category();
		subsectorsTag.setName("Sub-Sectors Tag");
		subsectorsTag.setCode(FinancialConstants.SUBSECTORS_TAG);
		subsectorsTag.setParentCategory(sectorsTag);		
		catDao.save(subsectorsTag);

		Category sectorsRoot = new Category();
		sectorsRoot.setLocale("en");
		sectorsRoot.setName("DAC Sector Scheme");
		sectorsRoot.setLocale("ro");
		sectorsRoot.setName("DAC Sector Scheme ro");
		sectorsRoot.setCode(FinancialConstants.SECTORS_ROOT);
		sectorsRoot.setTags(new HashSet<Category>());
		sectorsRoot.getTags().add(sectorsTag);
		catDao.save(sectorsRoot);
		
		
		Category s1 = new Category();
		s1.setCode("430");
		s1.setLocale("en");
		s1.setName("Others en");
		s1.setLocale("ro");
		s1.setName("Others ro");
<<<<<<< Upstream, based on origin/master
		s1.setTags(new HashSet<Category>());
		s1.getTags().add(sectorsTag);
		s1.getTags().add(subsectorsTag);
		s1.setParentCategory(sectorsRoot);
=======
>>>>>>> bdd7b3e added new common module, for commonly used utils, moved generic code for liquibase jpa invocation to it, and contexthelper
		catDao.save(s1);
		
		Category s2 = new Category();
		s2.setCode("510");
		s2.setLocale("en");
		s2.setName("General Budget Support en");
		s2.setLocale("ro");
		s2.setName("General Budget Support ro");
<<<<<<< Upstream, based on origin/master
		s2.setTags(new HashSet<Category>());
		s2.getTags().add(sectorsTag);
		s2.getTags().add(subsectorsTag);
		s2.setParentCategory(sectorsRoot);
=======
>>>>>>> bdd7b3e added new common module, for commonly used utils, moved generic code for liquibase jpa invocation to it, and contexthelper
		catDao.save(s2);

		Category s3 = new Category();
		s3.setCode("210");
		s3.setLocale("en");
		s3.setName("Transport and Storage en");
		s3.setLocale("ro");
		s3.setName("Transport and Storage ro");
<<<<<<< Upstream, based on origin/master
		s3.setTags(new HashSet<Category>());
		s3.getTags().add(sectorsTag);
		s3.getTags().add(subsectorsTag);
		s3.setParentCategory(sectorsRoot);
=======
>>>>>>> bdd7b3e added new common module, for commonly used utils, moved generic code for liquibase jpa invocation to it, and contexthelper
		catDao.save(s3);

		Category s4 = new Category();
		s4.setCode("230");
		s4.setLocale("en");
		s4.setName("Energy Generation and Support en");
		s4.setLocale("ro");
		s4.setName("Energy Generation and Support ro");
<<<<<<< Upstream, based on origin/master
		s4.setTags(new HashSet<Category>());
		s4.getTags().add(sectorsTag);
		s4.getTags().add(subsectorsTag);
		s4.setParentCategory(sectorsRoot);
=======
>>>>>>> bdd7b3e added new common module, for commonly used utils, moved generic code for liquibase jpa invocation to it, and contexthelper
		catDao.save(s4);

		Category s5 = new Category();
		s5.setCode("311");
		s5.setLocale("en");
		s5.setName("Agriculture en");
		s5.setLocale("ro");
		s5.setName("Agriculture ro");
<<<<<<< Upstream, based on origin/master
		s5.setTags(new HashSet<Category>());
		s5.getTags().add(sectorsTag);
		s5.getTags().add(subsectorsTag);
		s5.setParentCategory(sectorsRoot);
=======
>>>>>>> bdd7b3e added new common module, for commonly used utils, moved generic code for liquibase jpa invocation to it, and contexthelper
		catDao.save(s5);
<<<<<<< Upstream, based on origin/master
=======

		Category sectorScheme = new Category();
		sectorScheme.setCode("000");
		sectorScheme.setLocale("en");
		sectorScheme.setName("DAC Sector Scheme");
		sectorScheme.setLocale("ro");
		sectorScheme.setName("DAC Sector Scheme ro");
		
		HashSet<Category> sectorList = new HashSet<Category>(catDao.findAllAsList());

		sectorScheme.setTags(sectorList);
		catDao.save(sectorScheme);
>>>>>>> bdd7b3e added new common module, for commonly used utils, moved generic code for liquibase jpa invocation to it, and contexthelper
	
	}

	private void createOrganizations() {
		Organization o1 = new Organization();
		
		o1.setCode("anOrgCode1");
		o1.setLocale("en");
		o1.setName("CDA Test Org 1 en");
		o1.setLocale("ro");
		o1.setName("CDA Test Org 1 ro");
		
		orgDao.save(o1);
		
		Organization o2 = new Organization();
		o2.setCode("anOrgCode2");
		o2.setLocale("en");
		o2.setName("CDA Test Org 2 en");
		o2.setLocale("ro");
		o2.setName("CDA Test Org 2 ro");
		
		orgDao.save(o2);

		Organization o3 = new Organization();
		o3.setCode("anOrgCode3");
		o3.setLocale("en");
		o3.setName("CDA Test Org 3 en");
		o3.setLocale("ro");
		o3.setName("CDA Test Org 3 ro");
		
		orgDao.save(o3);

		Organization o4 = new Organization();
		o4.setCode("anOrgCode4");
		o4.setLocale("en");
		o4.setName("CDA Test Org 4 en");
		o4.setLocale("ro");
		o4.setName("CDA Test Org 4 ro");
		
		orgDao.save(o4);

		Organization o5 = new Organization();
		o5.setCode("anOrgCode5");
		o5.setLocale("en");
		o5.setName("CDA Test Org 5 en");
		o5.setLocale("ro");
		o5.setName("CDA Test Org 5 ro");
		
		orgDao.save(o5);
	}

	

	
}
