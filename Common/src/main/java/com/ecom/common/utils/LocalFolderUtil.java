package com.ecom.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Data
@AllArgsConstructor
@Slf4j
public class LocalFolderUtil {

    private String path;


    public  String upload(MultipartFile file, String filename) {

        String filepath = this.path + "\\" + filename;
        Path path = Paths.get(filepath);

        try{
            Files.deleteIfExists(path);}
         catch (IOException e) {
            throw new RuntimeException(e);
        }

        File newFile = new File(filepath);

        try {
//            if(newFile.exists()) {
//                newFile.delete();
//            }
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filepath;


    }
}
