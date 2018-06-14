package at.htl_villach.docker2go;

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
}
