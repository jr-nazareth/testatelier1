package fr.inazareth.test.atelier1.business.player.service;

import fr.inazareth.test.atelier1.Application;
import fr.inazareth.test.atelier1.business.player.Player;
import fr.inazareth.test.atelier1.core.RestService;
import fr.inazareth.test.atelier1.core.exception.BusinessException;
import java.util.List;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class PlayerSimpleService extends PlayerService implements RestService<Player> {

    public PlayerSimpleService(Application application) {
        super(application);
    }
    
    @Override
    public List<Player> root() throws Exception {
        return container();
    }

    @Override
    public Player get(Integer id) throws Exception {
        if (id == null) {
            throw new BusinessException("Id is null");
        }

        return container().stream()
                .filter(p -> p.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Player post(Player p) throws Exception {
        if (container().contains(p)) {
            throw new BusinessException("Already exist");
        }
        
        container().add(p);

        container().sort((Player p1, Player p2) -> {
            Integer i1 = p1.data() != null ? p1.data().rank() : 0;
            Integer i2 = p2.data() != null ? p2.data().rank() : 0;
            
            return Integer.compare(i1, i2);
        });

        return p;
    }

    @Override
    public Player put(Player p) throws Exception {

        int i = container().indexOf(p);
        if (i == -1) {
            throw new BusinessException("Not exist");
        }
        container().set(i, p);
        return p;
    }

    @Override
    public Player delete(Integer id) throws Exception {
        if (id == null) {
            throw new BusinessException("Id is null");
        }

        Player p = get(id);
        if (p == null) {
            throw new BusinessException("Not exist");
        }
        container().remove(p);
        return p;
    }

    @Override
    public Integer parseId(String str) throws Exception {
        try{
            return Integer.parseInt(str);
        }catch(NumberFormatException ex){
            throw new BusinessException("Invalid parameter", ex);
        }
    }
}
