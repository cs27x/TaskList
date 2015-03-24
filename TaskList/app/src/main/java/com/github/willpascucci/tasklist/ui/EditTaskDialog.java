package com.github.willpascucci.tasklist.ui;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.activeandroid.ActiveAndroid;
import com.github.willpascucci.tasklist.R;
import com.github.willpascucci.tasklist.model.Task;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by Nathan Walker on 2/23/15.
 */
public class EditTaskDialog extends DialogFragment {
    public final String KEY_ID = "KEY_ID";

    private EditText editTask;
    private Task task;


    private NumberPicker numberPicker;

    public DialogFragment newInstance(int id) {
        EditTaskDialog dialog = new EditTaskDialog();

        Bundle args = new Bundle();
        args.putInt(KEY_ID, id);
        dialog.setArguments(args);

        return dialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_task, container);

        final int id = getArguments().getInt(KEY_ID);
        task = Task.get(id);

        setEditTextTaskField(view, R.id.edit_task, task, "text");
        setEditTextTaskField(view, R.id.edit_desc, task, "description");

        Button dateButton = (Button) view.findViewById(R.id.button_date);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDate = new Date();
                DatePickerDialog dateDialog = new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        task.setDeadline(new Date(year,monthOfYear,dayOfMonth));
                    }
                },currentDate.getYear(),currentDate.getMonth(),currentDate.getDay());
                
            }
        });

        numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                task.importance = newVal;
            }
        });
        return view;
    }

    public static void setEditTextTaskField(View view, int viewId, final Task task, final String fieldStr) {
        EditText editText = (EditText) view.findViewById(viewId);
        final Field field;
        try {
            field = Task.class.getField(fieldStr);
            editText.setText((String)field.get(task));
        } catch (Exception e) {
            throw new AssertionError();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    field.set(task, s.toString());
                } catch (Exception e) {
                    throw new AssertionError();
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }
}
