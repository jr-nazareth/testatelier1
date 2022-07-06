package fr.inazareth.test.atelier1.business.player.service;

import fr.inazareth.test.atelier1.Application;
import fr.inazareth.test.atelier1.business.player.Player;
import fr.inazareth.test.atelier1.core.ObjectService;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public abstract class PlayerService extends ObjectService<Player> {

    public PlayerService(Application application) {
        super(application, Player.class);
    }

    @Override
    public Class<Player> type() {
        return Player.class;
    }
}
