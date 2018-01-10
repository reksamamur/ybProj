package digyb.la03.ybprog;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewScoreFragment extends Fragment {


    ListView listView;
    Button btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_score, container, false);

        listView = view.findViewById(R.id.lv);

        //---------------------------------------

        final ListView lv= view.findViewById(R.id.lv);
        final Downloader d=new Downloader(getActivity(),Config.URL_LIST,lv);

                //EXECUTE DOWNLOAD
                d.execute();


        //--------------------------------------


        //+++++++++++++++++++++++++++++++++++++

        //+++++++++++++++++++++++++++++++++++++
        return view;
    }

}

