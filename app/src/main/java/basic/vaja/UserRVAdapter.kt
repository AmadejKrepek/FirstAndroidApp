package basic.vaja

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


class UserRVAdapter // creating a constructor class for our adapter class.
internal constructor() : ListAdapter<UserEntity, UserRVAdapter.ViewHolder>(DIFF_CALLBACK) {
    // creating a variable for on item click listener.
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // below line is use to inflate our layout
        // file for each item of our recycler view.
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_rv_item, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // below line of code is use to set data to
        // each item of our recycler view.
        val model = getuserAt(position)
        holder.name.text = model!!.name
        holder.surname.text = model.surname
        holder.dateOfBirth.text = model.dateOfBirth
        holder.heartRate.text = model.heartRate
        holder.so2.text = model.so2
        holder.bodyTemperature.text = model.bodyTemperature
    }

    // creating a method to get user modal for a specific position.
    fun getuserAt(position: Int): UserEntity? {
        return getItem(position)
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        // view holder class to create a variable for each view.
        var name: TextView
        var surname: TextView
        var dateOfBirth: TextView
        var heartRate: TextView
        var so2: TextView
        var bodyTemperature: TextView

        init {
            // initializing each view of our recycler view.
            name = itemView.findViewById(R.id.idUserName)
            surname = itemView.findViewById(R.id.idUserSurname)
            dateOfBirth = itemView.findViewById(R.id.idUserDateOfBirth)
            heartRate = itemView.findViewById(R.id.idUserHeartRate)
            so2 = itemView.findViewById(R.id.idUserSo2)
            bodyTemperature = itemView.findViewById(R.id.idUserBodyTemperature)

            // adding on click listener for each item of recycler view.
            itemView.setOnClickListener { // inside on click listener we are passing
                // position to our item of recycler view.
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(getItem(position))
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(model: UserEntity?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    companion object {
        // creating a call back for item of recycler view.
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<UserEntity> =
            object : DiffUtil.ItemCallback<UserEntity>() {
                override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
                    // below line is to check the user name, description and user duration.
                    return oldItem.name == newItem.name && oldItem.surname == newItem.surname && oldItem.dateOfBirth == newItem.dateOfBirth && oldItem.heartRate == newItem.heartRate && oldItem.so2 == newItem.so2 && oldItem.bodyTemperature == newItem.bodyTemperature
                }
            }
    }
}