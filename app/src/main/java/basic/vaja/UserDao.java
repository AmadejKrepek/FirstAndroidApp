package basic.vaja;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// Adding annotation
// to our Dao class
@androidx.room.Dao
public interface UserDao {

    // below method is use to
    // add data to database.
    @Insert
    void insert(UserEntity model);

    // below method is use to update
    // the data in our database.
    @Update
    void update(UserEntity model);

    // below line is use to delete a
    // specific user in our database.
    @Delete
    void delete(UserEntity model);

    // on below line we are making query to
    // delete all users from our database.
    @Query("DELETE FROM user_data")
    void deleteAllUsers();

    // below line is to read all the users from our database.
    // in this we are ordering our users in ascending order
    // with our user name.
    @Query("SELECT * FROM user_data ORDER BY name ASC")
    LiveData<List<UserEntity>> getAllUsers();
}
