package com.sabya.newsapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.sabya.newsapp.R;

/**
 * Created by Sabyasachi
 */
public class EditProfileFragment extends Fragment {

    public EditProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.layout_fragment_edit_profile, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
