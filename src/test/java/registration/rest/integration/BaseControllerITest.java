package registration.rest.integration;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseControllerITest {
    private static int port = 7900;
    private static String contextPath = "/registration";
    private static Server server;


    @BeforeClass
    public static void beforeClass() throws Exception {
        startJetty();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        stopJetty();
    }

    private static void startJetty() throws Exception {
        server = new Server();

        SocketConnector connector = new SocketConnector();
        connector.setPort(port);

        WebAppContext context = new WebAppContext();
        context.setServer(server);
        context.setContextPath(contextPath);
        context.setWar("src/main/webapp");

        server.setConnectors(new Connector[]{connector});
        server.setHandler(context);
        server.start();
    }

    private static void stopJetty() throws Exception {
        server.stop();
    }

    protected static String getRestEndpoint(String path) {
        return String.format("http://localhost:%1$d%2$s/rest/%3$s", port, contextPath, path);
    }

    protected static String getRestEndpoint(String path, String param) {
        return String.format("http://localhost:%1$d%2$s/rest/%3$s/%4$s", port, contextPath, path, param);
    }
}
