package how.autotest.todomvc4tasj;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class POCTest {
    @BeforeEach
    public void setUp(){
        open("https://todomvc4tasj.herokuapp.com/");

        Wait().until(ExpectedConditions.jsReturnsValue(
                "return $._data($('#new-todo').get(0), 'events').hasOwnProperty('keyup');"));
    }
    @Test
    @Description("Test adding todo")
    void checkAddToDo(){
        $("#new-todo").append("a").pressEnter();
        $("#new-todo").append("b").pressEnter();
        $("#new-todo").append("c").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a","b","c"));

        $$("#todo-list>li").findBy(text("c")).doubleClick();
        $(byXpath("//*[@id='todo-list']//li[.//text()='c']//*[@class='edit']"))
                .sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
        $(byXpath("//*[@id='todo-list']//li[.//text()='c']//*[@class='edit']"))
                .append("c edited").pressEnter();

        $$("#todo-list>li").findBy(text("c edited")).doubleClick();
        $(byXpath("//*[@id='todo-list']//li[.//text()='c edited']//*[@class='edit']"))
                .sendKeys(Keys.ESCAPE);

        $(byXpath("//*[@id='todo-list']//li[.//text()='b']//*[@class='toggle']")).click();
        $("#clear-completed").click();
        $$("#todo-list>li").shouldHave(exactTexts("a","c edited"));

        $(byXpath("//*[@id='todo-list']//li[.//text()='c edited']//*[@class='toggle']")).hover();
        $(byXpath("//*[@id='todo-list']//li[.//text()='c edited']//*[@class='destroy']")).click();
        $$("#todo-list>li").shouldHave(exactTexts("a"));
    }
}
