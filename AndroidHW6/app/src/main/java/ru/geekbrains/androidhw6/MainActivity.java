package ru.geekbrains.androidhw6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.geekbrains.androidhw6.fragments.NotesMainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotesMainFragment notesMainFragment = new NotesMainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, notesMainFragment)
                .commit();
    }
}