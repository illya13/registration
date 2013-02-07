package registration.domain;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


public class UserTest {
    @Test
    public void userBuilderTest() {
        User.Builder builder = new User.Builder();
        User root = builder.newUser("root").setPassword("qwerty").
                setFirstName("first").setLastName("last").
                build();

        assertThat(root.getId(), is("root"));
        assertThat(root.getPassword(), is("qwerty"));
        assertThat(root.getFirstName(), is("first"));
        assertThat(root.getLastName(), is("last"));
    }
}
