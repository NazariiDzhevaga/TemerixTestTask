package perforators;

import base.BasePage;
import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;

public class PerforatorsPage extends BasePage {

    String PerforatorsBuyBlockOnPage = "//div[@class='catalog-item-wrap']//span[@class='price']";

    public PerforatorsPage checkPricePresenceForPerforators(int pageCount) {
        for (int i = 0; i < pageCount; i++) {
            ElementsCollection perforators = $$x(PerforatorsBuyBlockOnPage);
            for (int j=0; j<perforators.size(); j++) {
                perforators.get(j).shouldBe(Condition.visible).shouldHave(Condition.text("грн."));
            }
            clickOnNextPageArrowIfPresent();
        }
        return this;
    }
}
