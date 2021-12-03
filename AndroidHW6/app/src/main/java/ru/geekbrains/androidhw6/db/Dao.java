package ru.geekbrains.androidhw6.db;

import ru.geekbrains.androidhw6.model.Note;

public interface Dao {
    void addNote(Note note);
    void updateNote(Note note);
}
