package com.github.willpascucci.tasklist.ui;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.activeandroid.ActiveAndroid;
import com.github.willpascucci.tasklist.R;
import com.github.willpascucci.tasklist.global.BusSingleton;
import com.github.willpascucci.tasklist.model.Task;
import com.github.willpascucci.tasklist.model.TaskList;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Calendar;

/**
 * Created by Nathan Walker on 2/23/15.
 */
public class EditTaskDialog extends DialogFragment {
    public static final String KEY_ID = "KEY_ID";

    private EditText editTask;
    private Task task;


    private NumberPicker numberPicker;

    public static DialogFragment newInstance(long id) {
        EditTaskDialog dialog = new EditTaskDialog();

        Bundle args = new Bundle();
        args.putLong(KEY_ID, id);
        dialog.setArguments(args);

        return dialog;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_task, container);

        final long id = getArguments().getLong(KEY_ID);
        task = Task.get(id);

        setEditTextTaskField(view, R.id.edit_task, task, "text");
        setEditTextTaskField(view, R.id.edit_desc, task, "description");

        Button dateButton = (Button) view.findViewById(R.id.button_date);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Date currentDate = new Date();
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        task.setDeadline(new Date(year, monthOfYear, dayOfMonth));
                    }
                }, year, month, day);
                dateDialog.show();
            }


        });

        numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setValue(task.getImportance());

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                task.setImportance(newVal);
            }
        });

        Button saveButton = (Button) view.findViewById(R.id.button_save);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.save();
                BusSingleton.get().post(new TaskListFragment.UpdateEvent());
                dismiss();
            }
        });

        Button cancelButton = (Button) view.findViewById(R.id.button_cancel);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    public static void setEditTextTaskField(View view, int viewId, final Task task, final String fieldStr) {
        EditText editText = (EditText) view.findViewById(viewId);
        final Field field;
        try {
            field = Task.class.getField(fieldStr);
            String str = (String) field.get(task);
            if (str != null)
                editText.setText(str);
        } catch (Exception e) {
            Log.d("TAG", e.toString());
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
