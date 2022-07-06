package fr.inazareth.test.atelier1.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.inazareth.test.atelier1.Application;
import fr.inazareth.test.atelier1.core.ObjectMapperFactory;
import fr.inazareth.test.atelier1.core.ObjectService;
import fr.inazareth.test.atelier1.core.RestService;
import fr.inazareth.test.atelier1.core.SimpleRestService;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jean-Raffi Nazareth
 * @param <T> Toute implémentation de ObjectService
 */
public class ServiceRestServlet<T extends ObjectService> extends RestServlet {

    protected Class<T> serviceType;
    protected Application application;

    public ServiceRestServlet(Application application, Class<T> serviceType) {
        this.serviceType = serviceType;
        this.application = application;
    }

    @Override
    protected Object executeRequest(HttpServletRequest r, HttpServletResponse re) throws Exception {

        RestMethod m = RestMethod.valueOf(r.getMethod());
        T s = application.createService(serviceType);

        String formatedPathInfo = r.getPathInfo() == null ? "/" : r.getPathInfo();
        Path path = Paths.get(formatedPathInfo);

        if (path.getParent() == null) {
            switch (m) {
                case GET:
                    if (s instanceof SimpleRestService) {
                        return ((SimpleRestService) s).root();
                    }
                    break;
            }
            if (s instanceof RestService) {
                RestService rs = (RestService) s;
                ObjectMapper om = ObjectMapperFactory.create();
                switch (m) {
                    case POST:
                        return rs.post(om.readValue(r.getInputStream(), rs.type()));

                    case PUT:
                        return rs.put(om.readValue(r.getInputStream(), rs.type()));
                }
            }
        } else {
            if (s instanceof RestService) {
                RestService rs = (RestService) s;
                Integer id = rs.parseId(path.getFileName().toString());
                switch (m) {
                    case GET:
                        return rs.get(id);
                    case DELETE:
                        return rs.delete(id);
                }
            }
        }

        re.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return null;
    }
}
