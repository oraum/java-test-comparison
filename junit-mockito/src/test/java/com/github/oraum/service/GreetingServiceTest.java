package com.github.oraum.service;

import com.github.oraum.persistence.dao.UserDao;
import com.github.oraum.persistence.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GreetingServiceTest {

    private GreetingService gs;

    @Before
    public void setup() {
        gs = new GreetingService();
        UserDao daoMock = mock(UserDao.class);
        gs.dao = daoMock;

        User testUser = new User();
        testUser.setId(1L);
        testUser.setName("World");

        when(daoMock.getUserById(anyLong())).thenReturn(testUser);

        User testUser2 = new User();
        testUser2.setId(2L);
        testUser2.setName("Foo");

        when(daoMock.getAllUsers()).thenReturn(Arrays.asList(testUser, testUser2));
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
