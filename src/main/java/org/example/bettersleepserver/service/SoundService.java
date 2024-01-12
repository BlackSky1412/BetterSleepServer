package org.example.bettersleepserver.service;

import org.example.bettersleepserver.entity.Sound;
import org.example.bettersleepserver.repository.SoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoundService {

    private final SoundRepository soundRepository;

    @Autowired
    public SoundService(SoundRepository soundRepository) {
        this.soundRepository = soundRepository;
    }

    public List<Sound> getAllSounds() {
        return soundRepository.findAll();
    }

    public Sound getSoundById(Long id) {
        Optional<Sound> optionalSound = soundRepository.findById(id);
        return optionalSound.orElse(null);
    }

    public Sound saveSound(Sound sound) {
        return soundRepository.save(sound);
    }

    public Optional<Sound> updateSound(Long id, Sound updatedSound) {
        return soundRepository.findById(id)
                .map(existingSound -> {
                    existingSound.setName(updatedSound.getName());
                    existingSound.setUrlSound(updatedSound.getUrlSound());
                    existingSound.setDuration(updatedSound.getDuration());
                    existingSound.setSlogan(updatedSound.getSlogan());
                    existingSound.setAuthor(updatedSound.getAuthor());
                    existingSound.setColor(updatedSound.getColor());
                    return soundRepository.save(existingSound);
                });
    }

    public void deleteSound(Long id) {
        soundRepository.deleteById(id);
    }
}
