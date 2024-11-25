package com.example.lab5.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
/**
 * Допоміжні функції до роботи з датами.
 *
 * @authorMarco Jakob
 */
    /**
     * Шаблон дати, який використовується для перетворення. Можна міняти на свій.
     */
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    /**
     * Форматувальник дати.
     */
    private static final DateTimeFormatter
            DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Повертає отриману дату у вигляді добре відформатованого рядка.
     * Використовується певний {@link DateUtil#DATE_PATTERN}.
     *
     * @paramdate - дата, яка буде повернена у вигляді рядка
     * @returnвідформатований рядок
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Перетворює рядок, який відформатовано за правилами
     * шаблону {@link DateUtil#DATE_PATTERN} в об'єкт {@link LocalDate}.
     * <p>
     * Повертає null, якщо рядок може бути перетворена.
     *
     * @paramdateString - дата у вигляді String
     * @returnоб'єкт дати або null, якщо рядок не може бути перетворено
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Перевіряє, чи є рядок коректною датою.
     *
     * @paramdateString
     * @returntrue, якщо рядок є коректною датою
     */
    public static boolean validDate(String dateString) {
// Намагаємось розібрати рядок.
        return DateUtil.parse(dateString) != null;
    }
}
