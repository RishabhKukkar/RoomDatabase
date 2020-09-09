package in.roomdatabase.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static in.roomdatabase.utils.ConstantUtils.ADD_FRAGMENT;
import static in.roomdatabase.utils.ConstantUtils.REPLACE_FRAGMENT;

public class BaseApp extends AppCompatActivity {

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