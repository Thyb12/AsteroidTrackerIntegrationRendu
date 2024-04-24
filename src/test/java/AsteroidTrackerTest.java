import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AsteroidTrackerTest {

    @Test
    public void testCalculateAsteroidDistance() throws IOException, ParseException {
        // Données d'entrée
        List<Asteroid> list = new ArrayList<>();
        // Appel de la méthode à tester
        List<Asteroid> distance = Panel1.getAsteroids("3000-01-12");

        // Vérification du résultat
        assertEquals(distance,list);
    }
}
