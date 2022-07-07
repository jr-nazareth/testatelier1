package fr.inazareth.test.atelier1.core.http;

import fr.inazareth.test.atelier1.core.exception.BusinessException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class NoEndPointServlet extends RestServlet {

    @Override
    protected Object executeRequest(HttpServletRequest r, HttpServletResponse re) throws Exception {
        throw new BusinessException("Invalid endpoint");
    }
}
