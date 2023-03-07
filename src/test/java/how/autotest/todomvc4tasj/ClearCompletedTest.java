package how.autotest.todomvc4tasj;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class ClearCompletedTest extends TestSetUp {
    @Test
    @Description("Test clear completed btn is not visible if there are only active todos in list")
    void checkClearCompletedBtnNotVisible(){
        $("#new-todo").append("a").pressEnter();
        $("#new-todo").append("b").pressEnter();
        $("#new-todo").append("c").pressEnter();
        $("#clear-completed").shouldNotBe(visible);
    }
    @Test
    @Description("Test clear completed btn. There are only active todos in list after clicking btn")
    void checkClearCompletedBtn(){
        $("#new-todo").append("a").pressEnter();
        $("#new-todo").append("b").pressEnter();
        $("#new-todo").append("c").pressEnter();
        $("#todo-list>li:nth-child(1) .toggle").click();
        $("#todo-list>li:nth-child(2) .toggle").click();
        $("#clear-completed").shouldBe(visible).shouldBe(enabled).click();
        $$("#todo-list>li").shouldHave(exactTexts("c"));
        $("#todo-list .completed").shouldNot(exist);
    }
}
