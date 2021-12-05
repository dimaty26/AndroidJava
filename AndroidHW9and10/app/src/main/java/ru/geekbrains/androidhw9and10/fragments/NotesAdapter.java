package ru.geekbrains.androidhw9and10.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.androidhw9and10.R;
import ru.geekbrains.androidhw9and10.data.Note;
import ru.geekbrains.androidhw9and10.data.NoteSource;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private NoteSource noteSource;
    private OnItemClickListener itemClickListener;

    public NotesAdapter(NoteSource noteSource) {
        this.noteSource = noteSource;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.bind(noteSource.getNoteData(position));
    }

    @Override
    public int getItemCount() {
        return noteSource.size();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView content;
        private TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);

            content.setOnClickListener(view -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }

        public void bind(Note note) {
            title.setText(note.getTitle());
            content.setText(note.getContent());
            date.setText(note.getDateCreated());
        }
    }
}
