package ru.netology.travel.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class AppPage {
    private SelenideElement buyButton = $("[class=button__content]");
    private SelenideElement creditBuyButton = $$("[class=button__content]").get(1);
    private SelenideElement continueButton = $$("[class=button__content]").get(2);

    public PayPage buy() {
        buyButton.click();
        return new PayPage();

    }

    public CreditPage buyInCredit() {
        creditBuyButton.click();
        return new CreditPage();
    }
}
