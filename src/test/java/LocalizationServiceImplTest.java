import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.i18n.LocalizationServiceImpl;

import static ru.netology.entity.Country.*;

public class LocalizationServiceImplTest {
    LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    public void massageForRussia() {
        String message = localizationService.locale(RUSSIA);
        Assertions.assertEquals("Добро пожаловать", message);
    }

    @Test
    public void massageForUSA() {
        String message = localizationService.locale(USA);
        Assertions.assertEquals("Welcome", message);
    }

    @Test
    public void massageForGermany() {
        String message = localizationService.locale(GERMANY);
        Assertions.assertEquals("Welcome", message);
    }

    @Test
    public void massageForBrazil() {
        String message = localizationService.locale(BRAZIL);
        Assertions.assertEquals("Welcome", message);
    }
}
