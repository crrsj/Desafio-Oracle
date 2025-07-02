package br.com.gutendex.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Entity
public class Book {
    @Id
    private Integer id;
    private String title;
    private String author;
    private String downloadUrl;
    @Version
    private Integer version = 0;

    @JsonCreator
    public Book(@JsonProperty("id") Integer id,
                @JsonProperty("title") String title,
                @JsonProperty("authors") List<Map<String, Object>> authors,
                @JsonProperty("formats") Map<String, String> formats) {
        this.id = id;
        this.title = title;
        this.author = authors != null && !authors.isEmpty() ?
                (String) authors.get(0).get("name") : "Unknown";
        this.downloadUrl = formats != null ? formats.get("application/epub+zip") : null;
        this.version = 0;

    }

    public Book() {
        this.version = 0;
    }
}
