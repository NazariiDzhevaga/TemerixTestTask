package perforators;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PerforatorsTest extends BaseTest {

    PerforatorsPage perforatorsPage = new PerforatorsPage();

    @BeforeClass
    public void beforeDrillsTest() {
        perforatorsPage.moveToPerforatorsFromPowerTools();
    }

    @Test
    public void checkPerforatesPricePresence(){
        perforatorsPage.checkPricePresenceForPerforators(5);
    }
}
