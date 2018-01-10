package digyb.la03.ybprog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailMurid extends AppCompatActivity {

    Parser parser = new Parser();

    String tNama;

    TextView a,b,c,d,e,f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_murid);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_detail);
        toolbar.setTitle(R.string.detail);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        a = findViewById(R.id.hasilNol);
        b = findViewById(R.id.hasilSatu);
        c = findViewById(R.id.hasilTiga);
        d = findViewById(R.id.hasilEmpat);
        e = findViewById(R.id.hasilLima);

        Intent i = getIntent();
        Intent iNama = getIntent();
        String tNama = iNama.getStringExtra("nama");

        diget();

        Toast.makeText(this, tNama, Toast.LENGTH_SHORT).show();

    }

    public void dipost(){

//        boolean isSuccess = false;



        final String sNama = tNama.toString().trim();


            final ProgressDialog prog = ProgressDialog.show(DetailMurid.this,"","Loading....",true);

            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_LIST2,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {

                            //If we are getting success from server
                            if (response.contains(Config.LOGIN_ISLOG)) {
                                prog.dismiss();
//                                gotoMainActivity();

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
                    params.put(Config.LOGIN_USER, sNama);

                    //returning parameter
                    return params;
                }
            };

            //Adding the string request to the queue
            Volley.newRequestQueue(this).add(stringRequest);

        }

    public void diget()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_LIST2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonobject = new JSONObject(response);
                    JSONArray jsonarray = jsonobject.getJSONArray("users");
                    JSONObject data = jsonarray.getJSONObject(0);

                    String sA = data.getString("A");
                    String sB = data.getString("B");
                    String sC = data.getString("C");
                    String sD = data.getString("D");
                    String sE = data.getString("E");

                    a.setText(sA);
                    b.setText(sB);
                    c.setText(sC);
                    d.setText(sD);
                    e.setText(sE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailMurid.this, "Something went wrong",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<String, String>();
                parameters.put("nama", tNama);
                return parameters;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);

    }
}