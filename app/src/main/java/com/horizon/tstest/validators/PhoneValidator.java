package com.horizon.tstest.validators;

import java.util.regex.Pattern;

/*By Phat La*/
public class PhoneValidator {
    private static Pattern pattern = Pattern.compile("^0(86|88|89|(9|12)[0-9]|16[2-9]|186|188|199)\\d{7}$");

    public static boolean isValid(final String number){
        return pattern.matcher(number).matches();
    }
}
