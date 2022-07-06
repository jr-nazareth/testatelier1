package fr.inazareth.test.atelier1.business.player.service;

import fr.inazareth.test.atelier1.Application;
import fr.inazareth.test.atelier1.business.player.Player;
import fr.inazareth.test.atelier1.core.exception.BusinessException;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class PlayerSimpleServiceTest {

    private Application application;

    @Before
    public void init() throws Exception {
        application = new Application.Builder().build();
        application.init();
    }

    /**
     * Test de la modification, ainsi que ses cas d'exception
     *
     * @throws java.lang.Exception
     */
    @Test()
    public void put() throws Exception {

        PlayerSimpleService s = application.createService(PlayerSimpleService.class);

        Player p = new Player();
        p.id(2);
        p.lastname("le last name");

        try {
            s.put(p);
            Assert.fail("Acuune BusinessException");
        } catch (BusinessException ex) {
        }

        s.post(p);

        String newName = "le last name2";

        p = new Player();
        p.id(2);
        p.lastname(newName);

        s.put(p);

        assertEquals(newName, s.get(p.id()).lastname());
    }

    /**
     * Test de l'ajout, ainsi que ses cas d'exception
     *
     * @throws java.lang.Exception
     */
    @Test()
    public void post() throws Exception {
        PlayerSimpleService s = application.createService(PlayerSimpleService.class);

        Player p = new Player();
        p.id(1);
        p.lastname("le last name");

        s.post(p);

        assertNotNull("Is null", s.get(p.id()));

        try {
            s.post(p);
            Assert.fail("Aucune BusinessException");
        } catch (BusinessException ex) {
        }
    }

    /**
     * Test de la suppression, ainsi que ses cas d'exception
     *
     * @throws java.lang.Exception
     */
    @Test()
    public void delete() throws Exception {
        PlayerSimpleService s = application.createService(PlayerSimpleService.class);

        Integer id = 52;
        assertNotNull("le joueur manque", s.get(id));

        s.delete(id);

        assertNull("le joueur ne devrait plus être la", s.get(id));

        try {
            s.delete(id);
            Assert.fail("Aucune BusinessException");
        } catch (BusinessException ex) {
        }
    }

    @After
    public void finish() {
        application.storage().clear();
        application = null;
    }
}
