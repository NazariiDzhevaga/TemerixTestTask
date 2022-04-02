package base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BasePage {
    private String powerTools = "//a[@href='/catalog/elektroinstrument/' and @class='menu-lvl0-link']";

    enum PowerToolsSection {
        DRILLS("//a[@href='/catalog/dreli/' and @class='menu-lvl1-link ']", "Дрели"),
        GRINDERS("//a[@href='/catalog/bolgarki/' and @class='menu-lvl1-link ']", "Болгарки (Угловые шлифмашины)"),
        PERFORATORS("//a[@href='/catalog/perforatory/' and @class='menu-lvl1-link ']", "Перфораторы");

        private String locator;
        private String expectedName;

        public String getLocator() {
            return locator;
        }

        public String getExpectedName() {
            return expectedName;
        }

        PowerToolsSection(String locator, String expectedName) {
            this.locator = locator;
            this.expectedName = expectedName;
        }
    }

    private BasePage hoverOnPowerTools() {
        $x(powerTools).shouldBe(Condition.visible).hover();
        return this;
    }

    private BasePage moveToSectionFromPowerTools(PowerToolsSection section) {
        hoverOnPowerTools();
        $x(section.getLocator()).shouldBe(Condition.visible).click();
        $x("//main[@class='container catalog-page']").shouldHave(Condition.text(section.getExpectedName()));
        return this;
    }

    public BasePage moveToDrillsFromPowerTools() {
        moveToSectionFromPowerTools(PowerToolsSection.DRILLS);
        return this;
    }

    public BasePage moveToGrindersFromPowerTools() {
        moveToSectionFromPowerTools(PowerToolsSection.GRINDERS);
        return this;
    }

    public BasePage moveToPerforatorsFromPowerTools() {
        moveToSectionFromPowerTools(PowerToolsSection.PERFORATORS);
        return this;
    }

    public BasePage clickOnNextPageArrowIfPresent() {
        $x("//a[@class='pagination-item arrow next']//i[contains(@class, 'flaticon')]")
                .shouldBe(Condition.enabled).hover().click();
        return this;



    }


}
