package registration.rest.integration;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(locations = {"classpath:spring/rest-client-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerITest extends BaseControllerITest {
    @Autowired
    RestTemplate restTemplate;

    @Test()
    public void testGetRestEndpoint() throws Exception {
        Assert.assertEquals("http://localhost:7900/registration/rest/user/root", getRestEndpoint("user", "root"));
    }
}
