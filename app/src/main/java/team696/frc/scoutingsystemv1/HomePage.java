package team696.frc.scoutingsystemv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    Button matchFormButton;
    Button pitScoutButton;
    Button headScoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        matchFormButton = (Button)findViewById(R.id.matchFormButton);
        pitScoutButton = (Button)findViewById(R.id.pitScoutButton);
        headScoutButton = (Button)findViewById(R.id.headScoutButton);

        matchFormButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, MatchForm.class));
            }

        });

    }
}
