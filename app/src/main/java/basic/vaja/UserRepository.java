package basic.vaja;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {

    // below line is the create a variable
    // for dao and list for all users.
    private UserDao dao;
    private LiveData<List<UserEntity>> allusers;

    // creating a constructor for our variables
    // and passing the variables to it.
    public UserRepository(Application application) {
        UserDatabase database = UserDatabase.getInstance(application);
        dao = database.Dao();
        allusers = dao.getAllUsers();
    }

    public List<UserEntity> getUsers() {
        try {
            return new GetUsersAsyncTask().execute().get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    // creating a method to insert the data to our database.
    public void insert(UserEntity model) {
        new InsertUserAsyncTask(dao).execute(model);
    }

    // creating a method to update data in database.
    public void update(UserEntity model) {
        new UpdateUserAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(UserEntity model) {
        new DeleteUserAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the users.
    public void deleteAllusers() {
        new DeleteAllUsersAsyncTask(dao).execute();
    }

    // below method is to read all the users.
    public LiveData<List<UserEntity>> getAllusers() {
        return allusers;
    }

    private class GetUsersAsyncTask extends AsyncTask<Void, Void, List<UserEntity>> {
        @Override
        protected List<UserEntity> doInBackground(Void... voids) {
            return dao.getUsers();
        }
    }

    // we are creating a async task method to insert new user.
    private static class InsertUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao dao;

        private InsertUserAsyncTask(UserDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(UserEntity... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our user.
    private static class UpdateUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao dao;

        private UpdateUserAsyncTask(UserDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(UserEntity... models) {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete user.
    private static class DeleteUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao dao;

        private DeleteUserAsyncTask(UserDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(UserEntity... models) {
            // below line is use to delete
            // our user modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all users.
    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao dao;
        private DeleteAllUsersAsyncTask(UserDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all users.
            dao.deleteAllUsers();
            return null;
        }
    }
}
