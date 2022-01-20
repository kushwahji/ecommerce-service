/**
 *
 */
package com.santosh.ms.fundtransfer.utils;


import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.santosh.ms.fundtransfer.exception.MsApplicationException;

/**
 * @author SANTOSH
 *
 */
public class DateConverter {
    private static Logger logger = LoggerFactory.getLogger(DateConverter.class);

    /**
     * Convert Date to String
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String convertDateToString(Date date, String pattern) {
        SimpleDateFormat format = null;
        try {
            format = new SimpleDateFormat(pattern);
        } catch (Exception e) {
        	logger.error("inside catch {}",e.getMessage());
            throw new MsApplicationException(e.getLocalizedMessage(), e.getMessage());
        }

        return format.format(date);
    }

    /**
     * Convert String to Date
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date convertStringToDate(String date, String pattern) {
        SimpleDateFormat formatter = null;
        Date parsedDate = null;
        try {
            formatter = new SimpleDateFormat(pattern);
            parsedDate = formatter.parse(date);
        } catch (Exception e) {
            throw new MsApplicationException(e.getLocalizedMessage(), e.getMessage());
        }

        return parsedDate;
    }

    public static Date changeDateFormat(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(formatter.format(date));
        } catch (ParseException e) {
            throw new MsApplicationException(e.getLocalizedMessage(), e.getMessage());
        }
    }

    public static int getYear(Date date) {
        return Integer.parseInt(convertDateToString(date, "yyyy"));
    }

    public static String getMonthName(Date date) {
        try {
            String monthNumber = convertDateToString(date, "MM");
            System.out.println("month number: " + monthNumber);
            String[] months = new DateFormatSymbols().getMonths();
            int n = Integer.parseInt(monthNumber) - 1;
            return (n >= 0 && n <= 11) ? months[n] : "wrong number";
        } catch (Exception e) {
            throw new MsApplicationException(e.getLocalizedMessage(), e.getMessage());
        }
    }

    public static int getMonthNumber(String monthName) {
        try {
            return Month.valueOf(monthName.toUpperCase()).getValue();
        } catch (Exception e) {
            throw new MsApplicationException(e.getLocalizedMessage(), e.getMessage());
        }
    }

    public static LocalDateTime changeDateTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        LocalDateTime localDateTime1 = null;
        try {
            localDateTime1 = LocalDateTime.parse(localDateTime.format(formatter), formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localDateTime1;
    }
}
