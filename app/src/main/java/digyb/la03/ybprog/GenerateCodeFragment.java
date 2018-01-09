package digyb.la03.ybprog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class GenerateCodeFragment extends Fragment {

    TextView kode;
    Button genereate;
    String code;


    public GenerateCodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_generate_code, container, false);

        kode = view.findViewById(R.id.txt_code);
        genereate = view.findViewById(R.id.btn_generate_code);

        genereate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateCode();

            }
        });

        return view;
    }

    public void generateCode()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Data bakal di hapus klo generate biji").setTitle("BAHAYA");

        builder.setPositiveButton("OKE BIJI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                StringBuilder salt = new StringBuilder();
                Random rnd = new Random();
                while (salt.length() < 5) { // length of the random string.
                    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                    salt.append(SALTCHARS.charAt(index));
                }
                code = salt.toString();

                kode.setText(code);

                sendCode();
            }
        });
        builder.setNegativeButton("DIH COPO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getContext(), "Yah Takut", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        //code

    }

    public void sendCode() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_REG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //If we are getting success from server
                        if (response.contains(Config.CODE_ISACC)) {
//                            hideDialog();
                            Toast.makeText(getActivity(), "Access Updated", Toast.LENGTH_SHORT).show();
                            gotoMainActivity();

                        } else {
                            hideDialog();
                            //Displaying an error message on toast
//                            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                            Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        hideDialog();
                        Toast.makeText(getContext(), "The server unreachable", Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.CODE_KODE, code);
//                params.put(Config.REG_PASS, sPass);
//                params.put(Config.REG_EMAIL, sEmail);

                //returning parameter
                return params;
            }
        };

        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    private void gotoMainActivity()
    {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        finish();
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

}
