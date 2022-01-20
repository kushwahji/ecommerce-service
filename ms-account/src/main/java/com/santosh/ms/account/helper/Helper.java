package com.santosh.ms.account.helper;

import org.springframework.stereotype.Component;

import com.santosh.ms.account.utils.DateConverter;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
/**
 * @author santosh.kushwah
 * @since: 12-12-2021
 */

@Component
public class Helper {
	static Random r = new Random();
	private Helper() {
		
	}
    public static long generateAccountNumber(){
        return Long.parseLong(DateConverter.convertDateToString(new Date(),"ddMMyyyyhhmmss")+generateRandom(1,5));
    }
    public static String generateReferenceNumber(){
        return UUID.randomUUID().toString();
    }
    private static int generateRandom(int minimum ,int maximum){
    	int rand = r.nextInt(50);
        return (rand*maximum) + minimum;
    }
    
}
