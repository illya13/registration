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

    private User expectedUser;
    private String expectedToken;

    @Before
    public void before() {
        User.Builder builder = new User.Builder();
        expectedUser = builder.newUser("user").setPassword("qwerty").
                setFirstName("first").setLastName("last").
                build();
        expectedToken = "token";
    }

    @Test
    public void loginOkTest() {
        given(userService.auth(expectedUser.getId(), expectedUser.getPassword())).willReturn(expectedUser);
        given(sessionService.generateToken(expectedUser.getId())).willReturn(expectedToken);

        //when
        String token = userConstroller.login(expectedUser.getId(), expectedUser.getPassword());

        // then
        assertThat(token, is(expectedToken));
    }

    @Test(expected = IllegalArgumentException.class)
    public void loginFailedTest() {
        given(userService.auth(expectedUser.getId(), expectedUser.getPassword())).willThrow(new IllegalArgumentException());
        given(sessionService.generateToken(expectedUser.getId())).willReturn(expectedToken);

        //when
        userConstroller.login(expectedUser.getId(), expectedUser.getPassword());

        // then exception
    }
}
