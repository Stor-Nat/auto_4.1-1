package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    private static Faker faker = new Faker(new Locale("ru"));

    public static String city() {
        String[] cityNames = new String[]{"Москва", "Воронеж", "Липецк", "Рязань", "Тула", "Казань"};
        int index = (int) (Math.random() * cityNames.length);
        return cityNames[index];
    }

    public static String getDate(int daysToAdd) {
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String name() {
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String phone() {
        return faker.phoneNumber().phoneNumber();
    }

}
