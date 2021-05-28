package com.example.demo.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private String brand;
    private String username;
    private MultipartFile image;
    private String markedUsername;
    private String tagsStr;
}
