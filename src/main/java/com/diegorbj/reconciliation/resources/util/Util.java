package com.diegorbj.reconciliation.resources.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Util {

    public static boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

}
