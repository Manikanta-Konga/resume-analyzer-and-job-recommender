package com.resume.Backend.parsing;
import org.apache.tika.Tika;

public class TikaSingleton {
    private static final Tika tika = new Tika();

    private TikaSingleton() {

    }

    public static Tika getInstance() {
        return tika;
    }
}
