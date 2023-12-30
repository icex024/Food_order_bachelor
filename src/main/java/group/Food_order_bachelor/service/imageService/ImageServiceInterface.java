package group.Food_order_bachelor.service.imageService;

import group.Food_order_bachelor.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageServiceInterface {
    String buildImager(MultipartFile multipartFile);
    Image getImageById(String imageId);
}
