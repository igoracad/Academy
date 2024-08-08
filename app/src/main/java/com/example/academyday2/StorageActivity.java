package com.example.academyday2;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.CallLog;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.example.academyday2.database.Item;
import com.example.academyday2.database.ItemDao;
import com.example.academyday2.database.ItemRoomDatabase;
import com.example.academyday2.databinding.ActivityStorageBinding;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

public class StorageActivity extends AppCompatActivity {

    private final String fileName = "filenamecognizant";

    private static final String NAME = "name";

    private static final String PWD = "pwd";

    ActivityStorageBinding binding;


    // DB ROOM code

    ItemDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // code room db
        ItemRoomDatabase database = ItemRoomDatabase.getDatabase(this);
        dao = database.itemDao();

        binding = ActivityStorageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }

    @Override
    protected void onStart() {
        super.onStart();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //db room code
                //globalScope is from couritines, java doesn't have natively
                //need to install dependecies or use ExecutorService
                ExecutorService executor = Executors.newSingleThreadExecutor();

                executor.submit(() -> {
                    Item item = new Item(21, "fruits", 11.11, 11);
                    dao.insert(item);
                });

                executor.shutdown();
            }
        });


        binding.btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                ---------------- Kotlin way of doing it ------------------
                GlobalScope.launch(Dispatchers.Main) {
                    var item = dao.getItem(21).first()
                    binding.tvDb.setText(item.toString())
                }*/

                //---------------- JAVA way of doing it ------------------
                binding.btnGet.setOnClickListener(i -> {
                    Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(() -> {
                        final Item item = dao.getItem(21);
                        runOnUiThread(() -> binding.txtData.setText(item.toString()));
                    });
                });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        storeData();
    }

    private void storeData() {
        // create a file
        SharedPreferences preferences = getSharedPreferences(fileName, MODE_PRIVATE);
        // open the file
        SharedPreferences.Editor editor = preferences.edit();
        // write to the file
        editor.putString(NAME, binding.etName.getText().toString());
        editor.putString(PWD, binding.etPassword.getText().toString());
        // save the file
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreData();

        Cursor cursor = getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC"
        );
        // Uri uriSms = Uri.parse("content://sms/inbox");
        //Cursor dataCursor = getContentResolver().query(uriSms, null, null, null, null);
        String colName = CallLog.Calls.NUMBER;

        String[] fromColNames = {colName};
        int[] toTexviewIds = {android.R.id.text1};

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                cursor, fromColNames, toTexviewIds);
        binding.listView.setAdapter(cursorAdapter);
    }

    private void restoreData() {
        //if file exists it'll open that file or create it
        SharedPreferences preferences = getSharedPreferences(fileName, MODE_PRIVATE);
        String name = preferences.getString(NAME, "");
        String password = preferences.getString(PWD, "");
        binding.etName.setText(name);
        binding.etPassword.setText(password);
    }
}