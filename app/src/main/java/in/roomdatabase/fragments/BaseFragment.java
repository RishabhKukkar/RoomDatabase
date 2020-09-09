/******************************************************************************
 * Copyright (c) 2020. All rights reserved. Cmile                             *
 * Created by : Rishabh N. Kukkar on                                          *
 * Last modified :  17/6/20 2:07 PM                                           *
 ******************************************************************************/

package in.roomdatabase.fragments;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static in.roomdatabase.utils.ConstantUtils.ADD_FRAGMENT;
import static in.roomdatabase.utils.ConstantUtils.REPLACE_FRAGMENT;

public class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void changeFragment(FragmentManager fragmentManager, int container, Fragment fragment,
                               int action, boolean addToBackStack, String TAG) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (action == ADD_FRAGMENT)
            fragmentTransaction.add(container, fragment);
        else if (action == REPLACE_FRAGMENT) {
            fragmentTransaction.replace(container, fragment);
        } else
            return;
        if (addToBackStack)
            fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }
}