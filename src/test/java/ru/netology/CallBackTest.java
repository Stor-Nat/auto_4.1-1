package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.*;


public class CallBackTest {

    @Test
    void shouldSubmitRequest() {
        String planMeetingDate = getDate(3);
        String replanMeetingDate = getDate(7);
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue(city());

//        Очищаем поле ввода даты
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(planMeetingDate);

        $("[data-test-id='name'] input").setValue(name());

        $("[data-test-id='phone'] input").setValue(phone());

        $("[data-test-id=agreement]").click();

        $(byText("Запланировать")).click();

        $(withText("Успешно!")).waitUntil(visible, 15000);

//      Проверка даты в сплывающем сообщении
        $("[data-test-id='success-notification'] .notification__content").shouldHave(text("Встреча успешно запланирована на " + planMeetingDate));

//        Очищаем поле ввода даты
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(replanMeetingDate);

        $(byText("Запланировать")).click();

        $(byText("Перепланировать")).click();

        $(withText("Успешно!")).waitUntil(visible, 15000);

//        Проверка даты в сплывающем сообщении
        $("[data-test-id='success-notification'] .notification__content").shouldHave(text("Встреча успешно запланирована на " + replanMeetingDate));
    }

}
