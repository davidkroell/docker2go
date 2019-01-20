package at.htl_villach.docker2go;

import java.util.Date;

public class Utilities {

    public static String formatBytes(long originalBytes) {
        String[] norm = new String[]{"B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
        int factor = 1024;
        double size = originalBytes;
        int index = 0;

        while (size >= factor && index < (norm.length - 1)) {
            size /= factor;
            index++;
        }

        return String.format("%s %s", Double.toString(Math.round(size * 100.0) / 100.0), norm[index]);
    }

    // some inaccurate, because it always uses divisions without floating point
    public static String timeElapsedString(Date start, Date end, String[] dateTypeStringsSingular, String[] dateTypeStringsPlural, String wrapper) {
        long diff = end.getTime() - start.getTime();

        // seconds, minutes, hours, days, weeks years
        int[] factors = new int[]{1000, 60, 60, 24, 7, 52};
        // counter
        int i = 0;
        while (factors.length > i && diff > factors[i]) {
            diff /= factors[i];
            i++;
        }

        // use singular or plural
        String dateTypeString;
        if (diff == 1)
            dateTypeString = dateTypeStringsSingular[i - 1];
        else
            dateTypeString = dateTypeStringsPlural[i - 1];

        String helper = String.format(dateTypeString, diff);
        return String.format(wrapper, helper);
    }

    public static String timeElapsedString(Date start, String[] dateTypeStringsSingular, String[] dateTypeStringsPlural, String wrapper) {
        return timeElapsedString(start,
                new Date(System.currentTimeMillis()),
                dateTypeStringsSingular,
                dateTypeStringsPlural,
                wrapper);
    }
}
