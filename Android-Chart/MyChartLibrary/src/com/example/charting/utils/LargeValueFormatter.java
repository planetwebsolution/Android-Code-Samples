
package com.example.charting.utils;

import java.text.DecimalFormat;

/**
 * Predefined value-formatter that formats large numbers in a pretty way.
*/
public class LargeValueFormatter implements ValueFormatter {

    private static final String[] SUFFIX = new String[] {
            "", "k", "m", "b", "t"
    };
    private static final int MAX_LENGTH = 4;

    private DecimalFormat mFormat;
    private String mText = "";

    public LargeValueFormatter() {
        mFormat = new DecimalFormat("###E0");
    }

    /**
     * Creates a formatter that appends a specified text to the result string
     * @param text a text that will be appended
     */
    public LargeValueFormatter(String appendix) {
        this();
        mText = appendix;
    }

    @Override
    public String getFormattedValue(float value) {
        return makePretty(value) + mText;
    }

    /**
     * Formats each number properly. Special thanks to Roman Gromov
     * (https://github.com/romangromov) for this piece of code.
     */
    private String makePretty(double number) {

        String r = mFormat.format(number);

        r = r.replaceAll("E[0-9]", SUFFIX[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);

        while (r.length() > MAX_LENGTH || r.matches("[0-9]+\\.[a-z]")) {
            r = r.substring(0, r.length() - 2) + r.substring(r.length() - 1);
        }

        return r;
    }
}
