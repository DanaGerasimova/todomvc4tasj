package how.autotest.todomvc4tasj;

import com.codeborne.selenide.Configuration;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class POCTest {
    @BeforeEach
    public void setUp(){
        open("https://todomvc4tasj.herokuapp.com/");

        Wait().until(ExpectedConditions.jsReturnsValue(
                "return $._data($('#new-todo').get(0), 'events').hasOwnProperty('keyup');"));
        Configuration.holdBrowserOpen = true;
    }
    @Test
    @Description("Test adding todo")
    void checkAddToDo(){
        // Test adding to do
        $("#new-todo").shouldBe(editable).append("a").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a"));

        // Test added to do counting
        $("#todo-count").shouldHave(text("1 item left"));

        // Test data saving after editing by press enter
        $("#todo-list>li:nth-child(1)").doubleClick();
        $("#todo-list>li:nth-child(1) .edit").shouldBe(editable)
                .sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        $("#todo-list>li:nth-child(1) .edit").append("new value").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("new value"));

        // Test data saving after editing by clicking on empty space
        $("#todo-list>li:nth-child(1)").doubleClick();
        $("#todo-list>li:nth-child(1) .edit").shouldBe(editable)
                .sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        $("#todo-list>li:nth-child(1) .edit").append("new value 1");
        $("#header").click();
        $$("#todo-list>li").shouldHave(exactTexts("new value 1"));

        // Test cancel editing by press escape
        $("#todo-list>li:nth-child(1)").doubleClick();
        $("#todo-list>li:nth-child(1) .edit").shouldBe(editable)
                .sendKeys(Keys.ESCAPE);
        $$("#todo-list>li").shouldHave(exactTexts("new value 1"));

        // Test trim spaces to do
        $("#new-todo").append("  new value 2   ").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("new value 1","new value 2"));
    }
}
