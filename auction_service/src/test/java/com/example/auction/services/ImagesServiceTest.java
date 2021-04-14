package com.example.auction.services;

import com.example.auction.repositories.ImagesRepository;
import com.example.auction.utils.FileUploadUtil;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ImagesServiceTest {

    @Mock
    private ImagesRepository imagesRepository;

    @InjectMocks
    private ImagesService imagesService;

    @TempDir
    Path directory;

    @Test
    void shouldUploadFiles(){
        File file = new File(directory.toString());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "content", IOUtils.toByteArray(fileInputStream));
            imagesService.uploadFiles(List.of(multipartFile), directory.toString());
            Stream<Path> list = Files.list(directory);
            System.out.println(list);
            assertTrue(list.findFirst().isPresent());
        }
        catch (IOException e){
            System.out.println(e);
        }


    }

}