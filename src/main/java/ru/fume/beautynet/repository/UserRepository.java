package ru.fume.beautynet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fume.beautynet.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserName(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);
}
