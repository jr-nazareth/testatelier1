package fr.inazareth.test.atelier1.business.player.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.inazareth.test.atelier1.Application;
import fr.inazareth.test.atelier1.business.player.HeadToHeadFile;
import fr.inazareth.test.atelier1.business.player.Player;
import fr.inazareth.test.atelier1.core.ObjectMapperFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class PlayerInitialiserService extends PlayerService {

    public PlayerInitialiserService(Application application) {
        super(application);
    }

    /**
     *
     * Initialise le stockage avec la ressource fournis
     * 
     * @return List
     * @throws java.lang.Exception
     */
    public List<Player> init() throws Exception {

        application().storage().getOrCreate(Player.class);

        ObjectMapper om = ObjectMapperFactory.create();

        InputStream is = Player.class.getResourceAsStream("resource/headtohead.json");
        HeadToHeadFile hth = om.readValue(is, HeadToHeadFile.class);

        PlayerSimpleService pss = application().createService(PlayerSimpleService.class);

        List<Player> ps = new ArrayList<>();
        for (Player p : hth.players()) {
            ps.add(pss.post(p));
        }
        return ps;
    }

}
