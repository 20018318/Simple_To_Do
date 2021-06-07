package sg.edu.rp.c346.id20018318.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText task;
    Button add;
    Button delete;
    Button clear;
    ListView toDoList;
    Spinner AddRemove;
    ArrayList<String> alTasks;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task = findViewById(R.id.editTextTask);
        add = findViewById(R.id.buttonAddItem);
        delete = findViewById(R.id.buttonDeleteItem);
        clear = findViewById(R.id.buttonClearList);
        toDoList = findViewById(R.id.listViewTask);
        AddRemove = findViewById(R.id.spinner);

        alTasks = new ArrayList<String>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        toDoList.setAdapter(adapter);

        AddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        task.setHint(R.string.hint1);
                        add.setEnabled(true);
                        delete.setEnabled(false);
                        break;
                    case 1:
                        task.setHint(R.string.hint2);
                        delete.setEnabled(true);
                        add.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = task.getText().toString();
                if (TextUtils.isEmpty(input)) {
                    task.setError(getString(R.string.errorMsg));
                } else {
                    String toDo = task.getText().toString();
                    alTasks.add(toDo);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = task.getText().toString();
                if (TextUtils.isEmpty(input)) {
                    task.setError(getString(R.string.errorMsg));
                } else {
                    if (!alTasks.isEmpty()) {
                        int pos = Integer.parseInt(task.getText().toString());
                        if (pos >= alTasks.size()) {
                            Toast.makeText(MainActivity.this, R.string.toast2, Toast.LENGTH_SHORT).show();
                        } else {
                            alTasks.remove(pos);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, R.string.toast3, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, R.string.toast1, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
}