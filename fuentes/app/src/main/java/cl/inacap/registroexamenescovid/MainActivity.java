package cl.inacap.registroexamenescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText input_rut;
    private EditText input_contrasenia;
    private Button boton_login;

    //Validación de rut y digito verificador.

    public static Boolean validaRut ( String rut ) {
        Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(rut);
        if ( matcher.matches() == false ) return false;
        String[] stringRut = rut.split("-");
        return stringRut[1].toLowerCase().equals(MainActivity.dv(stringRut[0]));
    }

    public static String dv ( String rut ) {
        Integer M=0,S=1,T=Integer.parseInt(rut);
        for (;T!=0;T=(int) Math.floor(T/=10))
            S=(S+T%10*(9-M++%6))%11;
        return ( S > 0 ) ? String.valueOf(S-1) : "k";
}

    public String digitos(String rut){
        String digitosContrasenia;
        if(rut.length() == 9){
            digitosContrasenia = rut.substring(3 ,7);
        } else {
            digitosContrasenia = rut.substring(4, 8).trim();
        }
        return digitosContrasenia;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.input_rut = findViewById(R.id.input_login_rut);
        this.input_contrasenia = findViewById(R.id.input_login_contrasenia);
        this.boton_login = findViewById(R.id.boton_login);
        this.boton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rut = input_rut.getText().toString().trim();
                String contrasenia = input_contrasenia.getText().toString().trim();
                if(rut.isEmpty() || validaRut(rut) == false){
                    Toast.makeText(MainActivity.this, "Ingrese un rut válido por favor", Toast.LENGTH_SHORT).show();
                } else if(contrasenia.isEmpty() || !contrasenia.equals(digitos(rut))) {
                    Toast.makeText(MainActivity.this, "La contraseña debe estar compuesta por los últimos 4 dígitos de su rut, sin considerar el dígito verificador", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}