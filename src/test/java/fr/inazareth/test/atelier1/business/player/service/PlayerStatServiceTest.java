package fr.inazareth.test.atelier1.business.player.service;

import fr.inazareth.test.atelier1.Application;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class PlayerStatServiceTest {

    private Application application;

    @Before
    public void init() throws Exception {
        application = new Application.Builder().build();
        application.init();
    }

    @Test
    public void median() throws Exception {
        PlayerStatService s = application.createService(PlayerStatService.class);
        assertEquals(s.median().intValue(), 185);
    }

    @Test
    public void imc() throws Exception {
        PlayerStatService s = application.createService(PlayerStatService.class);
        assertEquals(s.imc().intValue(), 24);
    }

    @Test
    public void topCountry() throws Exception {
        PlayerStatService s = application.createService(PlayerStatService.class);
        assertEquals(s.topCountry().code(), "SRB");
    }

    @After
    public void finish() {
        application.storage().clear();
        application = null;
    }
}
