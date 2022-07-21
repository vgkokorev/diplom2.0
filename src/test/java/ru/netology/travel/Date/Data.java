package ru.netology.travel.Date;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Data {
    private Data() {
    }

    @Value
    public static class CardNumber {
        private String CardNumber;
        public static CardNumber getFirstCardNumber() {
            return new CardNumber("4444 4444 4444 4441");
        }
        public static CardNumber getSecondCardNumber() {
            return new CardNumber("4444 4444 4444 4442");
        }
        public static CardNumber getIncorrectCardNumber() {
            return new CardNumber("");
        }
    }
    @Value
    public static class Owner {
        private String Owner;
        public static Owner getCorrectOwner() {
            Faker faker;
            faker = new Faker();
            String customerName = faker.name().fullName();
            return new Owner(customerName);
        }
        public static Owner getIncorrectOwner() {
            return new Owner("");
        }
        public static Owner getSpecialsOwner() {
            return new Owner("@@");
        }
    }
    @Value
    public static class CardCVV {
        private String CardCVV;
        public static CardCVV getCorrectcode() {
            Faker faker;
            faker = new Faker();
            int intcode = faker.number().numberBetween(100, 999);
            String CardCVV = Integer.toString(intcode);
            return new CardCVV(CardCVV);
        }
        public static CardCVV getIncorrectcode() {
            return new CardCVV("");
        }
        public static CardCVV getSpecialsCardcode() {
            return new CardCVV("@@");
        }
    }
    @Value
    public static class Cardyear {
        private String Cardyear;
        public static Cardyear getThisYear() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
            String thisYear = LocalDate.now().format(formatter);
            return new Cardyear(thisYear);
        }
        public static Cardyear getIncorrectYear() {
            return new Cardyear("");
        }
        public static Cardyear getSpecialsCardYear() {
            return new Cardyear("@@");
        }
    }
    @Value
    public static class CardMonth {
        private String CardMonth;
        public static CardMonth getFalseMonth() {
            return new CardMonth("13");
        }
        public static CardMonth getIncorrectMonth() {
            return new CardMonth("");
        }
        public static CardMonth getThisMonth() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
            String thisMonth = LocalDate.now().format(formatter);
            return new CardMonth(thisMonth);
        }
        public static CardMonth getSpecialsCardMonth() {
            return new CardMonth("@@");
        }

    }
}