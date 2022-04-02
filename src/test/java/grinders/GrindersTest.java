package grinders;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GrindersTest extends BaseTest {
    GrindersPage grindersPage = new GrindersPage();

    @BeforeClass
    public void beforeDrillsTest() {
        grindersPage.moveToGrindersFromPowerTools();
    }

    @Test
    public void verifyCalculatedPromotionalPricesOfGrinders(){
        grindersPage.checkGrindersWithDiscountToHaveCorrectNewPrice(10);

    }

}
