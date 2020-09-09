package in.roomdatabase.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.roomdatabase.MainActivity;
import in.roomdatabase.R;
import in.roomdatabase.adapter.StudentListAdapter;
import in.roomdatabase.model.Student;

import static in.roomdatabase.utils.ConstantUtils.PD_STUDENT_INFORMATION;
import static in.roomdatabase.utils.ConstantUtils.REPLACE_FRAGMENT;


public class HomeFragment extends BaseFragment implements StudentListAdapter.StudentClickListener {
    //Views
    private View view;

    @BindView(R.id.rvHome)
    RecyclerView rvHome;

    //ArrayList
    private ArrayList studentList;

    //Objects
    private StudentListAdapter studentListAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).makeViewAndGone(View.VISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initModel();
        setUpViews();
    }

    private void setUpViews() {
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        studentListAdapter = new StudentListAdapter(
                getContext(),
                studentList,
                this
        );
        rvHome.setAdapter(studentListAdapter);
    }

    private void initModel() {
        studentList = new ArrayList<>();
        studentList = (ArrayList) MainActivity.myAppDatabase.myData().getStudents();
    }

    @Override
    public void onStudentClicked(Student student) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PD_STUDENT_INFORMATION, student);
        UpdateStudentFragment updateStudentFragment = new UpdateStudentFragment();
        updateStudentFragment.setArguments(bundle);
        changeFragment(getFragmentManager(),
                R.id.fmMainActivity,
                updateStudentFragment,
                REPLACE_FRAGMENT,
                true,
                null);
    }

    @Override
    public void onDeleteStudentClicked(Student student) {
        MainActivity.myAppDatabase.myData().deleteStudent(student);
        studentList = (ArrayList) MainActivity.myAppDatabase.myData().getStudents();
        setUpViews();
    }
}