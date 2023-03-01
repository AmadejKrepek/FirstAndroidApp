package basic.vaja

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

// adding annotation for our database entities and db version.
@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    // below line is to create
    // abstract variable for dao.
    abstract fun Dao(): UserDao

    // we are creating an async task class to perform task in background.
    private class PopulateDbAsyncTask internal constructor(instance: UserDatabase?) :
        AsyncTask<Void?, Void?, Void?>() {
        init {
            val dao = instance!!.Dao()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            return null
        }
    }

    companion object {
        // below line is to create instance
        // for our database class.
        private var instance: UserDatabase? = null

        // on below line we are getting instance for our database.
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): UserDatabase? {
            // below line is to check if
            // the instance is null or not.
            if (instance == null) {
                // if the instance is null we
                // are creating a new instance
                instance =  // for creating a instance for our database
                        // we are creating a database builder and passing
                        // our database class with our database name.
                    Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java, "user_database"
                    ) // below line is use to add fall back to
                        // destructive migration to our database.
                        .fallbackToDestructiveMigration() // below line is to add callback
                        // to our database.
                        .addCallback(roomCallback) // below line is to
                        // build our database.
                        .build()
            }
            // after creating an instance
            // we are returning our instance
            return instance
        }

        // below line is to create a callback for our room database.
        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // this method is called when database is created
                // and below line is to populate our data.
                PopulateDbAsyncTask(instance).execute()
            }
        }
    }
}