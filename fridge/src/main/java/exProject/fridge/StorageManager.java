package exProject.fridge;

import com.amazonaws.services.s3.AmazonS3;
import exProject.fridge.service.StorageService;
import exProject.fridge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@Component
@RequiredArgsConstructor
public class StorageManager {

    private final StorageService storageService;
    private final AmazonS3 s3Client;

    public String getImage(String imageId) {

        URL url = s3Client.getUrl("fridgeproject", imageId);
        String urltext = ""+url;

        return urltext;
    }

    public void saveImage(String imageId, MultipartFile file) throws IOException {
        storageService.uploadFile(file, imageId);
    }

    public void deleteImage(String imageId) throws IOException {
        storageService.deleteFile(imageId);
    }
}

