package registration.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import registration.domain.User;
import registration.service.SessionService;
import registration.service.UserService;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @Mock
    private SessionService sessionService;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userConstroller;

    private User expected;

    @Before
    public void before() {
        User.Builder builder = new User.Builder();
        expected = builder.newUser("user").setPassword("qwerty").
                setFirstName("first").setLastName("last").
                build();
    }

    @Test
    public void loginOkTest() {
        given(userService.auth(expected.getId(), expected.getPassword())).willReturn(expected);

        // when
        String token = userConstroller.login(expected.getId(), expected.getPassword());

        // then
        assertThat(token, is(isNotNull()));
    }

    @Test
    public void loginFailedTest() {
        given(userService.auth(expected.getId(), expected.getPassword())).willThrow(IllegalArgumentException.class);
        when(userConstroller.login(expected.getId(), expected.getPassword())).thenThrow(new IllegalArgumentException());
    }
}
