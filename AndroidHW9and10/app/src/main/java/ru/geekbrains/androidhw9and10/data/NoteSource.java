package ru.geekbrains.androidhw9and10.data;

public interface NoteSource {

    Note getNoteData(int position);

    int size();

    void deleteNote(int position);

    void updateNote(int position, Note note);

    void addNote(Note note);

    void clearNotes();
}
