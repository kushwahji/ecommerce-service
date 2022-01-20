package com.santosh.ms.fundtransfer.helper;

import org.springframework.stereotype.Component;

import com.santosh.ms.fundtransfer.utils.DateConverter;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther Santosh-Kus
 * Date: 31-12-2021
 */

@Component
public class Helper {
    public static long generateAccountNumber(){
        return Long.parseLong(DateConverter.convertDateToString(new Date(),"ddMMyyyyhhmmss")+generateRandom(1,5));
    }
    public static String generateReferenceNumber(){
        return UUID.randomUUID().toString();
    }
    private static int generateRandom(int minimum ,int maximum){
        return (int)((Math.random()*maximum) + minimum);
    }
}
