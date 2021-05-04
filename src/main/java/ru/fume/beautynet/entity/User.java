package ru.fume.beautynet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import ru.fume.beautynet.entity.enums.ERole;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity - номер будети увеличиваться вверх
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, updatable = true)
    private String username;
    @Column(nullable = false)
    private String lastname;
    @Column(unique = true, updatable = false)
    private String email;
    @Column(nullable = true, columnDefinition = "text")
    private String about;
    @Column(length = 3000)
    private String password;

    // Создать отдельную таблицу и связать айдишник и роль пользователя
    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> role = new HashSet<>();

    // Каскадное поведение, когда удалим пользователя, то все прилегающие посты, тоже будут удалены из базы данных
    // fetch Lazy type, не надо получать все посты из бд, когда пытаемся получить данные юзера. Мы будем получать дан-
    // ные только, когда сами захотим.
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdData;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @PrePersist
    protected void onCreate(){
        this.createdData = LocalDateTime.now();
    }
}
