package group.Food_order_bachelor.controller;

import group.Food_order_bachelor.repository.ImageRepository;
import group.Food_order_bachelor.service.imageService.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    @PostMapping("/upload-image")
    @CrossOrigin("http://localhost:3000")
    public String uploadImage(@RequestParam("file") MultipartFile file){
        return imageService.buildImager(file);
    }

    @GetMapping("/get-image-test")
    @CrossOrigin("http://localhost:3000")
    public Resource test(@RequestParam String id){
        var byteArray = imageRepository.getReferenceById(UUID.fromString(id)).getData();
        return new ByteArrayResource(byteArray);
    }
}
