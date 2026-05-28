package com.resume.Backend.storingindb;

import com.resume.Backend.exceptionhandling.customexception.InvalidFileException;
import com.resume.Backend.exceptionhandling.customexception.ResumeProcessingException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class FileStoringService {

    public String createUniqueFileName(String fileName) {

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
        String timeStamp = time.format(formatter);

        String uuId = UUID.randomUUID()
                .toString()
                .replace("-", "");

        // Handle NullPointerException
        int idxOfDot = fileName.lastIndexOf(".");

        if(idxOfDot == -1) {
            throw new InvalidFileException("No File extension, upload proper file.");
        }

        // Gives the name without taking extensions
        String name = fileName.substring(0, idxOfDot);

        // Stores the extensions, and converts to lowercase
        String extension = fileName.substring(idxOfDot).toLowerCase();

        String newFileName;     // new name for file, before storing it.

        // Checks whether the entered resume is supported format or not
        if(!(extension.equals(".pdf") || extension.equals(".doc") || extension.equals(".docx"))) {
            throw new InvalidFileException("Format not supporting, upload pdf, doc or docx file");
        }

        newFileName = name + "_" + timeStamp + "_" + uuId + extension;

        return newFileName;
    }

    // Returns the destination path of the file
    public File storeFileInServerFile(String fileName, MultipartFile originalFile) {
        //To save the file, I'm using the absolute path.
        //Because when I used relative path, spring is storing the file in tomcat temporary folder.
        String fileDir = System.getProperty("user.dir") + File.separator + "uploads/resumes";

        File folder = new File(fileDir);

        // It checks whether folder exists or not.
        // If it not exists it creates the folder in the root directory
        if(!folder.exists()) {
            folder.mkdirs();
        }

//         It doesn't create the file, it only represents the file path in the system.
        File destination = new File(folder, fileName);

        try {
            originalFile.transferTo(destination);
        } catch(IOException ex) {
            throw new ResumeProcessingException("Failed to store the file in it's destination",
                    ex);
        }

        return destination;
    }

}
