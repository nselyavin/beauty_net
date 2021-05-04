package ru.fume.beautynet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fume.beautynet.entity.Comment;
import ru.fume.beautynet.entity.Post;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Post> findAllByPost(Post post);

    Comment findByIdAndUserId(Long commentId, Long userId);
}
