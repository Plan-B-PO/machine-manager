package com.po.planb.machinemanager.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class NameValidator {

    private static final String regex = "^[a-zA-Z0-9_-]*$";
    private static final Pattern pattern = Pattern.compile(regex);

    public boolean validate(String str) {
        return pattern.matcher(str).matches();
    }

}
