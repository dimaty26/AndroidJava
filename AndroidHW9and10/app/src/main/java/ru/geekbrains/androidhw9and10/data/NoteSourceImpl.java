package ru.geekbrains.androidhw9and10.data;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.androidhw9and10.R;

public class NoteSourceImpl implements NoteSource {

    private List<Note> dataSource;
    private Resources resources;

    public NoteSourceImpl(Resources resources) {
        this.dataSource = new ArrayList<>();
        this.resources = resources;
    }

    public NoteSource init() {
        String[] titles = resources.getStringArray(R.array.titles);
        String[] descriptions = resources.getStringArray(R.array.descriptions);
        int length = descriptions.length;
        for (int i = 0; i < length; i++) {
            dataSource.add(new Note(titles[i], descriptions[i]));
        }
        return this;
    }

    @Override
    public Note getNoteData(int position) {
        return dataSource.get(position);
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public void deleteNote(int position) {
        dataSource.remove(position);
    }

    @Override
    public void updateNote(int position, Note note) {
        dataSource.set(position, note);
    }

    @Override
    public void addNote(Note note) {
        dataSource.add(note);
    }

    @Override
    public void clearNotes() {
        dataSource.clear();
    }
}
