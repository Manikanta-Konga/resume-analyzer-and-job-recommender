package com.resume.Backend.Service;

import com.resume.Backend.Parser.ResumeParsing;
import com.resume.Backend.Repository.ResumeRepo;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class ResumeService {

    private final ResumeRepo repo;

    private final ResumeParsing parser;



    ResumeService(ResumeRepo repo, ResumeParsing parser) {
        this.repo = repo;
        this.parser = parser;
    }

    public String uploadResume(MultipartFile resFile) throws IOException{

        String fileName = resFile.getOriginalFilename();

        long size = resFile.getSize();

        double fileSize = (double) size / (1024 * 1024);

        if(fileSize >= 5) {
            throw new RuntimeException("File size too large");
        }

        if(resFile.isEmpty()) {
            throw new RuntimeException("File is Empty, again upload resume");
        }

//        assert fileName != null;
        if(fileName.isEmpty()) {
            throw new RuntimeException("No File Name");
        }

        LocalDateTime time = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");

        String timeStamp = time.format(formatter);

        String uuId = UUID.randomUUID().toString().replace("-", "");

        // Handle NullPointerException
        int idxOfDot = fileName.lastIndexOf(".");

        if(idxOfDot == -1) {
            throw new RuntimeException("No File extension, upload proper file.");
        }

        // Gives the name without taking extensions
        String name = fileName.substring(0, idxOfDot);

        // Stores the extensions, and converts to lowercase
        String extension = fileName.substring(idxOfDot).toLowerCase();

        String newFileName;     // new name for file, before storing it.

        // Checks whether the entered resume is supported format or not
        if(!(extension.equals(".pdf") || extension.equals(".doc") || extension.equals(".docx"))) {
            throw new RuntimeException("Format not supporting, upload pdf, doc or docx file");
        }
        newFileName = name + "_" + timeStamp + "_" + uuId + extension;


        // Saving the file

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
        File destination = new File(folder, newFileName);

//        File parent = destination.getParentFile();
//        System.out.println();
//        System.out.println("Before mkdirs: "+parent.exists());
//        System.out.println("using mkdirs: "+parent.mkdirs());
//        System.out.println("after mkdirs: "+parent.exists());
//        System.out.println();
//        System.out.println("is file is empty: "+resFile.isEmpty()+ ",  Size: "+resFile.getSize());
//
//        System.out.println();
//        System.out.println("Current Working Directory: "+System.getProperty("user.dir"));
//        System.out.println();
//        System.out.println("File storing at : "+destination.getAbsolutePath());
//        System.out.println(destination.exists());
//        System.out.println();

        resFile.transferTo(destination);

        String parsedText = parser.extractStringFromFile(destination);

        return parsedText;
    }


}
