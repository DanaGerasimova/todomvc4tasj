package how.autotest.todomvc4tasj;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class EditToDoTest extends TestSetUp{
    @Test
    @Description("Test data saving after editing by press enter")
    void checkEditToDoByEnter(){
        $("#new-todo").append("a").pressEnter();
        $("#todo-list>li:nth-child(1)").doubleClick();
        $("#todo-list>li:nth-child(1) .edit").shouldBe(editable)
                .sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        $("#todo-list>li:nth-child(1) .edit").append("new value").pressEnter();

        $$("#todo-list>li").shouldHave(exactTexts("new value"));
    }
    @Test
    @Description("Test data saving after editing by clicking on empty space")
    void checkEditToDoByClick(){
        $("#new-todo").append("a").pressEnter();
        $("#todo-list>li:nth-child(1)").doubleClick();
        $("#todo-list>li:nth-child(1) .edit").shouldBe(editable)
                .sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        $("#todo-list>li:nth-child(1) .edit").append("new value");
        $("#header").click();

        $$("#todo-list>li").shouldHave(exactTexts("new value"));
    }
    @Test
    @Description("Test cancel editing by press escape")
    void checkCancelEditToDoByEscape(){
        $("#new-todo").append("a").pressEnter();
        $("#todo-list>li:nth-child(1)").doubleClick();
        $("#todo-list>li:nth-child(1) .edit").shouldBe(editable)
                .sendKeys(Keys.ESCAPE);

        $$("#todo-list>li").shouldHave(exactTexts("a"));
    }
    @Test
    @Description("Test trim spaces todo")
    void checkTrimToDo(){
        $("#new-todo").append("  a test   ").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a test"));
    }
    @Test
    @Description("Test control elements hiding while edit")
    void checkControlElementsHidingWhileEdit(){
        $("#new-todo").append("a").pressEnter();
        $("#todo-list>li:nth-child(1)").doubleClick();
        $("#todo-list>li:nth-child(1) .toggle").shouldNotBe(visible);
        $("#todo-list>li:nth-child(1) .destroy").shouldNotBe(visible);
    }
    @Test
    @Description("Test delete btn showing by hovering")
    void checkToDoDeleteButtonShow(){
        $("#new-todo").append("a").pressEnter();
        $("#todo-list>li:nth-child(1) .toggle").shouldBe(visible)
                .shouldBe(enabled).hover();
        $("#todo-list>li:nth-child(1) .destroy").shouldBe(visible);
    }
    @Test
    @Description("Test delete only one todo")
    void checkDeleteOnlyOneToDo(){
        $("#new-todo").append("a").pressEnter();
        $("#todo-list>li:nth-child(1) .toggle").hover();
        $("#todo-list>li:nth-child(1) .destroy").click();
        $("#todo-list>li").shouldNot(exist);
    }
    @Test
    @Description("Test delete btn")
    void checkDeleteToDo(){
        $("#new-todo").append("a").pressEnter();
        $("#new-todo").append("b").pressEnter();
        $("#new-todo").append("c").pressEnter();
        $("#todo-list>li:nth-child(1) .toggle").hover();
        $("#todo-list>li:nth-child(1) .destroy").click(); //проверить
        $$("#todo-list>li").shouldHave(exactTexts("b","c"));
    }
    @Test
    @Description("Test autodeleting empty string todo")
    void checkDeleteEmptyStringToDo(){
        $("#new-todo").append("a").pressEnter();
        $("#new-todo").append("").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a"));
    }
    @Test
    @Description("Test data saving after editing selected todo")
    void checkEditSelectedToDoByDoubleClick(){
        $("#new-todo").append("a").pressEnter();
        $("#todo-list>li:nth-child(1) .toggle").click();
        $("#todo-list>li:nth-child(1)").doubleClick();
        $("#todo-list>li:nth-child(1) .edit").pressEnter();
        $("#todo-list>li:nth-child(1) .toggle").shouldBe(checked);
    }
}
