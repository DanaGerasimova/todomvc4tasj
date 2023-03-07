package how.autotest.todomvc4tasj;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class CheckCompletedToDoTest extends TestSetUp {
    @Test
    @Description("Test filtering completed todo if there are no completed todos")
    void checkCompletedToDoEmpty(){
        $("#new-todo").append("a").pressEnter();
        $("a[href*='completed']").shouldBe(visible).shouldBe(enabled).click();
        $(".filter-completed").should(exist);
        $("#todo-list>li").shouldNotBe(visible);
        $("#todo-list>li:nth-child(1) .toggle").shouldNotBe(selected);
    }
    @Test
    @Description("Test filtering completed todo")
    void checkCompletedToDo(){
        $("#new-todo").append("a").pressEnter();
        $("#todo-list>li:nth-child(1) .toggle").click();
        $("a[href*='completed']").shouldBe(visible).shouldBe(enabled).click();
        $(".filter-completed>li:nth-child(1)").should(exist)
                .shouldBe(visible).shouldHave(text("a"));
        $("#todo-list>li:nth-child(1) .toggle").shouldBe(selected);
    }
    @Test
    @Description("Test unchecking completed todo")
    void uncheckCompletedToDo(){
        $("#new-todo").append("a").pressEnter();
        $("#todo-list>li:nth-child(1) .toggle").click();
        $("#todo-list>li:nth-child(1) .toggle").shouldBe(selected);
        $("#todo-list .completed").shouldHave(exactText("a"));
        $("#todo-list>li:nth-child(1) .toggle").click();
        $("#todo-list>li:nth-child(1) .toggle").shouldNotBe(selected);
        $("#todo-list .completed").shouldNot(exist);
    }
    @Test
    @Description("Test toggle all todos select")
    void checkCompletedAllToDos(){
        $("#new-todo").append("a").pressEnter();
        $("#new-todo").append("b").pressEnter();
        $("#new-todo").append("c").pressEnter();
        $("#toggle-all").shouldBe(enabled).click();
        $$("#todo-list .completed").shouldHave(exactTexts("a","b","c"));
    }
    @Test
    @Description("Test toggle all todos unselect")
    void uncheckCompletedAllToDos(){
        $("#new-todo").append("a").pressEnter();
        $("#new-todo").append("b").pressEnter();
        $("#new-todo").append("c").pressEnter();
        $("#toggle-all").shouldBe(enabled).click();
        $$("#todo-list .completed").shouldHave(exactTexts("a","b","c"));
        $("#toggle-all").shouldBe(enabled).click();
        $("#todo-list .completed").shouldNot(exist);
    }
}
