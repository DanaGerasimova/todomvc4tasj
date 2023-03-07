package how.autotest.todomvc4tasj;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.*;

public class TestSetUp {
    @BeforeEach
    public void setUp(){
        open("https://todomvc4tasj.herokuapp.com/");

        Wait().until(ExpectedConditions.jsReturnsValue(
                "return $._data($('#new-todo').get(0), 'events').hasOwnProperty('keyup');"));
    }
}
