package fr.inazareth.test.atelier1.business.player.service;

import fr.inazareth.test.atelier1.business.player.PlayersStat;
import fr.inazareth.test.atelier1.Application;
import fr.inazareth.test.atelier1.business.player.Player;
import fr.inazareth.test.atelier1.business.player.PlayerCountry;
import fr.inazareth.test.atelier1.core.ObjectService;
import fr.inazareth.test.atelier1.core.SimpleRestService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class PlayerStatService extends ObjectService<Player> implements SimpleRestService<PlayersStat> {

    public PlayerStatService(Application application) {
        super(application, Player.class);
    }

    /**
     *
     * Calcule l' IMC pour un joueur
     *
     * @param p Joueur
     *
     * @return Integer
     */
    protected Integer imc(Player p) {
        if (p.data() == null) {
            return null;
        }
        return imc(p.data().weight(), p.data().height());
    }

    protected Integer imc(Integer weight, Integer height) {
        double kg = weight.doubleValue() / 1000;
        double m = height.doubleValue() / 100;
        Double imc = kg / Math.pow(m, 2);
        Long limc = Math.round(imc);

        return limc.intValue();
    }

    /**
     *
     * Calcule l' IMC moyen de tous les joueurs
     *
     * @return Integer
     */
    public Integer imc() {
        double dimc = container()
                .stream()
                .flatMapToInt(p -> IntStream.of(imc(p)))
                .average().orElse(-1.0);
        if (dimc == -1.0) {
            return null;
        }

        Long limc = Math.round(dimc);
        return limc.intValue();
    }

    /**
     * Calcule la médiane de la taille des joueurs
     *
     * @return Integer
     */
    public Integer median() {
        int[] heights = container()
                .stream()
                .flatMapToInt(p -> IntStream.of(p.data().height()))
                .sorted()
                .toArray();

        int i = (heights.length + 1) / 2;
        if (heights.length > 0) {
            return heights[i];
        }
        return null;
    }

    /**
     * Déduis le pays qui a le plus grand ratio de parties gagnées
     *
     * @return PlayerCountry
     */
    public PlayerCountry topCountry() {

        Map<PlayerCountry, List<Integer>> aggs = new HashMap<>();
        for (Player p : container()) {
            if (aggs.containsKey(p.country())) {
                aggs.get(p.country()).addAll(p.data().last());
            } else {
                aggs.put(p.country(), new ArrayList<>(p.data().last()));
            }
        }

        ArrayList<Map.Entry<PlayerCountry, List<Integer>>> pls = new ArrayList<>(aggs.entrySet());
        pls.sort((e1, e2) -> {
            Integer s1 = e1.getValue().stream().reduce(0, Integer::sum);
            Integer s2 = e2.getValue().stream().reduce(0, Integer::sum);

            Integer r1 = s1 / e1.getValue().size();
            Integer r2 = s2 / e2.getValue().size();

            return Integer.compare(r2, r1);
        });

        return pls.get(0).getKey();
    }

    @Override
    public PlayersStat root() throws Exception {
        PlayersStat ps = new PlayersStat();
        ps.imc(imc());
        ps.median(median());
        ps.topCountry(topCountry());
        return ps;
    }
}
