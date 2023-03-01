package basic.vaja

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ViewModal(application: Application) : AndroidViewModel(application) {
    // creating a new variable for user repository.
    private val repository: UserRepository

    // below method is to get all the users in our list.
    // below line is to create a variable for live
    // data where all the users are present.
    val allusers: LiveData<List<UserEntity?>?>?

    // constructor for our view modal.
    init {
        repository = UserRepository(application)
        allusers = repository.allusers
    }

    val users: List<UserEntity?>?
        get() = repository.users

    // below method is use to insert the data to our repository.
    fun insert(model: UserEntity?) {
        repository.insert(model)
    }

    // below line is to update data in our repository.
    fun update(model: UserEntity?) {
        repository.update(model)
    }

    // below line is to delete the data in our repository.
    fun delete(model: UserEntity?) {
        repository.delete(model)
    }

    // below method is to delete all the users in our list.
    fun deleteAllusers() {
        repository.deleteAllusers()
    }
}