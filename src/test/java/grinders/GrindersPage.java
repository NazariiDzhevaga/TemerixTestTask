package grinders;

import base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class GrindersPage extends BasePage {
    private final String grindersOnPageWithStock = "//div[@class='sticker discount flaticon-sale']/ancestor::div[@class='photo']";
    private final String percentageOfDiscount = "//div[@class='sticker price-w-discount']";
    private final String oldPriceOfModelLocator = "//div[@class='price-values']//span[@class='price-old']";
    private final String newPriceLocator = "//div[@class='price']";
    private final String nameOfModelLocator = "//div[@class='main-header']";

    private double numberOfPercentageWithoutSymbols;
    private int numberOfOldPrice;
    private int newPrice;



    private GrindersPage selectRandomGrinderModel() {
        ElementsCollection grindersAmount = $$x(grindersOnPageWithStock);
        int selectedModel = new Random().nextInt(grindersAmount.size());
        grindersAmount.get(selectedModel).click();
        return this;
    }

    private GrindersPage findPercentageOfDiscount() {
        String percentageWithoutSymbols = $x(percentageOfDiscount).getText().
                replaceAll("[^\\d.]", "");
        numberOfPercentageWithoutSymbols = Integer.parseInt(percentageWithoutSymbols);
        return this;
    }

    private GrindersPage findPromotionalPrice() {
        String oldPriceOfModel = $x(oldPriceOfModelLocator).scrollTo().shouldBe(Condition.visible).text().
                replaceAll("[^\\d]", "");
        numberOfOldPrice = Integer.parseInt(oldPriceOfModel);
        return this;
    }

    private GrindersPage findNewPrice() {
        String newPriceModel = $x(newPriceLocator).getAttribute("outerText").replaceAll("[^\\d]", "");
        newPrice = Integer.parseInt(newPriceModel);
        return this;
    }

    private void checkCorrectPriceDetails() {
        double expectedPriceFromOldOne = ((100 - numberOfPercentageWithoutSymbols) / 100) * numberOfOldPrice;
        assertTrue(Math.abs(expectedPriceFromOldOne-newPrice) <= newPrice * 0.01,
                "\nModel:" + $x(nameOfModelLocator).getText() +
                        "\nExpected price = " + expectedPriceFromOldOne +
                        "\nActual price = " + newPrice);

    }

    private void verifyCalculationPriceOfGrinders(int amountOfRandomGrinders) {
        for (int i = 0; i < amountOfRandomGrinders; i++) {
                    selectRandomGrinderModel();
                    findPercentageOfDiscount();
                    findPromotionalPrice();
                    findNewPrice();
                    checkCorrectPriceDetails();
                    back();
        }
    }

    public void checkGrindersWithDiscountToHaveCorrectNewPrice(int amountsOfGrindersToCheck) {
        int counter = 0;
        while (counter < amountsOfGrindersToCheck) {
            ElementsCollection grindersWithDiscount = $$x(grindersOnPageWithStock);
            if (grindersWithDiscount.size() == 0){
                clickOnNextPageArrowIfPresent();
            }
            else {
                counter += grindersWithDiscount.size();
                verifyCalculationPriceOfGrinders(amountsOfGrindersToCheck);
                if (counter < amountsOfGrindersToCheck) {
                    clickOnNextPageArrowIfPresent();
                }
            }
        }
    }

}
