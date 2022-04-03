package drills;

import base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class DrillsPage extends BasePage {
    private String oldPrice = "//div[@class='price-values']//span[@class='price-old']",
            actualPrice = "//div[@class='price']";

    public void checkDrillsWithDiscountToHaveOldAndNewPrices(int amountsOfDrillsToCheck) {
        int counter = 0;
        while (counter < amountsOfDrillsToCheck) {
            ElementsCollection drillsWithDiscount =
                    $$x("//div[@class='sticker discount flaticon-sale']/ancestor::div[@class='photo']");
            if (drillsWithDiscount.size() == 0){
                clickOnNextPageArrowIfPresent();
            }
            else {
                counter += drillsWithDiscount.size();
                presenceOfOldAndNewPriceForOneDrillModel(amountsOfDrillsToCheck, drillsWithDiscount);
                if (counter < amountsOfDrillsToCheck) {
                    clickOnNextPageArrowIfPresent();
                }
            }
        }
    }

    private void presenceOfOldAndNewPriceForOneDrillModel(int amountOfChosenGoods, ElementsCollection presentDrills) {
        for (int i = 0; i < Math.min(amountOfChosenGoods, presentDrills.size()); i++) {
            presentDrills.get(i).scrollTo().click();
            $x(oldPrice).shouldHave(Condition.text("грн"));
            $x(actualPrice).shouldHave(Condition.text("грн"));
            back();
        }
    }
}
