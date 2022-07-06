package fr.inazareth.test.atelier1.business.player;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class PlayersStat {

    private Integer imc;
    private Integer median;
    private PlayerCountry topCountry;

    public Integer imc() {
        return imc;
    }

    public void imc(Integer imc) {
        this.imc = imc;
    }

    public Integer median() {
        return median;
    }

    public void median(Integer median) {
        this.median = median;
    }

    public PlayerCountry topCountry() {
        return topCountry;
    }

    public void topCountry(PlayerCountry topCountry) {
        this.topCountry = topCountry;
    }
}
