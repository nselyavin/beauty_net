package ru.fume.beautynet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fume.beautynet.entity.Photo;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository {
    Optional<Photo> findByUserId(Long userId);

    Optional<Photo> findByPostId(Long postId);
}
