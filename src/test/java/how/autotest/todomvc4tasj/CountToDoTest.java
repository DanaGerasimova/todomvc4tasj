package how.autotest.todomvc4tasj;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class CountToDoTest extends TestSetUp {
    @Test
    @Description("Test todo count is not visible if there are no todos in list")
    void checkToDoCountNotVisible(){
        $("#todo-count").shouldNotBe(visible);
    }
    @Test
    @Description("Test added todo counting")
    void checkAddedToDoCount(){
        $("#new-todo").append("a").pressEnter();
        $("#todo-count").shouldHave(text("1 item left"));
    }
    @Test
    @Description("Test several added todo counting")
    void checkSeveralAddedToDoCount(){
        $("#new-todo").append("a").pressEnter();
        $("#new-todo").append("b").pressEnter();
        $("#new-todo").append("c").pressEnter();
        $("#todo-count").shouldHave(text("3 items left"));
    }
}
