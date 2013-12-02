/**
 * 
 */
package org.devgateway.eudevfin.financial.test.services;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;

import org.devgateway.eudevfin.financial.Category;
import org.devgateway.eudevfin.financial.service.CategoryService;
import org.hibernate.LazyInitializationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alex
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/financialContext.xml",
		"classpath:META-INF/commonFinancialContext.xml","classpath:testFinancialContext.xml" })
@Component 
@TransactionConfiguration(defaultRollback=false, transactionManager="transactionManager")
public class CategoryServiceTest {

	private static final String SECTORS_ROOT_TEST = "sectors_root_test";

	private static final String SUBSECTORS_LABEL_TEST = "subsectors_label_test";

	private static final String SECTORS_LABEL_TEST = "sectors_label_test";
	
	@Autowired
	CategoryService categoryService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testSave() {
		Category c 	= new Category();
		c.setName("Test parent category");
		c.setCode("TPC-test");
		c.setChildren(new HashSet<Category>());
		
		Category c1 = new Category();
		c1.setName("First child");
		c1.setCode("FC-test");
		c.getChildren().add(c1);
		
		Category c2 = new Category();
		c2.setName("Second child");
		c2.setCode("SC-test");
		c.getChildren().add(c2);
		
		Category result 	= categoryService.save(c);
		assertNotNull(result.getChildren());
		assertEquals(result.getChildren().size(), 2);
		
	}
	
	@Test
	public void testFindByLabelCode() {
		createLabels();
		
		createFakeSectors();
		
		List<Category> allSectors				= categoryService.findByTagsCode(SECTORS_LABEL_TEST);
		assertEquals(allSectors.size(), 3);
		
		List<Category> allSubSectors			= categoryService.findByTagsCode(SUBSECTORS_LABEL_TEST);
		assertEquals(allSubSectors.size(), 2);
		
		Category rootSectorsInitialized			= categoryService.findByCode(SECTORS_ROOT_TEST, true);
		assertNotNull(rootSectorsInitialized);
		assertNotNull(rootSectorsInitialized.getChildren());
		assertNotNull(rootSectorsInitialized.getChildren().iterator().next());
		assertNotNull(rootSectorsInitialized.getTags().iterator().next());
		
		Category rootSectorsNonInitialized		= categoryService.findByCode(SECTORS_ROOT_TEST, false);
		try {
			rootSectorsNonInitialized.getChildren().iterator().next();
		}
		catch(LazyInitializationException e){
			assertTrue("Chilren not fetched by hibernate", true);
		}
		try {
			rootSectorsNonInitialized.getTags().iterator().next();
		}
		catch(LazyInitializationException e){
			assertTrue("Tags not fetched by hibernate", true);
		}
		
		
	}

	@Transactional
	private void createFakeSectors() {
		Category sectorLabel	= categoryService.findByCode(SECTORS_LABEL_TEST, false);
		Category subSectorLabel	= categoryService.findByCode(SUBSECTORS_LABEL_TEST, false);
		
		Category sectorsRoot = new Category();
		sectorsRoot.setName("Sectors Root");
		sectorsRoot.setCode(SECTORS_ROOT_TEST);
		sectorsRoot.setTags(new HashSet<Category>());
		sectorsRoot.getTags().add(sectorLabel);
		
		Category sector1	= new Category();
		sector1.setName("Sector 1");
		sector1.setCode("sector_1_test");
		sector1.setParentCategory(sectorsRoot);
		sector1.setTags(new HashSet<Category>());
		sector1.getTags().add(sectorLabel);
		sector1.getTags().add(subSectorLabel);
		
		Category sector2	= new Category();
		sector2.setName("Sector 2");
		sector2.setCode("sector_2_test");
		sector2.setParentCategory(sectorsRoot);
		sector2.setTags(new HashSet<Category>());
		sector2.getTags().add(sectorLabel);
		sector2.getTags().add(subSectorLabel);
		
		categoryService.save(sectorsRoot);
	}


	/**
	 * creates some fake labels for the test
	 */
	@Transactional
	private void createLabels() {
		Category labelRoot	= new Category();
		labelRoot.setName("Label Root - test");
		labelRoot.setCode("label_root_test");
		
		Category sectors = new Category();
		sectors.setName("Sectors Label - test");
		sectors.setCode(SECTORS_LABEL_TEST);
		sectors.setParentCategory(labelRoot);
		
		Category subsectors = new Category();
		subsectors.setName("Sub-Sectors Label - test");
		subsectors.setCode(SUBSECTORS_LABEL_TEST);
		subsectors.setParentCategory(sectors);
		
		Category fakeLabel = new Category();
		fakeLabel.setName("Fake label - test");
		fakeLabel.setCode("fake_label_test"); 
		fakeLabel.setParentCategory(labelRoot);
		
		categoryService.save(labelRoot);
	}

}