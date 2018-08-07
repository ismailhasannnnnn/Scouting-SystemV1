package team696.frc.scoutingsystemv1;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

public class HomePage extends AppCompatActivity {

    private static final String TAG = "HomePage";

    Button matchFormButton;
    Button matchDataButton;
    Button pitScoutButton;
    Button headScoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        matchFormButton = (Button)findViewById(R.id.matchFormButton);
        matchDataButton = (Button)findViewById(R.id.matchDataButton);
        pitScoutButton = (Button)findViewById(R.id.pitScoutButton);
        headScoutButton = (Button)findViewById(R.id.headScoutButton);

        matchFormButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, MatchForm.class));
            }

        });

        matchDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, MatchData.class));
            }
        });



    }
}
