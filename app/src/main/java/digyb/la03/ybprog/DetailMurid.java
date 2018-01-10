package digyb.la03.ybprog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class DetailMurid extends AppCompatActivity {

    Parser parser = new Parser();

    String tNama;

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

        Intent i = getIntent();
        Intent iNama = getIntent();
        String tNama = iNama.getStringExtra("nama");

        Toast.makeText(this, tNama, Toast.LENGTH_SHORT).show();

    }
}