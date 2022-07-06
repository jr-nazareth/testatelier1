package fr.inazareth.test.atelier1.core.exception;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class RestException {

    private String type;
    private String message;

    public String type() {
        return type;
    }

    public String message() {
        return message;
    }

    public static RestException create(Exception ex) {
        RestException e = new RestException();
        e.type = ex.getClass().getSimpleName();
        e.message = ex.getLocalizedMessage();
        return e;
    }
}
