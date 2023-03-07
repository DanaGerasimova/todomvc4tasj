package how.autotest.todomvc4tasj;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AddToDoTest extends TestSetUp {
    @Test
    @Description("Test adding todo")
    void checkAddToDo(){
        $("#new-todo").shouldBe(editable).append("a").pressEnter();
        $("#new-todo").shouldBe(editable).append("b").pressEnter();
        $("#new-todo").shouldBe(editable).append("c").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a","b","c"));
    }
    @Test
    @Description("Test clearing to do list by editing item with empty string")
    public void checkClearToDoListByEnterEmptyString(){
        $("#new-todo").append("a").pressEnter();
        $("#todo-list>li:nth-child(1)").doubleClick();
        $("#todo-list>li:nth-child(1) .edit").shouldBe(editable)
                .sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        $("#todo-list>li:nth-child(1) .edit").append("").pressEnter();
        $("#todo-list>li").shouldNot(exist);
    }
    @Test
    @Description("Test deleting todo by entering empty string")
    public void checkDeleteToDoByEnterEmptyString(){
        $("#new-todo").append("a").pressEnter();
        $("#new-todo").append("b").pressEnter();
        $("#new-todo").append("c").pressEnter();
        $("#todo-list>li:nth-child(2)").doubleClick();
        $("#todo-list>li:nth-child(2) .edit").shouldBe(editable)
                .sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        $("#todo-list>li:nth-child(2) .edit").append("").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a","c"));
    }
}
