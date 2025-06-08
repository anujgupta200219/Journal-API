package com.anuj.second.entity;

import com.anuj.second.enums.sentiment;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "entries")
@Data
@NoArgsConstructor
public class entry {
    @Id
    private String id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    private sentiment sentiment;
}
