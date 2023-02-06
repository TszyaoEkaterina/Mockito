import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.Random;

public class GeoServiceImplTest {
    GeoServiceImpl geoService = new GeoServiceImpl();
    Random random = new Random();

    @Test
    public void testWithMoscowIP() {
        Location location = geoService.byIp(GeoServiceImpl.MOSCOW_IP);
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    public void testWithNewYorkIP() {
        Location location = geoService.byIp(GeoServiceImpl.NEW_YORK_IP);
        Assertions.assertEquals(Country.USA, location.getCountry());
    }

    @Test
    public void testWithRandomRussianIP() {
        String russianIp = "172." + random.nextInt(255) + "."
                + random.nextInt(255) + "." + random.nextInt(255);
        Location location = geoService.byIp(russianIp);
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    public void testWithRandomUsaIP() {
        String usaIp = "96." + random.nextInt(255) + "."
                + random.nextInt(255) + "." + random.nextInt(255);
        Location location = geoService.byIp(usaIp);
        Assertions.assertEquals(Country.USA, location.getCountry());
    }

    @Test
    public void testWithLocalHost() {
        Location location = geoService.byIp(GeoServiceImpl.LOCALHOST);
        Assertions.assertNull(location.getCountry());
    }
}
