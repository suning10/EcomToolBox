package com.ecom.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Data
@AllArgsConstructor
@Slf4j
public class LocalFolderUtil {

    private String path;


    public String upload(MultipartFile file, String filename) {

        String filepath = this.path + "\\" + filename;

        File newFile = new File(filepath);

        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return filepath;


    }
}
