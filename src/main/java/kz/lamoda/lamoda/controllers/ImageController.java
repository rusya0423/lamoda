package kz.lamoda.lamoda.controllers;

import kz.lamoda.lamoda.models.Image;
import kz.lamoda.lamoda.repositories.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 3.11.2019
 * @project EShopSpring
 */
@Controller
@RequestMapping("/images")
@AllArgsConstructor
public class ImageController {
    private final String NO_IMAGE = "no-image.png";
    private final ImageRepository imageRepository;

    @GetMapping("{id}")
    public ResponseEntity<byte[]> fileName(@PathVariable Long id) throws Exception {
        String fileName;

        Optional<Image> imageOpt = imageRepository.findByDress_Id(id);
        if (imageOpt.isPresent()) {
            fileName = imageOpt.get().getPath();
        } else {
            fileName = NO_IMAGE;
        }


        Path filePath = Paths.get("var/tmp")
                .toAbsolutePath().normalize().resolve(fileName);
        MediaType mediaType = null;
        mediaType = MediaType.IMAGE_JPEG;

        Resource resource = new UrlResource(filePath.toUri());
        return ResponseEntity.ok().contentType(mediaType).body(Files.readAllBytes(filePath));
    }

}