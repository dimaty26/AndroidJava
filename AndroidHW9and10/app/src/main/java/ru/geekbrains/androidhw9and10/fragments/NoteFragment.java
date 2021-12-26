package ru.geekbrains.androidhw9and10.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import ru.geekbrains.androidhw9and10.MainActivity;
import ru.geekbrains.androidhw9and10.R;
import ru.geekbrains.androidhw9and10.data.Note;
import ru.geekbrains.androidhw9and10.observe.Publisher;

public class NoteFragment extends Fragment {

    private static final String ARG_NOTE = "Param_Note";

    private Note note;
    private Publisher publisher;

    private TextInputEditText title;
    private TextInputEditText description;

    public static NoteFragment newInstance(Note note) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    public static NoteFragment newInstance() {
        return new NoteFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        publisher = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        initView(view);

        if (note != null) {
            populateView();
        }
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        note = collectNote();
    }

    private Note collectNote() {
        String title = this.title.getText().toString();
        String description = this.description.getText().toString();
        return new Note(title, description);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        publisher.notifySingle(note);
    }

    private void populateView() {
        title.setText(note.getTitle());
        description.setText(note.getContent());
    }

    private void initView(View view) {
        title = view.findViewById(R.id.input_title);
        description = view.findViewById(R.id.input_description);
    }
}
