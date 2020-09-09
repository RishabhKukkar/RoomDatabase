package in.roomdatabase.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.roomdatabase.MainActivity;
import in.roomdatabase.R;
import in.roomdatabase.model.Student;

import static in.roomdatabase.utils.ConstantUtils.PD_STUDENT_INFORMATION;

public class UpdateStudentFragment extends BaseFragment {
    //View
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


    public UpdateStudentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            student = getArguments().getParcelable(PD_STUDENT_INFORMATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_student, container, false);
        ((MainActivity) getActivity()).makeViewAndGone(View.GONE);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        mbtAddStudent.setText(getContext().getString(R.string.update_student));

        etStudentName.setText(student.getStudentName());
        etStudentDivision.setText(student.getStudentDivision());
        etStudentStandard.setText(student.getStudentStandard());

        mbtAddStudent.setOnClickListener(view1 -> {
            if (validateDetails()) {
                String studentName = etStudentName.getText().toString().trim();
                String studentStandard = etStudentStandard.getText().toString().trim();
                String studentDivision = etStudentDivision.getText().toString().trim();
                int studentId = student.getId();
                student = new Student(
                        studentName,
                        studentStandard,
                        studentDivision
                );
                student.setId(studentId);
                MainActivity.myAppDatabase.myData().updateStudent(student);
                Toast.makeText(getContext(), "Record Added Sucessfully", Toast.LENGTH_SHORT).show();
            }
        });
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