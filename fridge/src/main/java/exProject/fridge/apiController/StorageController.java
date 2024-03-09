package exProject.fridge.apiController;

import com.amazonaws.services.s3.AmazonS3;
import exProject.fridge.dto.ResponseDto;
import exProject.fridge.service.StorageService;
import exProject.fridge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@RestController
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;
    private final UserService userService;
    private final AmazonS3 s3Client;


    @GetMapping("/{imageId}")
    public ResponseDto<String> getImage(@PathVariable("imageId") long imageId) {

        URL url = s3Client.getUrl("fridgeproject", Long.toString(imageId));
        String urltext = ""+url;

        return new ResponseDto<String>(HttpStatus.OK.value(), urltext);
    }

    @PostMapping("/{imageId}")
    public ResponseDto<Integer> saveImage(
            @PathVariable("imageId") long imageId,
            @RequestParam(value = "file") MultipartFile file) throws IOException {

        storageService.deleteFile(imageId);
        storageService.uploadFile(file, imageId);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}

