package group.Food_order_bachelor.service.imageService;

import group.Food_order_bachelor.model.Image;
import group.Food_order_bachelor.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageServiceInterface {
    private final ImageRepository imageRepository;
    @Override
    public String buildImager(MultipartFile multipartFile) {
        String fileName = generateDisplayName(multipartFile.getOriginalFilename());

        Image image = Image.builder()
                .id(UUID.randomUUID())
                .fileName(fileName)
                .fileType(multipartFile.getContentType())
                .size(multipartFile.getSize())
                .build();
        try {
            var flag = multipartFile.getBytes();
            image.setData(flag);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageRepository.save(image);
        return image.getId().toString();
    }

    @Override
    public Image getImageById(String imageId) {
        return imageRepository.getReferenceById(UUID.fromString(imageId));
    }

    public String generateUniqueNumber() {
        int min = 10000;
        int max = 99999;
        int random_int = (int) (Math.random() * (max - min + 1) + min);
        return "" + random_int;
    }

    public String generateFileName(String fileName) {

        // generate random alphabet
        String shortRandomAlphabet = generateUniqueNumber();

        // create date format as string
        String dateStrFormat = "yyyy-MM-ddTHH-mm-ss";

        // find extension of file
        int indexOfExtension = fileName.indexOf(".");
        String extensionName = fileName.substring(indexOfExtension);

        // return new file name
        return dateStrFormat + "_" + shortRandomAlphabet + extensionName;

    }

    public String generateDisplayName(String orgFileName) {
        String orgCleanPath = StringUtils.cleanPath(orgFileName);

        // Check if the file's name contains invalid characters
        if (orgCleanPath.contains(".."))
            throw new RuntimeException("Sorry! Filename contains invalid path sequence " + orgCleanPath);

        // generate new file name
        return generateFileName(orgCleanPath);
    }
}
