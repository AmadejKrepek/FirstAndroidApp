package basic.vaja;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class UserRVAdapter extends ListAdapter<UserEntity, UserRVAdapter.ViewHolder> {

    // creating a variable for on item click listener.
    private OnItemClickListener listener;

    // creating a constructor class for our adapter class.
    UserRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<UserEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserEntity>() {
        @Override
        public boolean areItemsTheSame(UserEntity oldItem, UserEntity newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(UserEntity oldItem, UserEntity newItem) {
            // below line is to check the user name, description and user duration.
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getSurname().equals(newItem.getSurname()) &&
                    oldItem.getDateOfBirth().equals(newItem.getDateOfBirth()) &&
                    oldItem.getHeartRate().equals(newItem.getHeartRate()) &&
                    oldItem.getSo2().equals(newItem.getSo2()) &&
                    oldItem.getBodyTemperature().equals(newItem.getBodyTemperature());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is use to inflate our layout
        // file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // below line of code is use to set data to
        // each item of our recycler view.
        UserEntity model = getuserAt(position);
        holder.name.setText(model.getName());
        holder.surname.setText(model.getSurname());
        holder.dateOfBirth.setText(model.getDateOfBirth());
        holder.heartRate.setText(model.getHeartRate());
        holder.so2.setText(model.getSo2());
        holder.bodyTemperature.setText(model.getBodyTemperature());
    }

    // creating a method to get user modal for a specific position.
    public UserEntity getuserAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // view holder class to create a variable for each view.
        TextView name, surname, dateOfBirth, heartRate, so2, bodyTemperature;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing each view of our recycler view.
            name = itemView.findViewById(R.id.idUserName);
            surname = itemView.findViewById(R.id.idUserSurname);
            dateOfBirth = itemView.findViewById(R.id.idUserDateOfBirth);
            heartRate = itemView.findViewById(R.id.idUserHeartRate);
            so2 = itemView.findViewById(R.id.idUserSo2);
            bodyTemperature = itemView.findViewById(R.id.idUserBodyTemperature);

            // adding on click listener for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // inside on click listener we are passing
                    // position to our item of recycler view.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(UserEntity model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
