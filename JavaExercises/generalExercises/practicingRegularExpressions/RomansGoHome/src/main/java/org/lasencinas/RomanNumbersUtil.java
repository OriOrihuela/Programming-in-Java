package org.lasencinas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RomanNumbersUtil {

    /* ---- Behaviours ---- */
    public static int sumatoryRomanNumbersResult(String romanNumbers) {
        int result = 0;
        for (int index = 0; index < romanNumbers.length(); index++) {
            Character number = romanNumbers.charAt(index);
            for (RomanNumbers romanNumber : RomanNumbers.values()) {
                if (romanNumber.name().equals(number.toString())) {
                    result += romanNumber.getValue();
                }
            }
        }
        return result;
    }

    public static int subtractiveRomanNumbersResult(String romanNumbers) {
        int result = 0;
        for (int index = 0; index < romanNumbers.length(); index++) {
            try {
                Character firstNumber = romanNumbers.charAt(index);
                Character nextNumber = romanNumbers.charAt(index + 1);
                String romanNumber = firstNumber.toString() + nextNumber.toString();
                for (RomanNumbers numbers : RomanNumbers.values()) {
                    if (numbers.name().equals(romanNumber)) {
                        result += numbers.getValue();
                    }
                }
            } catch (StringIndexOutOfBoundsException exception) {
                // :D
            }
        }
        return result;
    }

    public static int computeSumatoryRomanNumbers(String romanNumbers) {
        String regex = "(?<!C)[DM]|(?<!X)[LC](?![DM])|(?<!I)[VX](?![LC])|I(?![VX])";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(romanNumbers);
        int result = 0;
        while (m.find()){
            result += sumatoryRomanNumbersResult(m.group());
        }
        return result;
    }

    public static int computeRomanNumbers(String romanNumbers) {
        String regEx = "(C[DM])|(X[LC])|(I[VX])";
        int result = 0;
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(romanNumbers);

        while (matcher.find()) {
            String matchedNumber = matcher.group();
            for (RomanNumbers numbers : RomanNumbers.values()) {
                if (numbers.name().equals(matchedNumber)) {
                    result += numbers.getValue();
                }
            }
        }
        return result + computeSumatoryRomanNumbers(romanNumbers);
    }
}
