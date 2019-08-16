package com.github.oraum.service;

import com.github.oraum.persistence.dao.UserDao;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

public class GreetingServiceTest {

    @Rule
    public WeldInitiator weld = WeldInitiator.from(GreetingService.class, UserDao.class, TestPersistenceProvider.class).inject(this).build();

    @Inject
    GreetingService gs;

    @Inject
    IDatabaseConnection databaseConnection;

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
    public void greetUserIdTest() {
        assertThat(gs.greetUserWithId(1L), is(equalTo("Hello World!")));
    }

    @Test
    public void greetUserTest() {
        assertThat(gs.greetUser("World"), is(equalTo("Hello World!")));
    }

    @Test
    public void greetAllUsersTest() {
        assertThat(gs.greetAllUsers(), is(equalTo("Hello World!\nHello Foo!")));
    }
}
