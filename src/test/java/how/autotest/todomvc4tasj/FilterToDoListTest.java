package how.autotest.todomvc4tasj;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class FilterToDoListTest extends TestSetUp {
    @Test
    @Description("Test checking completed todo")
    void checkCompletedToDo(){
        $("#new-todo").append("a").pressEnter();
        $("#new-todo").append("b").pressEnter();
        $("#new-todo").append("c").pressEnter();
        $("#todo-list>li:nth-child(2) .toggle").click();
        $("a[href*='completed']").click();
        $$("#todo-list .completed").shouldHave(size(1));
    }
    @Test
    @Description("Test checking active todo")
    void checkActiveToDo(){
        $("#new-todo").append("a").pressEnter();
        $("#new-todo").append("b").pressEnter();
        $("#new-todo").append("c").pressEnter();
        $("#todo-list>li:nth-child(2) .toggle").click();
        $("a[href*='active']").click();
        $$("#todo-list .active").shouldHave(size(2));
    }
    @Test
    @Description("Test checking all todo")
    void checkAllToDo(){
        $("#new-todo").append("a").pressEnter();
        $("#new-todo").append("b").pressEnter();
        $("#new-todo").append("c").pressEnter();
        $("#todo-list>li:nth-child(2) .toggle").click();
        $("a[href='#/']").click();
        $$("#todo-list li").shouldHave(size(3));
    }
}
