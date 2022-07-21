package ru.netology.travel.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.travel.Date.DbUtils;
import ru.netology.travel.pages.CreditPage;
import ru.netology.travel.Date.Data;
import ru.netology.travel.pages.PayPage;
import java.sql.SQLException;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    //  TODO    mysql    OR    postgresql

    @BeforeEach
    void setUp() {
        DbUtils.clearTables();
        String url = System.getProperty("sut.url");
        open(url);
    }
    @Test
    void successfulDebitBuy() throws SQLException{
        var order = new PayPage();
        order.сlickbuybutton();
        order.сardNumber(Data.CardNumber.getFirstCardNumber());
        order.inputmonth(Data.CardMonth.getThisMonth());
        order.inputyear(Data.Cardyear.getThisYear());
        order.inputOwner(Data.Owner.getCorrectOwner());
        order.inputcode(Data.CardCVV.getCorrectcode());
        order.сlickcontinue();
        order.verifySuccess();
        assertEquals("APPROVED", DbUtils.getPayStatus());
    }

    @Test
    void declinedDebitBuy() throws SQLException{
        var order = new PayPage();
        order.сlickbuybutton();
        order.сardNumber(Data.CardNumber.getSecondCardNumber());
        order.inputmonth(Data.CardMonth.getThisMonth());
        order.inputyear(Data.Cardyear.getThisYear());
        order.inputOwner(Data.Owner.getCorrectOwner());
        order.inputcode(Data.CardCVV.getCorrectcode());
        order.сlickcontinue();
        order.verifyError();
        assertEquals("DECLINED", DbUtils.getPayStatus());
    }

    @Test
    void successfulCreditBuy() throws SQLException{
        var order = new CreditPage();
        order.сlickcreditbutton();
        order.сardNumber(Data.CardNumber.getFirstCardNumber());
        order.inputmonth(Data.CardMonth.getThisMonth());
        order.inputyear(Data.Cardyear.getThisYear());
        order.inputOwner(Data.Owner.getCorrectOwner());
        order.inputcode(Data.CardCVV.getCorrectcode());
        order.сlickcontinue();
        order.verifySuccess();
        assertEquals("APPROVED", DbUtils.getCreditStatus());
    }

    @Test
    void declinedCreditBuy() throws SQLException{
        var order = new CreditPage();
        order.сlickcreditbutton();
        order.сardNumber(Data.CardNumber.getSecondCardNumber());
        order.inputmonth(Data.CardMonth.getThisMonth());
        order.inputyear(Data.Cardyear.getThisYear());
        order.inputOwner(Data.Owner.getCorrectOwner());
        order.inputcode(Data.CardCVV.getCorrectcode());
        order.сlickcontinue();
        order.verifyError();
        assertEquals("DECLINED", DbUtils.getCreditStatus());
    }

    @Test
    void emptyForm() {
        var order = new PayPage();
        order.сlickbuybutton();
        order.сardNumber(Data.CardNumber.getIncorrectCardNumber());
        order.inputmonth(Data.CardMonth.getIncorrectMonth());
        order.inputyear(Data.Cardyear.getIncorrectYear());
        order.inputOwner(Data.Owner.getIncorrectOwner());
        order.inputcode(Data.CardCVV.getIncorrectcode());
        order.сlickcontinue();
        order.cardValidate();
        order.ownerValidate();
        order.monthValidate();
        order.yearValidate();
        order.codeValidate();
    }

    @Test
    void validateMonth() {
        var order = new PayPage();
        order.сlickbuybutton();
        order.сardNumber(Data.CardNumber.getFirstCardNumber());
        order.inputmonth(Data.CardMonth.getFalseMonth());
        order.inputyear(Data.Cardyear.getThisYear());
        order.inputOwner(Data.Owner.getCorrectOwner());
        order.inputcode(Data.CardCVV.getCorrectcode());
        order.сlickcontinue();
        order.monthValidate();
    }

    @Test
    void wrongSymbolsMonth() {
        var order = new PayPage();
        order.сlickbuybutton();
        order.сardNumber(Data.CardNumber.getFirstCardNumber());
        order.inputmonth(Data.CardMonth.getSpecialsCardMonth());
        order.inputyear(Data.Cardyear.getThisYear());
        order.inputOwner(Data.Owner.getCorrectOwner());
        order.inputcode(Data.CardCVV.getCorrectcode());
        order.сlickcontinue();
        order.monthValidate();
    }

    @Test
    void zeroMonth() {
        var order = new PayPage();
        order.сlickbuybutton();
        order.сardNumber(Data.CardNumber.getFirstCardNumber());
        order.inputmonth(Data.CardMonth.getIncorrectMonth());
        order.inputyear(Data.Cardyear.getThisYear());
        order.inputOwner(Data.Owner.getCorrectOwner());
        order.inputcode(Data.CardCVV.getCorrectcode());
        order.сlickcontinue();
        order.monthValidate();
    }

    @Test
    void validateYear() {
        var order = new PayPage();
        order.сlickbuybutton();
        order.сardNumber(Data.CardNumber.getFirstCardNumber());
        order.inputmonth(Data.CardMonth.getThisMonth());
        order.inputyear(Data.Cardyear.getIncorrectYear());
        order.inputOwner(Data.Owner.getCorrectOwner());
        order.inputcode(Data.CardCVV.getCorrectcode());
        order.сlickcontinue();
        order.yearValidate();
    }

    @Test
    void wrongSymbolsYear() {
        var order = new PayPage();
        order.сlickbuybutton();
        order.сardNumber(Data.CardNumber.getFirstCardNumber());
        order.inputmonth(Data.CardMonth.getThisMonth());
        order.inputyear(Data.Cardyear.getSpecialsCardYear());
        order.inputOwner(Data.Owner.getCorrectOwner());
        order.inputcode(Data.CardCVV.getCorrectcode());
        order.сlickcontinue();
        order.yearValidate();
    }

    @Test
    void validateOwner() {
        var order = new PayPage();
        order.сlickbuybutton();
        order.сardNumber(Data.CardNumber.getFirstCardNumber());
        order.inputmonth(Data.CardMonth.getThisMonth());
        order.inputyear(Data.Cardyear.getThisYear());
        order.inputOwner(Data.Owner.getIncorrectOwner());
        order.inputcode(Data.CardCVV.getCorrectcode());
        order.сlickcontinue();
        order.ownerValidate();
    }

    @Test
    void wrongSymbolsOwner() {
        var order = new PayPage();
        order.сlickbuybutton();
        order.сardNumber(Data.CardNumber.getFirstCardNumber());
        order.inputmonth(Data.CardMonth.getThisMonth());
        order.inputyear(Data.Cardyear.getThisYear());
        order.inputOwner(Data.Owner.getSpecialsOwner());
        order.inputcode(Data.CardCVV.getCorrectcode());
        order.ownerValidate();
    }

    @Test
    void validateCode() {
        var order = new PayPage();
        order.сlickbuybutton();
        order.сardNumber(Data.CardNumber.getFirstCardNumber());
        order.inputmonth(Data.CardMonth.getThisMonth());
        order.inputyear(Data.Cardyear.getThisYear());
        order.inputOwner(Data.Owner.getCorrectOwner());
        order.inputcode(Data.CardCVV.getIncorrectcode());
        order.сlickcontinue();
        order.codeValidate();
    }
}
