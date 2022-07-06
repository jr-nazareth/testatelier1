package fr.inazareth.test.atelier1.business.player;

import java.net.URI;
import java.util.Objects;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class PlayerCountry {

    private URI picture;
    private String code;

    public URI picture() {
        return picture;
    }

    public void picture(URI picture) {
        this.picture = picture;
    }

    public String code() {
        return code;
    }

    public void code(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerCountry other = (PlayerCountry) obj;
        return Objects.equals(this.code, other.code);
    }
}
