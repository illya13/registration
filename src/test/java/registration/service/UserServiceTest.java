package registration.service;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import registration.domain.User;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void before() {
        userService = new UserService();
    }

    @Test
    public void authOkTest() {
        assertThat(userService.auth("root", "qwerty"), is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void authFailed1Test() {
        assertThat(userService.auth("root", "qwerty1"), is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void authFailed2Test() {
        assertThat(userService.auth("root1", "qwerty"), is(notNullValue()));
    }

    @Test
    public void getUserByIdOkTest() {
        assertThat(userService.getUserById("root"), is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUserByIdFailedTest() {
        assertThat(userService.getUserById("root1"), is(notNullValue()));
    }

    @Test
    public void getAllUsersTest() {
        assertThat(userService.getAllUsers(), is(hasSize(1)));
    }

    @Test
    public void createDeleteUserTest() {
        int size = userService.getAllUsers().size();

        User.Builder builder = new User.Builder();
        User expected = builder.newUser("user").setPassword("qwerty").
                setFirstName("first").setLastName("last").
                build();

        User actual = userService.createUser(expected);
        assertThat(actual, isUserEquals(expected));

        assertThat(userService.getAllUsers(), is(hasSize(size+1)));

        userService.deleteUserById(expected.getId());
        assertThat(userService.getAllUsers(), is(hasSize(size)));
    }

    public static Matcher<User> isUserEquals(final User excepted) {
        return new TypeSafeMatcher<User>() {
            @Override
            protected boolean matchesSafely(User user) {
                return user.getId().equals(excepted.getId()) &&
                        user.getPassword().equals(excepted.getPassword()) &&
                        user.getFirstName().equals(excepted.getFirstName()) &&
                        user.getLastName().equals(excepted.getLastName());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(excepted.toString());
            }
        };
    }
}
