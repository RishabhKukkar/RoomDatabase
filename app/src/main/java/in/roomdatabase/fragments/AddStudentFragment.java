package in.roomdatabase.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.roomdatabase.MainActivity;
import in.roomdatabase.R;
import in.roomdatabase.model.Student;

public class AddStudentFragment extends BaseFragment {
    //Views
    private View view;

    @BindView(R.id.etStudentName)
    TextInputEditText etStudentName;

    @BindView(R.id.etStudentStandard)
    TextInputEditText etStudentStandard;

    @BindView(R.id.etStudentDivision)
    TextInputEditText etStudentDivision;

    @BindView(R.id.mbtAddStudent)
    MaterialButton mbtAddStudent;


    //Objects
    private Student student;

    public AddStudentFragment() {
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
        view = inflater.inflate(R.layout.fragment_add_student, container, false);
        ((MainActivity) getActivity()).makeViewAndGone(View.GONE);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupListener();
    }

    private void setupListener() {
        mbtAddStudent.setOnClickListener(view1 -> {
            if (validateDetails()) {
                String studentName = etStudentName.getText().toString().trim();
                String studentStandard = etStudentStandard.getText().toString().trim();
                String studentDivision = etStudentDivision.getText().toString().trim();
                student = new Student(
                        studentName,
                        studentStandard,
                        studentDivision
                );
                MainActivity.myAppDatabase.myData().addStudent(student);
                Toast.makeText(getContext(), "Record Added Sucessfully", Toast.LENGTH_SHORT).show();
                clearViews();
            }
        });
    }

    private void clearViews() {
        etStudentStandard.setText(null);
        etStudentDivision.setText(null);
        etStudentName.setText(null);
    }

    private boolean validateDetails() {
        if (etStudentName.getText().toString().equals("")) {
            etStudentName.setError(getContext().getString(R.string.please_enter_student_name));
            return false;
        }
        if (etStudentStandard.getText().toString().equals("")) {
            etStudentStandard.setError(getContext().getString(R.string.please_enter_student_standard));
            return false;
        }
        if (etStudentDivision.getText().toString().equals("")) {
            etStudentDivision.setError(getContext().getString(R.string.please_enter_student_division));
            return false;
        }
        return true;

    }
}