package com.anuj.second.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")

@Data
@NoArgsConstructor
public class configentity {
    private String key;
    private String value;
}
