package basic.vaja;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {

    // creating a new variable for user repository.
    private UserRepository repository;

    // below line is to create a variable for live
    // data where all the users are present.
    private final LiveData<List<UserEntity>> allusers;

    // constructor for our view modal.
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allusers = repository.getAllusers();
    }

    public List<UserEntity> getUsers() {
        return repository.getUsers();
    }

    // below method is use to insert the data to our repository.
    public void insert(UserEntity model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(UserEntity model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(UserEntity model) {
        repository.delete(model);
    }

    // below method is to delete all the users in our list.
    public void deleteAllusers() {
        repository.deleteAllusers();
    }

    // below method is to get all the users in our list.
    public LiveData<List<UserEntity>> getAllusers() {
        return allusers;
    }
}

