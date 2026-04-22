package com.resume.Backend.Parser;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class ResumeParsing {

    public String extractStringFromFile(File resumeFile) throws IOException {

        String fileInfo = "";

        Tika tika = TikaSingleton.getInstance();

        String type = tika.detect(resumeFile);


        if (!(type.equals("application/pdf") || type.equals("application/msword"))) {
            throw new RuntimeException("Content in the file is not supported");
        }

        try {
            fileInfo = tika.parseToString(resumeFile);
        } catch (TikaException obj) {
            obj.getStackTrace();
        }

        return fileInfo;
    }

}


