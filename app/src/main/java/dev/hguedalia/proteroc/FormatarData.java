package dev.hguedalia.proteroc;

public class FormatarData {

    public static String dataFormatada(String data) {
        if (data.length() >= 8) {
            return data.substring(0, 2) + "/" + data.substring(2, 4) + "/" + data.substring(4, 8);
        } else if (data.length() >= 6) {
            return data.substring(0, 2) + "/" + data.substring(2, 4) + "/" + data.substring(4);
        } else if (data.length() >= 4) {
            return data.substring(0, 2) + "/" + data.substring(2);
        } else {
            return data;
        }
    }
}