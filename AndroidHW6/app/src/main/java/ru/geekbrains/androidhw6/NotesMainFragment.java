package ru.geekbrains.androidhw6;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesMainFragment extends Fragment {

    public NotesMainFragment() {
        // Required empty public constructor
    }

    public static NotesMainFragment newInstance(String param1, String param2) {
        NotesMainFragment fragment = new NotesMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes_main, container, false);
        MaterialButton addNoteBtn = view.findViewById(R.id.add_new_note_button);
        addNoteBtn.setOnClickListener(v -> {
            NoteFragment noteFragment = NoteFragment.newInstance();
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_notes_container, noteFragment)
                    .addToBackStack("")
                    .commit();
        });
        return view;
    }
}