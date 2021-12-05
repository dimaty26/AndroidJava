package ru.geekbrains.androidhw9and10.data;

public interface NoteSource {
    Note getNoteData(int position);

    int size();
}
