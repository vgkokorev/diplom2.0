package ru.netology.travel.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.travel.Date.Data;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PayPage {
    private SelenideElement form = $(By.className("App_appContainer__3jRx1"));
    private SelenideElement fieldBlock = form.$(By.className("form"));
    private SelenideElement cardNumber = $("[class=input__box] [placeholder='0000 0000 0000 0000']");
    private SelenideElement month = $("[class=input__box] [placeholder='08']");
    private SelenideElement year = $("[class=input__box] [placeholder='22']");
    private SelenideElement owner = $(By.xpath("//span[contains(text(), 'Владелец')]/following::input"));
    private SelenideElement code = fieldBlock.$("[placeholder='999']");

    private SelenideElement errorCardNumber = $(By.xpath("//span[@class='input__sub' and preceding-sibling::span[contains(text(), 'Номер карты')]]"));
    private SelenideElement errorMonth = $(By.xpath("//span[@class='input__sub' and preceding-sibling::span[contains(text(), 'Месяц')]]"));
    private SelenideElement errorYear = $(By.xpath("//span[@class='input__sub' and preceding-sibling::span[contains(text(), 'Год')]]"));
    private SelenideElement errorOwner = $(By.xpath("//span[@class='input__sub' and preceding-sibling::span[contains(text(), 'Владелец')]]"));
    private SelenideElement errorCode = $(By.xpath("//span[@class='input__sub' and preceding-sibling::span[contains(text(), 'CVC/CVV')]]"));
    private SelenideElement continueButton = $$("[class=button__content]").get(2);
    private SelenideElement buyButton = $("[class=button__content]");

    public void сlickbuybutton() { buyButton.click(); }
    public void сlickcontinue() {
        continueButton.click();
    }
    public void сardNumber(Data.CardNumber CardNumber) {
        cardNumber.setValue(CardNumber.getCardNumber());
    }
    public void inputOwner(Data.Owner Owner) {
        owner.setValue(Owner.getOwner());
    }
    public void inputcode(Data.CardCVV cardCVV) {
        code.setValue(cardCVV.getCardCVV());
    }
    public void inputyear(Data.Cardyear cardyear) {
        year.setValue(cardyear.getCardyear());
    }
    public void inputmonth(Data.CardMonth cardMonth) {
        month.setValue(cardMonth.getCardMonth());
    }
    public void AllFieldsvalidate() {
        $$(".input__sub").shouldHave(CollectionCondition.size(5)).shouldHave(CollectionCondition.texts("Поле обязательно для заполнения"));
    }
    public void cardValidate() {
        cardNumber.shouldBe(empty);
        errorCardNumber.shouldBe(visible);
        errorCardNumber.shouldHave(text("Неверный формат"));
    }
    public void monthValidate() {
        month.shouldBe(empty);
        errorMonth.shouldBe(visible);
        errorMonth.shouldHave(text("Неверный формат"));
    }
    public void yearValidate() {
        year.shouldBe(empty);
        errorYear.shouldBe(visible);
        errorYear.shouldHave(text("Неверный формат"));
    }
    public void ownerValidate() {
        owner.shouldBe((empty));
        errorOwner.shouldBe(visible);
        errorOwner.shouldHave(text("Поле обязательно для заполнения"));
    }
    public void codeValidate() {
        code.shouldBe(empty);
        errorCode.shouldBe(visible);
        errorCode.shouldHave(text("Неверный формат"));
    }

    public void verifySuccess() {
        $("[class=notification__title]").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(exactText("Успешно"));
        $("[class=notification__content]").shouldBe(Condition.visible).shouldHave(exactText("Операция одобрена Банком."));
    }

    public void verifyError() {
        $$("[class=notification__title]").get(1).shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(exactText("Ошибка"));
        $$("[class=notification__content]").get(1).shouldBe(Condition.visible).shouldHave(exactText("Ошибка! Банк отказал в проведении операции."));
    }
}

