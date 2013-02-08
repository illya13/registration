package registration.rest.integration;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import registration.domain.User;

@ContextConfiguration(locations = {"classpath:spring/rest-client-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerITest extends BaseControllerITest {
    @Autowired
    RestTemplate restTemplate;

    @Test()
    public void testGetRestEndpoint() throws Exception {
        Assert.assertEquals("http://localhost:7900/registration/rest/user/root", getRestEndpoint("user", "root"));
    }

    @Test(expected = HttpServerErrorException.class)
    public void testCreateUser() throws Exception {
        User response = restTemplate.postForObject(getRestEndpoint("user"), expectedUser, User.class);
        Assert.assertNotNull(response);
        Assert.assertEquals(expectedUser.getId(), response.getId());
        Assert.assertEquals(expectedUser.getFirstName(), response.getFirstName());
        Assert.assertEquals(expectedUser.getLastName(), response.getLastName());
    }

    @Test()
    public void testGetUser() throws Exception {
        User response = restTemplate.getForObject(getRestEndpoint("user", expectedUser.getId(), "?token={token}"),
                User.class, expectedToken);
        Assert.assertNotNull(response);
        Assert.assertEquals(expectedUser.getId(), response.getId());
        Assert.assertNotNull(response.getFirstName());
        Assert.assertNotNull(response.getLastName());
    }

    @Test()
    public void testGetUsers() throws Exception {
        User[] response = restTemplate.getForObject(getRestEndpoint("user")+"?token={token}",
                User[].class, expectedToken);
        Assert.assertNotNull(response);
        Assert.assertEquals(1, response.length);
        Assert.assertEquals(expectedUser.getId(), response[0].getId());
        Assert.assertNotNull(response[0].getFirstName());
        Assert.assertNotNull(response[0].getLastName());
    }

    @Test()
    public void testDeleteUser() throws Exception {
        restTemplate.delete(getRestEndpoint("user", expectedUser.getId(), "?token={token}"), expectedToken);
    }
}
