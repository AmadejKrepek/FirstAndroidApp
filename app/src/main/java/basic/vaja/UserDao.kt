package basic.vaja

import androidx.lifecycle.LiveData
import androidx.room.*

// Adding annotation
// to our Dao class
@Dao
interface UserDao {
    @get:Query("SELECT * FROM user_data ORDER BY name ASC")
    val users: List<UserEntity?>?

    // below method is use to
    // add data to database.
    @Insert
    fun insert(model: UserEntity?): Long

    // below method is use to update
    // the data in our database.
    @Update
    fun update(model: UserEntity?)

    // below line is use to delete a
    // specific user in our database.
    @Delete
    fun delete(model: UserEntity?)

    // on below line we are making query to
    // delete all users from our database.
    @Query("DELETE FROM user_data")
    fun deleteAllUsers()

    // below line is to read all the users from our database.
    // in this we are ordering our users in ascending order
    // with our user name.
    @get:Query("SELECT * FROM user_data ORDER BY name ASC")
    val allUsers: LiveData<List<UserEntity?>?>?
}