package com.horizon.tstest.validators;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*By Phat La*/
public class PhoneValidatorTest {
    private String[] validPrefix = {
            "0120", "0121", "0122", "0123", "0124",
            "0125", "0126", "0127", "0128", "0129",
            "0162", "0163", "0164", "0165", "0166",
            "0167", "0168", "0169", "0186", "0188",
            "0199",  "086",  "088",  "089",  "090",
            "091",   "092",  "093",  "094",  "095",
            "096",   "097",  "098",  "099"
    };

    private String[] invalidPrefix = {
            "000", "0000", "001", "0001", "00000", "11111",
            "0119", "0130", "0161", "0170", "0185", "0200",
            "085", "087", "100"
    };

    @Test
    public void testValidPhoneNumbers(){
        for(String prefix:validPrefix)
            assertTrue("Not True: " + prefix, PhoneValidator.isValid(prefix + "1234567"));
    }

    @Test
    public void testInvalidNumbers(){
        for(String prefix:invalidPrefix)
            assertFalse("Must False: " + prefix, PhoneValidator.isValid(prefix + "1234567"));
    }

    @Test
    public void testNullNumber(){
        assertFalse("Null Number",  PhoneValidator.isValid(""));
    }
}