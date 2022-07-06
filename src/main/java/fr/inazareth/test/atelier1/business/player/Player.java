package fr.inazareth.test.atelier1.business.player;

import java.net.URI;
import java.util.Objects;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class Player {

    private Integer id;
    private String firtname;
    private String lastname;
    private String sex;
    private PlayerCountry country;
    private URI picture;
    private PlayerData data;

    public Integer id() {
        return id;
    }

    public void id(Integer id) {
        this.id = id;
    }

    public String firtname() {
        return firtname;
    }

    public void firtname(String firtname) {
        this.firtname = firtname;
    }

    public String lastname() {
        return lastname;
    }

    public void lastname(String lastname) {
        this.lastname = lastname;
    }

    public String sex() {
        return sex;
    }

    public void sex(String sex) {
        this.sex = sex;
    }

    public PlayerCountry country() {
        return country;
    }

    public void country(PlayerCountry country) {
        this.country = country;
    }

    public URI picture() {
        return picture;
    }

    public void picture(URI picture) {
        this.picture = picture;
    }

    public PlayerData data() {
        return data;
    }

    public void data(PlayerData data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Player other = (Player) obj;
        return Objects.equals(this.id, other.id);
    }

}
