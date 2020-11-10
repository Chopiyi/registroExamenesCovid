package cl.inacap.registroexamenescovid.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class PacienteDBOpenHelper extends SQLiteOpenHelper {

    private String sqlCreate = "CREATE Table paciente("+ "rut PRIMARY KEY NOT NULL," +
            "nombre TEXT," +
            "apellido TEXT," +
            "fecha DATE," +
            "area_trabajo TEXT," +
            "sintomas BOOL," +
            "temperatura FLOAT," +
            "tos BOOL," +
            "presion INT)";

    public PacienteDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(sqlCreate);
            sqLiteDatabase.execSQL(
                    "INSERT INTO paciente(rut, nombre, apellido, fecha, area_trabajo, sintomas, presion, temperatura, tos)" +
                            "VALUES ('18585098-1'" +
                            ", 'Alexis'" +
                            ", 'Gutierrez'" +
                            ", '23/05/1994'" +
                            ", 'Otro'" +
                            ", 'false'" +
                            ", 34" +
                            ", 34.5" +
                            ", 0)");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pacientes");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
