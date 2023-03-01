package basic.vaja

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import basic.vaja.UserDatabase.Companion.getInstance
import java.util.concurrent.ExecutionException

class UserRepository(application: Application?) {
    // below line is the create a variable
    // for dao and list for all users.
    private val dao: UserDao

    // below method is to read all the users.
    val allusers: LiveData<List<UserEntity?>?>?

    // creating a constructor for our variables
    // and passing the variables to it.
    init {
        val database = getInstance(application!!)
        dao = database!!.Dao()
        allusers = dao.allUsers
    }

    val users: List<UserEntity?>?
        get() = try {
            GetUsersAsyncTask().execute().get()
        } catch (e: ExecutionException) {
            throw RuntimeException(e)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }

    // creating a method to insert the data to our database.
    fun insert(model: UserEntity?) {
        InsertUserAsyncTask(dao).execute(model)
    }

    // creating a method to update data in database.
    fun update(model: UserEntity?) {
        UpdateUserAsyncTask(dao).execute(model)
    }

    // creating a method to delete the data in our database.
    fun delete(model: UserEntity?) {
        DeleteUserAsyncTask(dao).execute(model)
    }

    // below is the method to delete all the users.
    fun deleteAllusers() {
        DeleteAllUsersAsyncTask(dao).execute()
    }

    private inner class GetUsersAsyncTask : AsyncTask<Void?, Void?, List<UserEntity?>?>() {
        override fun doInBackground(vararg p0: Void?): List<UserEntity?>? {
            return dao.users
        }
    }

    // we are creating a async task method to insert new user.
    private class InsertUserAsyncTask(private val dao: UserDao) :
        AsyncTask<UserEntity?, Void?, Void?>() {
        override fun doInBackground(vararg model: UserEntity?): Void? {
            // below line is use to insert our modal in dao.
            dao.insert(model[0])
            return null
        }
    }

    // we are creating a async task method to update our user.
    private class UpdateUserAsyncTask(private val dao: UserDao) :
        AsyncTask<UserEntity?, Void?, Void?>() {
        override fun doInBackground(vararg models: UserEntity?): Void? {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0])
            return null
        }
    }

    // we are creating a async task method to delete user.
    private class DeleteUserAsyncTask(private val dao: UserDao) :
        AsyncTask<UserEntity?, Void?, Void?>() {
        override fun doInBackground(vararg models: UserEntity?): Void? {
            // below line is use to delete
            // our user modal in dao.
            dao.delete(models[0])
            return null
        }
    }

    // we are creating a async task method to delete all users.
    private class DeleteAllUsersAsyncTask(private val dao: UserDao) :
        AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            // on below line calling method
            // to delete all users.
            dao.deleteAllUsers()
            return null
        }
    }
}