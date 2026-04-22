package com.resume.Backend.Parser;

import org.apache.tika.Tika;

public class TikaSingleton {
    private static Tika tika = new Tika();

    private TikaSingleton() {

    }

    public static Tika getInstance() {
        return tika;
    }
}
