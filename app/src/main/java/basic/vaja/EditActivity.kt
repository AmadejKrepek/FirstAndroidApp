package basic.vaja

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EditActivity : AppCompatActivity() {
    private var usersRV: RecyclerView? = null
    private var viewModal: ViewModal? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        // initializing our variable for our recycler view and fab.
        usersRV = findViewById(R.id.idRVUsers)
        /*        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        // adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new user
                // and passing a constant value in it.
                Intent intent = new Intent(EditActivity.this, AddUserActivity.class);
                startActivityForResult(intent, ADD_USER_REQUEST);
            }
        });*/

        // setting layout manager to our adapter class.
        usersRV?.layoutManager = LinearLayoutManager(this)
        usersRV?.setHasFixedSize(true)

        // initializing adapter for recycler view.
        val adapter = UserRVAdapter()

        // setting adapter class for recycler view.
        usersRV?.adapter = adapter

        // passing a data from view modal.
        viewModal = ViewModelProviders.of(this)[ViewModal::class.java]

        // below line is use to get all the users from view modal.
        viewModal!!.allusers?.observe(this) { models -> // when the data is changed in our models we are
            // adding that list to our adapter class.
            adapter.submitList(models)
        }
        // below method is use to add swipe to delete method for item of recycler view.
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                viewModal!!.delete(adapter.getuserAt(viewHolder.adapterPosition))
                Toast.makeText(this@EditActivity, "user deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(usersRV)// below line is use to attach this to recycler view.
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener { model -> // after clicking on item of recycler view
            // we are opening a new activity and passing
            // a data to our activity.
            val intent = Intent(this@EditActivity, AddUserActivity::class.java)
            intent.putExtra(AddUserActivity.EXTRA_ID, model.id)
            intent.putExtra(AddUserActivity.EXTRA_NAME, model.name)
            intent.putExtra(AddUserActivity.EXTRA_SURNAME, model.surname)
            intent.putExtra(AddUserActivity.EXTRA_DATE_OF_BIRTH, model.dateOfBirth)
            intent.putExtra(AddUserActivity.EXTRA_HEART_RATE, model.heartRate)
            intent.putExtra(AddUserActivity.EXTRA_SO2, model.so2)
            intent.putExtra(AddUserActivity.EXTRA_TEMPERATURE, model.bodyTemperature)

            // below line is to start a new activity and
            // adding a edit user constant.
            startActivityForResult(intent, EDIT_USER_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_USER_REQUEST && resultCode == RESULT_OK) {
            val name = data!!.getStringExtra(AddUserActivity.EXTRA_NAME)
            val surname = data.getStringExtra(AddUserActivity.EXTRA_SURNAME)
            val dateOfBirth = data.getStringExtra(AddUserActivity.EXTRA_DATE_OF_BIRTH)
            val heartRate = data.getStringExtra(AddUserActivity.EXTRA_HEART_RATE)
            val so2 = data.getStringExtra(AddUserActivity.EXTRA_SO2)
            val bodyTemperature = data.getStringExtra(AddUserActivity.EXTRA_TEMPERATURE)
            val model = UserEntity(name, surname, dateOfBirth, heartRate, so2, bodyTemperature)
            viewModal!!.insert(model)
            Toast.makeText(this, "User saved", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_USER_REQUEST && resultCode == RESULT_OK) {
            val id = data!!.getIntExtra(AddUserActivity.EXTRA_ID, -1)
            if (id == -1) {
                Toast.makeText(this, "user can't be updated", Toast.LENGTH_SHORT).show()
                return
            }
            val name = data.getStringExtra(AddUserActivity.EXTRA_NAME)
            val userDesc = data.getStringExtra(AddUserActivity.EXTRA_SURNAME)
            val dateOfBirth = data.getStringExtra(AddUserActivity.EXTRA_DATE_OF_BIRTH)
            val heartRate = data.getStringExtra(AddUserActivity.EXTRA_HEART_RATE)
            val so2 = data.getStringExtra(AddUserActivity.EXTRA_SO2)
            val bodyTemperature = data.getStringExtra(AddUserActivity.EXTRA_TEMPERATURE)
            val model = UserEntity(name, userDesc, dateOfBirth, heartRate, so2, bodyTemperature)
            model.id = id
            viewModal!!.update(model)
            Toast.makeText(this, "User updated", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "User not saved", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val ADD_USER_REQUEST = 1
        private const val EDIT_USER_REQUEST = 2
    }
}