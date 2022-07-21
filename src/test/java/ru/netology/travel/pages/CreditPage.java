package ru.netology.travel.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.travel.Date.Data;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private SelenideElement cardNumber = $("[class=input__box] [placeholder='0000 0000 0000 0000']");
    public SelenideElement month = $("[class=input__box] [placeholder='08']");
    private SelenideElement year = $("[class=input__box] [placeholder='22']");
    private SelenideElement owner = $(By.xpath("//span[contains(text(), 'Владелец')]/following::input"));
    private SelenideElement code = $("[class=input__box] [placeholder='999']");
    private SelenideElement errorCardNumber = $(By.xpath("//span[@class='input__sub' and preceding-sibling::span[contains(text(), 'Номер карты')]]"));
    private SelenideElement errorMonth = $(By.xpath("//span[@class='input__sub' and preceding-sibling::span[contains(text(), 'Месяц')]]"));
    private SelenideElement errorYear = $(By.xpath("//span[@class='input__sub' and preceding-sibling::span[contains(text(), 'Год')]]"));
    private SelenideElement errorOwner = $(By.xpath("//span[@class='input__sub' and preceding-sibling::span[contains(text(), 'Владелец')]]"));
    private SelenideElement errorCode = $(By.xpath("//span[@class='input__sub' and preceding-sibling::span[contains(text(), 'CVC/CVV')]]"));
    private SelenideElement creditBuyButton = $$("[class=button__content]").get(1);
    private SelenideElement continueButton = $$("[class=button__content]").get(2);

    public void сlickcreditbutton() {
        creditBuyButton.click();
    }
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

    public void verifySuccess() {
        $("[class=notification__title]").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(exactText("Успешно"));
        $("[class=notification__content]").shouldBe(Condition.visible).shouldHave(exactText("Операция одобрена Банком."));
    }

    public void verifyError() {
        $$("[class=notification__title]").get(1).shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(exactText("Ошибка"));
        $$("[class=notification__content]").get(1).shouldBe(Condition.visible).shouldHave(exactText("Ошибка! Банк отказал в проведении операции."));
    }
}
