package in.roomdatabase.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "studentName")
    private String studentName;

    @ColumnInfo(name = "studentStandard")
    private String studentStandard;

    @ColumnInfo(name = "studentDivision")
    private String studentDivision;


    public Student(String studentName, String studentStandard, String studentDivision) {
        this.studentName = studentName;
        this.studentStandard = studentStandard;
        this.studentDivision = studentDivision;
    }

    protected Student(Parcel in) {
        id = in.readInt();
        studentName = in.readString();
        studentStandard = in.readString();
        studentDivision = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentStandard() {
        return studentStandard;
    }

    public void setStudentStandard(String studentStandard) {
        this.studentStandard = studentStandard;
    }

    public String getStudentDivision() {
        return studentDivision;
    }

    public void setStudentDivision(String studentDivision) {
        this.studentDivision = studentDivision;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(studentName);
        parcel.writeString(studentStandard);
        parcel.writeString(studentDivision);
    }
}
