package fr.inazareth.test.atelier1;

import fr.inazareth.test.atelier1.business.player.service.PlayerInitialiserService;
import fr.inazareth.test.atelier1.core.ObjectService;
import fr.inazareth.test.atelier1.core.storage.ObjectStorage;
import fr.inazareth.test.atelier1.core.http.ServiceRestServlet;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.Deflater;
import javax.servlet.Servlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class Application {

    public static class Builder {

        private final Application application;

        private String httpHost = "localhost";
        private Integer httpPort = 9988;

        private final ServletContextHandler contextHandler = new ServletContextHandler();

        public Builder() {
            application = new Application(new Server());
        }

        public Builder configure(String[] args) {

            boolean skipNext = false;
            for (int i = 0; i < args.length; i++) {
                if (skipNext) {
                    skipNext = false;
                    continue;
                }
                switch (args[i]) {
                    case "--http-host" -> {
                        httpHost = args[i + 1];
                        skipNext = true;
                    }
                    case "--http-port" -> {
                        httpPort = Integer.parseInt(args[i + 1]);
                        skipNext = true;
                    }
                }
            }
            return this;
        }

        public <T extends ObjectService> Builder addEndPoint(Class<T> c, String path) throws Exception {
            ServiceRestServlet<T> servlet = new ServiceRestServlet<>(application, c);
            ServletHolder holder = new ServletHolder(servlet);

            contextHandler.addServlet(holder, path);
            return this;
        }

        public <T extends ObjectService> Builder addEndPoint(Servlet s, String path) throws Exception {
            ServletHolder holder = new ServletHolder(s);

            contextHandler.addServlet(holder, path);
            return this;
        }

        final public Application build() {

            // HttpServer
            Server server = application.httpServer();

            ServerConnector connector = new ServerConnector(server);
            connector.setPort(httpPort);
            connector.setHost(httpHost);

            server.setConnectors(new Connector[]{connector});

            // GZip response
            GzipHandler gzipHandler = new GzipHandler();
            gzipHandler.setCompressionLevel(Deflater.DEFAULT_COMPRESSION);
            gzipHandler.setHandler(contextHandler);

            server.setHandler(gzipHandler);

            // Main Application
            return application;
        }
    }

    private final ObjectStorage storage;
    private final Server httpServer;

    private Application(Server httpServer) {
        storage = new ObjectStorage();
        this.httpServer = httpServer;
    }

    protected Server httpServer() {
        return httpServer;
    }

    public ObjectStorage storage() {
        return storage;
    }

    public <T extends ObjectService> T createService(Class<T> cl)
            throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException {
        return cl.getConstructor(Application.class).newInstance(this);
    }
    
    public void init() throws Exception{
        createService(PlayerInitialiserService.class).init();
    }
    
    public void start() throws Exception {
        init();
        httpServer.start();
    }

    public void stop() throws Exception {
        httpServer.stop();
    }
}
