package com.anuj.second.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")

@Data
@NoArgsConstructor
public class user {
    @Id
    private String id;

    @NonNull
    @Indexed(unique = true)
    private String username;

    @NonNull
    private String password;

    private String email;

    private boolean sanalysis;

    @DBRef
    private List<entry> entries=new ArrayList<>();
    private List<String> roles;
}
