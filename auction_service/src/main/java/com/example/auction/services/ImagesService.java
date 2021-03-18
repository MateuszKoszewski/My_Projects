package com.example.auction.services;

import com.example.auction.model.dao.ImageEntity;
import com.example.auction.repositories.ImagesRepository;
import com.example.auction.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagesService {

    private final ImagesRepository imagesRepository;

    public void uploadFiles(List<MultipartFile> listOfFiles, String directory) {
        for (int i = 0; i < listOfFiles.size(); i++) {
            String fileName = listOfFiles.get(i).getOriginalFilename();
            try {
                FileUploadUtil.saveFile(directory, fileName, listOfFiles.get(i));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ImageEntity getImageEntityFromFile(MultipartFile file, boolean isMainImage, String directory) {
        ImageEntity imageEntity = new ImageEntity();
        String uploadPath = directory + "/" + file.getOriginalFilename();
        imageEntity.setPath(uploadPath);
        imageEntity.setMain(isMainImage);
        return imageEntity;
    }

    public List<ImageEntity> getImageEntityList(List<MultipartFile> listOfFiles, String directory) {
        List<ImageEntity> listOfImageEntity = new ArrayList<>();
        ImageEntity mainImageToAdd = getImageEntityFromFile(listOfFiles.get(0), true, directory);
        listOfImageEntity.add(mainImageToAdd);
        for (int i = 1; i < listOfFiles.size(); i++) {
            ImageEntity imageToAdd = getImageEntityFromFile(listOfFiles.get(i), false, directory);
            listOfImageEntity.add(imageToAdd);
        }
        return listOfImageEntity;
    }

    public void addImagesEntityDataToDb(List<ImageEntity> listOfImageEntity) {
        for (int i = 0; i < listOfImageEntity.size(); i++) {
            imagesRepository.save(listOfImageEntity.get(i));
        }
    }
}
