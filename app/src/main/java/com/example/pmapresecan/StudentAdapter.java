package com.example.pmapresecan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Student> studenti;
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_STUDENT = 1;
    private Context context;

    public StudentAdapter(List<Student> studenti, Context context){
        this.studenti = studenti;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position){
        if(position == 0){
            return VIEW_TYPE_HEADER;
        } else {
            return VIEW_TYPE_STUDENT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_HEADER){
            View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout, parent, false);
            return new HeaderViewHolder(headerView);
        }
        else {
            View studentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_layout, parent, false);
            return new StudentViewHolder(studentView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof StudentViewHolder){
            StudentViewHolder studentViewHolder=(StudentViewHolder) holder;
            Student student =studenti.get(position-1);
            studentViewHolder.textIme.setText(student.getIme());
            studentViewHolder.textPrezime.setText(student.getPrezime());
            studentViewHolder.textPredmet.setText(student.getPredmet());
            if (student.getSlika() != null && !student.getSlika().isEmpty()) {
                Glide.with(holder.itemView.getContext())
                        .load(student.getSlika())
                        .into(studentViewHolder.imageView);
            } else {

            }
        }
        if (holder instanceof HeaderViewHolder){
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    context, R.array.languages, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            headerViewHolder.spinnerLanguage.setAdapter(adapter);

            headerViewHolder.spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (context instanceof HomeActivity) {
                        ((HomeActivity) context).onItemSelected(parent, view, position, id);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            headerViewHolder.textHeader.setText(context.getResources().getString(R.string.header_text));
        }
    }

    @Override
    public int getItemCount() {
        return studenti.size() + 1;
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView textHeader;
        Spinner spinnerLanguage;
        HeaderViewHolder(View view) {
            super(view);
            textHeader = view.findViewById(R.id.textHeader);
            spinnerLanguage = view.findViewById(R.id.spinnerLanguage);
        }
    }

    private class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView textIme, textPrezime, textPredmet;
        ImageView imageView;
        StudentViewHolder(View view) {
            super(view);
            textIme = view.findViewById(R.id.textIme);
            textPrezime = view.findViewById(R.id.textPrezime);
            textPredmet = view.findViewById(R.id.textPredmet);
            imageView = view.findViewById(R.id.imageView);
        }
    }
}