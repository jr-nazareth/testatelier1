package fr.inazareth.test.atelier1.business.player;

import java.util.List;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class PlayerData {

    private Integer rank;
    private Integer points;
    private Integer weight;
    private Integer height;
    private Integer age;
    private List<Integer> last;

    public Integer rank() {
        return rank;
    }

    public void rank(Integer rank) {
        this.rank = rank;
    }

    public Integer points() {
        return points;
    }

    public void points(Integer points) {
        this.points = points;
    }

    public Integer weight() {
        return weight;
    }

    public void weight(Integer weight) {
        this.weight = weight;
    }

    public Integer height() {
        return height;
    }

    public void height(Integer height) {
        this.height = height;
    }

    public Integer age() {
        return age;
    }

    public void age(Integer age) {
        this.age = age;
    }

    public List<Integer> last() {
        return last;
    }

    public void last(List<Integer> last) {
        this.last = last;
    }
}
