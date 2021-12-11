package ru.geekbrains.androidhw9and10.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ru.geekbrains.androidhw9and10.R;

public class AboutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setActionBar();
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    private void setActionBar() {
        setHasOptionsMenu(true);
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle("About");
        }
    }
}
