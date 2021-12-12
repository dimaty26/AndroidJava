package ru.geekbrains.androidhw9and10.observe;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.androidhw9and10.data.Note;

public class Publisher {
    private List<Observer> observerList;

    public Publisher() {
        this.observerList = new ArrayList<>();
    }

    public void subscribe(Observer observer) {
        observerList.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observerList.remove(observer);
    }

    public void notifySingle(Note note) {
        for (Observer observer : observerList) {
            observer.updateNote(note);
            unsubscribe(observer);
        }
    }
}
