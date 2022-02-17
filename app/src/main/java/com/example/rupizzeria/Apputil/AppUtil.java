package com.example.rupizzeria.Apputil;

import java.text.DecimalFormat;
/**
 * App util is used to format numerous decimal values to 2 decimal values.
 *
 */
public class AppUtil {

    /**
     * A functional formatting the double values to 2 decimal values
     * @param value
     * @return
     */
    public static double decimalFormat(double value) {

        try {
            if (value != 0.0) {
                DecimalFormat df = new DecimalFormat("####0.00");
                return Double.parseDouble(df.format(value));
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return value;
    }
}
