package com.github.oraum.service;

import com.github.oraum.persistence.dao.UserDao;
import com.github.oraum.persistence.entity.User;
import com.github.oraum.service.GreetingService;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
public class GreetingServiceTest {

    @Inject
    GreetingService gs;

    @Inject
    IDatabaseConnection databaseConnection;

    @Deployment
    public static Archive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addAsResource("dataset.xml")
                .addClasses(GreetingService.class, User.class, UserDao.class, TestPersistenceProvider.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before
    public void setUpDataBase() {
        try {
            FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder().build(GreetingServiceTest.class.getClassLoader().getResourceAsStream("dataset.xml"));
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void greetAllUsersTest() {
        assertThat(gs.greetAllUsers(), is(equalTo("Hello World!\nHello Foo!")));
    }

    @Test

    public void greetUserIdTest() {
        assertThat(gs.greetUserWithId(1L), is(equalTo("Hello World!")));
    }

    @Test
    public void greetUserTest() {
        assertThat(gs.greetUser("World"), is(equalTo("Hello World!")));
    }


}
