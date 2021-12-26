package ru.geekbrains.androidhw9and10.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Note {
    private String title;
    private String content;
    private LocalDateTime dateCreated;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        dateCreated = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateCreated() {
        return dateCreated.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
