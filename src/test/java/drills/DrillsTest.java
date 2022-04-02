package drills;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DrillsTest extends BaseTest {
    DrillsPage drillsPage = new DrillsPage();
    @BeforeClass
    public void beforeDrillsTest(){
        drillsPage.moveToDrillsFromPowerTools();
    }

    @Test
    public void verifyThatAllDiscountedDrillsHaveTwoPrices(){
        drillsPage.checkDrillsWithDiscountToHaveOldAndNewPrices(15);


    }
}
