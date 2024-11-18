package ru.hogwarts.school.REST_APP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.REST_APP.model.Avatar;
import ru.hogwarts.school.REST_APP.service.AvatarService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/images")
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    @PostMapping
    public ResponseEntity<Avatar> upload(@RequestParam("file") MultipartFile file) throws IOException {
        Avatar image = avatarService.uploadImage(file);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping("/{id}/db")
    public ResponseEntity<byte[]> getImageFromDb(@PathVariable Long id) {
        byte[] imageData = avatarService.getImageData(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> getImageFromFile(@PathVariable Long id) throws IOException {
        byte[] imageData = avatarService.getImageDataFromPath(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }

    @GetMapping("/get-{allAvatars}+{sizePage}")
    public ResponseEntity<List<Avatar>> getAllAvatars(@PathVariable Integer allAvatars, @PathVariable Integer sizePage) {
        return new ResponseEntity<List<Avatar>>(avatarService.getAllAvatars(allAvatars, sizePage), HttpStatus.OK);
    }
}
