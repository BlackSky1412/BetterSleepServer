package org.example.bettersleepserver.controller;

import org.example.bettersleepserver.entity.Sound;
import org.example.bettersleepserver.service.SoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;



import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sounds")
@CrossOrigin(origins = "http://localhost:3000") // Replace with the Flutter server address
public class SoundController {

    @Autowired
    private SoundService soundService;


    @GetMapping
    public List<SoundApiResponse> listSounds() {
        List<Sound> sounds = soundService.getAllSounds();
        // Convert the list of sounds to a list of SoundApiResponse objects
        List<SoundApiResponse> response = sounds.stream()
                .map(sound -> new SoundApiResponse(
                        sound.getId(),
                        sound.getName(),
                        sound.getColor(),
                        sound.getDuration(),
                        sound.getSlogan(),
                        sound.getAuthor(),
                        sound.getUrlSound()
                ))
                .collect(Collectors.toList());

        return response;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SoundApiResponse getSoundById(@PathVariable Long id) {
        Sound sound = soundService.getSoundById(id);
        // Convert the Sound object to a SoundApiResponse object
        return new SoundApiResponse(
                sound.getId(),
                sound.getName(),
                sound.getColor(),
                sound.getDuration(),
                sound.getSlogan(),
                sound.getAuthor(),
                sound.getUrlSound()
        );
    }

    @GetMapping(value = "/mp3/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getMp3File(@PathVariable String fileName) {
        return buildResponseEntity(fileName);
    }
    private ResponseEntity<Resource> buildResponseEntity(String fileName) {
        // Đường dẫn của file MP3 trong resources folder
        String filePath = "static/sound/" + fileName;

        // Thêm đuôi .mp3 nếu không có
        if (!fileName.endsWith(".mp3")) {
            filePath += ".mp3";
        }

        // Tạo một Resource từ đường dẫn file
        Resource resource = new ClassPathResource(filePath);

        // Kiểm tra xem file có tồn tại không
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Xác định content type của file MP3
        String contentType;
        try {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        } catch (Exception e) {
            contentType = MediaType.APPLICATION_OCTET_STREAM.toString();
        }

        // Trả về file MP3 với content type và các headers phù hợp
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
