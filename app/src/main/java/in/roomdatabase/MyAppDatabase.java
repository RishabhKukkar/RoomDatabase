package in.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import in.roomdatabase.model.Student;
import in.roomdatabase.utils.DatabaseQuery;

@Database(entities = {Student.class}, version = 2)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract DatabaseQuery myData();
}
