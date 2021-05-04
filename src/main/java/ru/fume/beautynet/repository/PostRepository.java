package ru.fume.beautynet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fume.beautynet.entity.Post;
import ru.fume.beautynet.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // SELECT * FROM POST as p WHERE User='user' SORT DESC
    List<Post> findAllByUserOrderByCreatedDateDesc(User user);

    List<Post> findAllByOrderByCreatedDataDesc();

    Optional<Post> findPostByIdAndUser(Long id, User user);
}
