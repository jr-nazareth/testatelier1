package fr.inazareth.test.atelier1;

import fr.inazareth.test.atelier1.business.player.service.PlayerSimpleService;
import fr.inazareth.test.atelier1.business.player.service.PlayerStatService;
import fr.inazareth.test.atelier1.core.http.NoEndPointServlet;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class Launcher {

    public static void main(String[] args) throws Exception {
        Application a = new Application.Builder()
                .configure(args)
                .addEndPoint(PlayerSimpleService.class, "/players/*")
                .addEndPoint(PlayerStatService.class, "/stat/*")
                .addEndPoint(new NoEndPointServlet(), "/*")
                .build();
        a.start();
    }
}
