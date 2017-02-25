package ca.customer.dao.repositories;

import ca.customer.dao.models.EntityModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.dataset.ReplacementDataSetLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ca.customer.dao.DaoConfig;
import ca.customer.dao.TestApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Vivek on 20-01-2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
@DbUnitConfiguration(dataSetLoader = ReplacementDataSetLoader.class)
@DatabaseSetup({"notification-init.xml"})
@SpringBootTest(classes = {TestApplication.class, DaoConfig.class})
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;


    @After
    @DatabaseTearDown
    public void tearDown() throws Exception {

    }

    @Before
    public void setUp() throws Exception {
        //donorModel = donorRepository.findOne(0L);
    }

    @Test()
    @ExpectedDatabase(table = EntityModel.TBL_ENTITY, value = "notification-save-expect.xml")
    public void testSave() throws JsonProcessingException {
        EntityModel entity = new EntityModel();



    }
}
