package com.diegorbj.reconciliation.services.utils;

public class Util {

    public static boolean isValidLong(String number){
        try {
            Long.parseLong(number);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static boolean isValidDescription(String description){
        return ! description.trim().equals("");
    }

}
