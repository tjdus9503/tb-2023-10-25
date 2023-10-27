package com.ll.domain;

import com.ll.standard.util.Ut;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String cmd;
    @Getter
    private String action;
    private String queryString;
    private Map<String, String> paramsMap;

    public Rq(String cmd) {
        paramsMap = new HashMap<>();

        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?", 2);
        action = cmdBits[0].trim();

        if (cmdBits.length == 1) {
            return;
        }

        queryString = cmdBits[1].trim();

        String[] queryStringBits = queryString.split("&");

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];
            String[] queryParamStrBits = queryParamStr.split("=", 2);

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            paramsMap.put(paramName, paramValue);
        }
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        return Ut.str.parseInt(paramsMap.get(paramName), defaultValue);
    }
}