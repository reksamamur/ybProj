package digyb.la03.ybprog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button btn_login;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pDialog = new ProgressDialog(getApplicationContext());
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Login();

//                Toast.makeText(LoginActivity.this, "HOREEE", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void btn_Login(){

//        boolean isSuccess = false;

        email = findViewById(R.id.edt_email);
        pass = findViewById(R.id.edt_pass);


        final String sEmail = email.getText().toString().trim();
        final String sPass = pass.getText().toString().trim();
        
        if(sEmail.isEmpty() || sPass.isEmpty())
        {
            Toast.makeText(this, "Please input Username / Password", Toast.LENGTH_SHORT).show();
        }
        else
        {

        final ProgressDialog prog = ProgressDialog.show(LoginActivity.this,"","Loading....",true);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_LOGIN,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        //If we are getting success from server
                        if (response.contains(Config.LOGIN_ISLOG)) {
                            prog.dismiss();
                            gotoMainActivity();

                        } else {
                            prog.dismiss();
                            //Displaying an error message on toast
                            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        prog.dismiss();
                        Toast.makeText(getApplicationContext(), "The server unreachable", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.LOGIN_USER, sEmail);
                params.put(Config.LOGIN_PASS, sPass);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);

        }

    }

    private void gotoMainActivity()
    {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }

//    private void showDialog() {
////        if (!pDialog.isShowing())
//            pDialog.show();
//    }
//
//    private void hideDialog() {
////        if (pDialog.isShowing())
//            pDialog.dismiss();
//    }


    public void btn_SignUp(View view){
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }
}
