import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MessageSenderTest {
    GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
    LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
    Random random = new Random();

    @Test
    public void russianMessageForMoscowIp() {
        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-real-ip", GeoServiceImpl.MOSCOW_IP);
        Assertions.assertEquals("Добро пожаловать", messageSender.send(headers));
    }

    @Test
    public void englishMessageForNewYorkIp() {
        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-real-ip", GeoServiceImpl.NEW_YORK_IP);
        Assertions.assertEquals("Welcome", messageSender.send(headers));
    }

    @Test
    public void russianMessageForRandomRussianIp() {
        String russianIp = "172." + random.nextInt(255) + "."
                + random.nextInt(255) + "." + random.nextInt(255);
        Mockito.when(geoService.byIp(russianIp))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-real-ip", russianIp);
        Assertions.assertEquals("Добро пожаловать", messageSender.send(headers));
    }

    @Test
    public void englishMessageForRandomUsaIp() {
        String usaIp = "96." + random.nextInt(255) + "."
                + random.nextInt(255) + "." + random.nextInt(255);
        Mockito.when(geoService.byIp(usaIp))
                .thenReturn(new Location("New York", Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-real-ip", usaIp);
        Assertions.assertEquals("Welcome", messageSender.send(headers));
    }

    @Test
    public void englishMessageForLocalHostIp() {
        Mockito.when(geoService.byIp(GeoServiceImpl.LOCALHOST))
                .thenReturn(new Location(null, null, null, 0));
        Mockito.when(localizationService.locale(null))
                .thenReturn("Welcome");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-real-ip", GeoServiceImpl.LOCALHOST);
        Assertions.assertEquals("Welcome", messageSender.send(headers));
    }
}
