package basic.vaja;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class EditActivity extends AppCompatActivity {

    private RecyclerView usersRV;
    private static final int ADD_USER_REQUEST = 1;
    private static final int EDIT_USER_REQUEST = 2;
    private ViewModal viewModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // initializing our variable for our recycler view and fab.
        usersRV = findViewById(R.id.idRVUsers);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        // adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new user
                // and passing a constant value in it.
                Intent intent = new Intent(EditActivity.this, AddUserActivity.class);
                startActivityForResult(intent, ADD_USER_REQUEST);
            }
        });

        // setting layout manager to our adapter class.
        usersRV.setLayoutManager(new LinearLayoutManager(this));
        usersRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final UserRVAdapter adapter = new UserRVAdapter();

        // setting adapter class for recycler view.
        usersRV.setAdapter(adapter);

        // passing a data from view modal.
        viewModal = ViewModelProviders.of(this).get(ViewModal.class);

        // below line is use to get all the users from view modal.
        viewModal.getAllusers().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(List<UserEntity> models) {
                // when the data is changed in our models we are
                // adding that list to our adapter class.
                adapter.submitList(models);
            }
        });
        // below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                viewModal.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(EditActivity.this, "user deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView(usersRV);
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new UserRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserEntity model) {
                // after clicking on item of recycler view
                // we are opening a new activity and passing
                // a data to our activity.
                Intent intent = new Intent(EditActivity.this, AddUserActivity.class);
                intent.putExtra(AddUserActivity.EXTRA_ID, model.getId());
                intent.putExtra(AddUserActivity.EXTRA_NAME, model.getName());
                intent.putExtra(AddUserActivity.EXTRA_SURNAME, model.getSurname());
                intent.putExtra(AddUserActivity.EXTRA_DATE_OF_BIRTH, model.getDateOfBirth());

                // below line is to start a new activity and
                // adding a edit user constant.
                startActivityForResult(intent, EDIT_USER_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_USER_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddUserActivity.EXTRA_NAME);
            String surname = data.getStringExtra(AddUserActivity.EXTRA_SURNAME);
            String dateOfBirth = data.getStringExtra(AddUserActivity.EXTRA_DATE_OF_BIRTH);
            UserEntity model = new UserEntity(name, surname, dateOfBirth);
            viewModal.insert(model);
            Toast.makeText(this, "user saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_USER_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddUserActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "user can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(AddUserActivity.EXTRA_NAME);
            String userDesc = data.getStringExtra(AddUserActivity.EXTRA_SURNAME);
            String dateOfBirth = data.getStringExtra(AddUserActivity.EXTRA_DATE_OF_BIRTH);
            UserEntity model = new UserEntity(name, userDesc, dateOfBirth);
            model.setId(id);
            viewModal.update(model);
            Toast.makeText(this, "Course updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Course not saved", Toast.LENGTH_SHORT).show();
        }
    }
}