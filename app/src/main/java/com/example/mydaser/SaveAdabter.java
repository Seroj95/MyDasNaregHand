package com.example.mydaser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SaveAdabter extends RecyclerView.Adapter<SaveAdabter.SaveViewHolder> {
    private List<Timer> timerArrayList;
    private OnTimerClickLisner onTimerClickLisner;
    public SaveAdabter(List<Timer> timerArrayList) {
        this.timerArrayList = timerArrayList;
    }
    interface OnTimerClickLisner {
        void onTimerClick(int position);
        void onLongTimerClick(int position);
    }
    public SaveAdabter(OnTimerClickLisner onTimerClickLisner) {
        this.onTimerClickLisner = onTimerClickLisner;
    }

    public void setOnTimerClickLisner(OnTimerClickLisner onTimerClickLisner) {
        this.onTimerClickLisner = onTimerClickLisner;
    }

    @NonNull
    @Override
    public SaveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.save_item, parent, false);
        return new SaveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaveViewHolder holder, int position) {
        Timer timer = timerArrayList.get(position);
        holder.saveTextView.setText(timer.getSave());
    }

    @Override
    public int getItemCount() {
        return timerArrayList.size();
    }

    class SaveViewHolder extends RecyclerView.ViewHolder {
        private TextView saveTextView;

        public SaveViewHolder(View itemView) {
            super(itemView);
            saveTextView = itemView.findViewById(R.id.saveTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onTimerClickLisner != null) {
                        onTimerClickLisner.onTimerClick(getAdapterPosition());

                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onTimerClickLisner != null) {
                        onTimerClickLisner.onLongTimerClick(getAdapterPosition());
                    }
                    return false;
                }
            });
        }
    }
}
