package ru.hogwarts.school.REST_APP.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.REST_APP.model.Avatar;
import ru.hogwarts.school.REST_APP.repository.AvatarRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class AvatarService {

    @Value("${upload.path}") // Get the upload path from application.properties
    private String uploadPath;

    private final AvatarRepository avatarRepository;

    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Avatar uploadImage(MultipartFile file) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = uuid + extension;
        Path filePath = Paths.get(uploadPath, fileName);
        Files.createDirectories(filePath.getParent()); // Create directories if needed
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        Avatar image = new Avatar();
        image.setName(fileName);
        image.setPath(filePath.toString());
        image.setData(file.getBytes());
        return avatarRepository.save(image);
    }

    public byte[] getImageData(Long id) {
        Avatar image = avatarRepository.findById(id);
        return image.getData();
    }

    public byte[] getImageDataFromPath(Long id) throws IOException {
        Avatar image = avatarRepository.findById(id);
        Path filePath = Paths.get(image.getPath());
        return Files.readAllBytes(filePath);
    }
}