package ru.geekbrains.androidhw9and10.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import ru.geekbrains.androidhw9and10.MainActivity;
import ru.geekbrains.androidhw9and10.Navigation;
import ru.geekbrains.androidhw9and10.R;
import ru.geekbrains.androidhw9and10.data.Note;
import ru.geekbrains.androidhw9and10.data.NoteSource;
import ru.geekbrains.androidhw9and10.data.NoteSourceImpl;
import ru.geekbrains.androidhw9and10.observe.Publisher;

public class NotesFragment extends Fragment {

    private NoteSource data;
    private NotesAdapter adapter;
    private RecyclerView recyclerView;
    private Navigation navigation;
    private Publisher publisher;

    private FirebaseDatabase db = FirebaseDatabase.getInstance("https://notes-application-5f3dd-default-rtdb.firebaseio.com/");
    private DatabaseReference root = db.getReference().child("Notes");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new NoteSourceImpl(getResources()).init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notes_fragment, container, false);
        initViews(view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.notes_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_note:
                navigation.addFragment(NoteFragment.newInstance(), true);
                publisher.subscribe(note -> {
                    data.addNote(note);
                    Map<String, String> map = new HashMap<>();
                    map.put("title", note.getTitle());
                    map.put("description", note.getContent());
                    map.put("data", note.getDateCreated());
                    root.push().setValue(map);
                    adapter.notifyItemInserted(data.size() - 1);
                });
                //addExampleNote();
                return true;
            case R.id.action_clear_notes:
                clearNotes();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.note_menu, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getMenuPosition();
        switch (item.getItemId()) {
            case R.id.action_update_note:
                navigation.addFragment(NoteFragment.newInstance(data.getNoteData(position)), true);
                publisher.subscribe(note -> {
                    data.updateNote(position, note);
                    adapter.notifyItemChanged(position);
                });
                return true;
            case R.id.action_delete_note:
                data.deleteNote(position);
                adapter.notifyItemRemoved(position);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void clearNotes() {
        data.clearNotes();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainActivity activity = (MainActivity) context;
        navigation = activity.getNavigation();
        publisher = activity.getPublisher();
    }

    @Override
    public void onDetach() {
        navigation = null;
        publisher = null;
        super.onDetach();
    }

    private void addExampleNote() {
        data.addNote(new Note("ExampleNote " + data.size(), "Description " + data.size()));
        adapter.notifyItemInserted(data.size() - 1);
        recyclerView.scrollToPosition(data.size() - 1);
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_lines);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);

        adapter = new NotesAdapter(data, this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.separator, null));
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setItemClickListener((view, position) -> Toast.makeText(getContext(), String.format("Позиция - %d", position), Toast.LENGTH_SHORT).show());
    }
}
