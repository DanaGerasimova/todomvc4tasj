package how.autotest.todomvc4tasj;

import com.codeborne.selenide.CollectionCondition;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class GeneralPageTest extends TestSetUp {
    @Test
    @Description("Test header text")
    void checkHeader(){
        $("#header h1").shouldHave(text("todos"));
    }
    @Test
    @Description("Test footer attributes")
    void checkFooter(){
        $$("#info>p")
                .shouldHave(CollectionCondition.exactTexts(
                        "Double-click to edit a todo",
                        "Created by Mikael Karon",
                        "Part of TodoMVC"));
    }
    @Test
    @Description("Test new todo placeholder")
    void checkNewToDoPlaceholder(){
        $("#new-todo").shouldHave(attribute("placeholder",
                "What needs to be done?"));
    }
    @Test
    @Description("Test unfocus new todo field")
    void checkUnFocusNewToDoByDefault(){
        $("#new-todo").shouldBe(visible).shouldNotBe(focused);
    }
    @Test
    @Description("Test new todo focus after clicking on it")
    void checkNewToDoFocus(){
        $("#new-todo").click();
        $("#new-todo").shouldBe(focused).shouldBe(editable);
    }
    @Test
    @Description("Test new todo unfocus after clicking on it and then clicking on any empty space")
    void checkNewToDoUnFocus(){
        $("#new-todo").click();
        $("#new-todo").shouldBe(focused).shouldBe(editable);
        $("#header h1").click();
        $("#new-todo").shouldBe(visible).shouldNotBe(focused);
    }
    @Test
    @Description("Test Mikael Karon link")
    void checkMikaelKaronLink(){
        $("a[href='https://github.com/mikaelkaron']").shouldBe(visible).shouldBe(enabled).click();
        webdriver().shouldHave(url("https://github.com/mikaelkaron"));
    }
    @Test
    @Description("Test todomvc link")
    void checkToDoMvcLink(){
        $("a[href='http://todomvc.com']").shouldBe(visible).shouldBe(enabled).click();
        webdriver().shouldHave(url("https://todomvc.com/"));
    }
}
