package in.roomdatabase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.roomdatabase.R;
import in.roomdatabase.model.Student;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Student> studentArrayList;
    StudentClickListener studentClickListener;

    public StudentListAdapter(Context context, ArrayList<Student> studentArrayList, StudentClickListener studentClickListener) {
        this.context = context;
        this.studentArrayList = studentArrayList;
        this.studentClickListener = studentClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_student_details, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(studentArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvItemStudentName)
        MaterialTextView tvItemStudentName;

        @BindView(R.id.tvItemStudentStandard)
        MaterialTextView tvItemStudentStandard;

        @BindView(R.id.tvItemStudentDivision)
        MaterialTextView tvItemStudentDivision;

        @BindView(R.id.ivItemStudentDelete)
        ImageView ivItemStudentDelete;

        @BindView(R.id.btnItemViewStudent)
        MaterialButton btnItemViewStudent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        void bindData(Student student) {
            tvItemStudentName.setText(student.getStudentName());
            tvItemStudentStandard.setText(student.getStudentStandard());
            tvItemStudentDivision.setText(student.getStudentDivision());

            btnItemViewStudent.setOnClickListener(view -> {
                studentClickListener.onStudentClicked(student);
            });

            ivItemStudentDelete.setOnClickListener(view -> {
                studentClickListener.onDeleteStudentClicked(student);
            });
        }
    }

    public interface StudentClickListener {
        void onStudentClicked(Student student);

        void onDeleteStudentClicked(Student student);
    }

}
