package org.example.bettersleepserver.repository;



import org.example.bettersleepserver.entity.Sound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundRepository extends JpaRepository<Sound, Long> {
    // You can add custom query methods here if needed
}
