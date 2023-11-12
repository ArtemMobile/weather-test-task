package com.example.weathertestapp.presentation.history;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weathertestapp.data.source.local.sqlite.HistoryModel;
import com.example.weathertestapp.databinding.HistoryCardBinding;
import java.util.Objects;

public class HistoryAdapter extends ListAdapter<HistoryModel, HistoryAdapter.HistoryHolder> {

    private final HistoryHolder.OnItemClickListener listener;

    protected HistoryAdapter(HistoryHolder.OnItemClickListener listener) {
        super(new TaskDiffCallBack());
        this.listener = listener;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryHolder(HistoryCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class HistoryHolder extends RecyclerView.ViewHolder {
        private final HistoryCardBinding binding;

        public HistoryHolder(HistoryCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(HistoryModel historyModel) {
            binding.textViewLocation.setText(historyModel.fullLocation());
            binding.textViewConditionAndTemp.setText(historyModel.condition());
            binding.textViewHumidity.setText(String.valueOf(historyModel.humidity()));
            binding.textViewWindSpeed.setText(String.valueOf(historyModel.wind()));
            binding.textViewLocationAndTime.setText(historyModel.localtime());
            binding.imageViewDelete.setOnClickListener(view -> listener.onItemClick(historyModel.Id()));
        }

        public interface OnItemClickListener {
            void onItemClick(Long id);
        }

    }
}

class TaskDiffCallBack extends DiffUtil.ItemCallback<HistoryModel> {

    @Override
    public boolean areItemsTheSame(@NonNull HistoryModel oldItem, @NonNull HistoryModel newItem) {
        return Objects.equals(oldItem.Id(), newItem.Id());
    }

    @Override
    public boolean areContentsTheSame(@NonNull HistoryModel oldItem, @NonNull HistoryModel newItem) {
        return oldItem.Id().equals(newItem.Id());
    }
}


