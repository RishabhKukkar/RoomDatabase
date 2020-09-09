package in.roomdatabase.utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import in.roomdatabase.model.Student;

@Dao
public interface DatabaseQuery {
    @Insert
    void addStudent(Student student);

    @Query("select * from student")
    List<Student> getStudents();

    @Delete
    void deleteStudent(Student student);

    @Update
    void updateStudent(Student student);

}