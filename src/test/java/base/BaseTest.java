package base;

import constants.Hosts;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    @BeforeSuite
    public void init(){
        open(Hosts.PLANETA_INSTRUMENT.getPath());
    }


}
