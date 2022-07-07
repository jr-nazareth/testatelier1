package fr.inazareth.test.atelier1.core.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.inazareth.test.atelier1.core.ObjectMapperFactory;
import fr.inazareth.test.atelier1.core.exception.BusinessException;
import fr.inazareth.test.atelier1.core.exception.RestException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public abstract class RestServlet extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        doRequest(r, re);
    }

    @Override
    protected void doPut(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        doRequest(r, re);
    }

    @Override
    protected void doPost(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        doRequest(r, re);
    }

    @Override
    protected void doGet(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        doRequest(r, re);
    }

    protected abstract Object executeRequest(HttpServletRequest r, HttpServletResponse re) throws Exception;

    protected void doRequest(HttpServletRequest r, HttpServletResponse re) {
        try {
            Object ore = executeRequest(r, re);
            onSuccess(ore, re);
        } catch (Exception ex) {
            onException(ex, re);
        }
    }

    protected void onSuccess(Object o, HttpServletResponse re) throws IOException {
        if (o != null) {
            re.getWriter().print(toJsonString(o));
        }
        re.setStatus(HttpServletResponse.SC_OK);
    }

    protected String toJsonString(Object o) throws JsonProcessingException {
        ObjectMapper om = ObjectMapperFactory.create();
        return om.writeValueAsString(o);
    }

    protected void onException(Exception ex, HttpServletResponse re) {
        if (ex instanceof BusinessException) {
            BusinessException bex = (BusinessException) ex;
            try {
                re.getWriter().write(toJsonString(RestException.create(bex)));
                re.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } catch (IOException ex1) {
                onInternalException(ex, re);
            }
        } else {
            try {
                Logger.getLogger(RestServlet.class.getName()).log(Level.SEVERE, null, ex);
                re.getWriter().write(toJsonString(RestException.create(new Exception("Service has error"))));
                re.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } catch (IOException ex1) {
                onInternalException(ex, re);
            }
        }
    }

    protected void onInternalException(Exception ex, HttpServletResponse re) {
        Logger.getLogger(RestServlet.class.getName()).log(Level.SEVERE, null, ex);
        re.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

}
