package in.roomdatabase;

import android.os.Bundle;

import androidx.room.Room;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.roomdatabase.fragments.AddStudentFragment;
import in.roomdatabase.fragments.HomeFragment;
import in.roomdatabase.utils.BaseApp;

import static in.roomdatabase.utils.ConstantUtils.REPLACE_FRAGMENT;

public class MainActivity extends BaseApp {

    @BindView(R.id.efbAddStudent)
    ExtendedFloatingActionButton efbAddStudent;


    public static MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        myAppDatabase = Room.databaseBuilder(
                getApplicationContext(),
                MyAppDatabase.class,
                "student")
                .allowMainThreadQueries().build();

        changeFragment(getSupportFragmentManager(),
                R.id.fmMainActivity,
                new HomeFragment(),
                REPLACE_FRAGMENT,
                false,
                null);


        efbAddStudent.setOnClickListener(view -> {
            changeFragment(getSupportFragmentManager(),
                    R.id.fmMainActivity,
                    new AddStudentFragment(),
                    REPLACE_FRAGMENT,
                    true,
                    null);
        });
    }

    public void makeViewAndGone(int view) {
        efbAddStudent.setVisibility(view);
    }


}