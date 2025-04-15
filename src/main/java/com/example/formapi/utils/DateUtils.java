package com.example.formapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    public static Date extractBirthDateFromCNP(String cnp) throws ParseException {
        // Validate the CNP length
        if (cnp == null || cnp.length() != 13) {
            throw new IllegalArgumentException("Invalid CNP length");
        }

        // Extract the year, month, and day from the CNP
        String yearStr = cnp.substring(1, 3);  // Digits 2-3 represent the year
        String monthStr = cnp.substring(3, 5); // Digits 4-5 represent the month
        String dayStr = cnp.substring(5, 7);   // Digits 6-7 represent the day

        // Extract the first digit to determine if the birth year is in 1900s or 2000s
        int firstDigit = Integer.parseInt(cnp.substring(0, 1));

        // Determine the correct century
        int year;
        if (firstDigit == 1 || firstDigit == 2) {
            year = 1900 + Integer.parseInt(yearStr);  // Born in the 1900s
        } else if (firstDigit == 3 || firstDigit == 4) {
            year = 1800 + Integer.parseInt(yearStr);  // Born in the 1800s
        } else {
            year = 2000 + Integer.parseInt(yearStr);  // Born in the 2000s
        }

        // Prepare the full birthdate string
        String birthDateString = String.format("%04d-%s-%s", year, monthStr, dayStr);

        // Parse the birthdate using SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(birthDateString);
    }
}
