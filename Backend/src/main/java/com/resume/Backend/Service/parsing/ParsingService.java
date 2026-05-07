package com.resume.Backend.service.parsing;

import com.resume.Backend.ExceptionHandling.customexception.InvalidFileException;
import com.resume.Backend.ExceptionHandling.customexception.ResumeProcessingException;
import com.resume.Backend.service.extraction.ExtractionService;
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

    public Tika givesTika() {
        return TikaSingleton.getInstance();
    }


    public String extractStringFromFile(File resumeFile){

        // Entire file content will be stored as String
        String fileContent = "";
        String type = "";

        Tika tika = givesTika();

        try {
            // Returns the type of file
            type = tika.detect(resumeFile);
        } catch(IOException ex) {
            throw new ResumeProcessingException("Failed to detect the type of file",
                    ex);
        }

        if (!(type.equals("application/pdf") || type.equals("application/msword"))) {
            throw new InvalidFileException("Content in the file is not supported. " +
                    "Only pdf or doc type files are allowed");
        }

        try {
            fileContent = tika.parseToString(resumeFile);
        } catch (IOException obj1) {
            throw new ResumeProcessingException("Failed to parse the Resume file",
                    obj1);
        } catch(TikaException obj2) {
            throw new ResumeProcessingException("Failed to parse the resume file, tika exception occured",
                    obj2);
        }

        String formattedFileContent = formatFileContent(fileContent);
        Set<String> extractedSkills = extraction.extractSkills(formattedFileContent);

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


