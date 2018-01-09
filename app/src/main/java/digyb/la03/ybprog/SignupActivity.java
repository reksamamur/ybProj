package digyb.la03.ybprog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText edtUser;
    private EditText edtEmail;
    private EditText edtPass;
    private Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnReg = findViewById(R.id.btn_reg);

        edtUser =findViewById(R.id.reg_user);

        edtPass =findViewById(R.id.reg_pass);

        edtEmail =findViewById(R.id.reg_email);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register() {
        final String sUser = edtUser.getText().toString().trim();
        final String sPass = edtPass.getText().toString().trim();
        final String sEmail = edtEmail.getText().toString().trim();

        if (sUser.isEmpty() || sPass.isEmpty() || sEmail.isEmpty())
        {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else
        {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_REG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //If we are getting success from server
                        if (response.contains(Config.REG_ISREG)) {
//                            hideDialog();
                            Toast.makeText(SignupActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                            gotoMainActivity();

                        } else {
                            hideDialog();
                            //Displaying an error message on toast
//                            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                            Toast.makeText(SignupActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        hideDialog();
                        Toast.makeText(getApplicationContext(), "The server unreachable", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.REG_USER, sUser);
                params.put(Config.REG_PASS, sPass);
                params.put(Config.REG_EMAIL, sEmail);

                //returning parameter
                return params;
            }

//        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        finish();
//        startActivity(intent);

        };

        Volley.newRequestQueue(this).add(stringRequest);
        }
    }



    private void gotoMainActivity()
    {
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }

    private void showDialog() {
//        if (!pDialog.isShowing())
//            pDialog.show();
    }

    private void hideDialog() {
//        if (pDialog.isShowing())
//            pDialog.dismiss();
    }

    public void btn_Login_sgn(View view){
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }
}
