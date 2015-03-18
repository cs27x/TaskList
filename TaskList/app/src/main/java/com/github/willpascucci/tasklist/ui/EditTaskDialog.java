package com.github.willpascucci.tasklist.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.willpascucci.tasklist.R;

/**
 * Created by Nathan Walker on 2/23/15.
 */
public class EditTaskDialog extends DialogFragment {

    private EditText e;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_task, container);



        return view;
    }
}
