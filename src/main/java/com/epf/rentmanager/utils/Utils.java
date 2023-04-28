package com.epf.rentmanager.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Utils {
    /**
     * Lit une date
     * @param date
     * @return
     */
    public static LocalDate readDate(String date) {
        LocalDate output = null;
        boolean error = false;

        do {
            try {
                error = false;
                output = LocalDate.parse(date);
            } catch (DateTimeParseException e) {
                error = true;
            }
        } while (error);

        return output;
    }

    /**
     * Lit un entier
     * @param entier
     * @return
     */
    public static int readInt(String entier) {

        String input = entier;
        int output = 0;
        boolean error = false;

        do {
            error = false;

            try {
                output = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                error = true;
            }
        } while (error);

        return output;
    }
}
