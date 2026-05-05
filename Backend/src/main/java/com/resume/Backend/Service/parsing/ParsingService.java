package com.resume.Backend.Service.parsing;

import com.resume.Backend.Service.extraction.ExtractionService;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Set;

@Component
public class ParsingService {

    ExtractionService extraction;


    ParsingService(ExtractionService extraction) {
        this.extraction = extraction;
    }



    public String extractStringFromFile(File resumeFile) throws IOException {

        // Entire file content will be stored as String
        String fileContent = "";

        Tika tika = TikaSingleton.getInstance();

        // Returns the type of file
        String type = tika.detect(resumeFile);


        if (!(type.equals("application/pdf") || type.equals("application/msword"))) {
            throw new RuntimeException("Content in the file is not supported");
        }

        try {
            fileContent = tika.parseToString(resumeFile);
        } catch (TikaException obj) {
            obj.getStackTrace();
        }

        String formattedFileContent = formatFileContent(fileContent);

        Set<String> extractedSkills = extraction.extractSkills(formattedFileContent);

//        System.out.println(extractedSkills);


        //Returning the formatted file as String without extra spaces, lines.
        // Converts entire String into lowercase
        return formattedFileContent;
    }

    public String formatFileContent(String fileInfo) {

        return fileInfo.replaceAll("\s+", " ")
                .replaceAll("\n+", " ").
                trim().
                toLowerCase();

    }

}


