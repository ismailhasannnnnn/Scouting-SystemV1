package team696.frc.scoutingsystemv1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AutonomousFragment extends Fragment {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference namesRef = databaseReference.child("MatchData");
    DatabaseReference gradesRef = databaseReference.child("grade");

    Button submitButton;
    Button boxesScalePlus;
    Button boxesScaleMinus;
    TextView boxesScaleLabel;
    EditText teamNumberField;
    EditText matchNumberField;
    EditText boxesScaleField;

    private String teamNumber;
    private String matchNumber;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.autonomous_fragment, container, false);
        submitButton = (Button)view.findViewById(R.id.submitButton);
        boxesScalePlus = (Button)view.findViewById(R.id.boxesScalePlus);
        boxesScaleMinus = (Button)view.findViewById(R.id.boxesScaleMinus);
        teamNumberField = (EditText)view.findViewById(R.id.teamNumberField);
        matchNumberField = (EditText)view.findViewById(R.id.matchNumberField);
        boxesScaleField = (EditText)view.findViewById(R.id.boxesScaleField);
        boxesScaleLabel = (TextView) view.findViewById(R.id.boxesScaleLabel);

        boxesScaleField.setKeyListener(null);

        boxesScalePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = convertStringToInt(boxesScaleValue());
                boxesScaleField.setText(String.valueOf(number + 1));
            }
        });

        boxesScaleMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = convertStringToInt(boxesScaleValue());
                boxesScaleField.setText(String.valueOf(number - 1));

                if(Integer.valueOf(boxesScaleValue()) <= 0){
                    boxesScaleField.setText("0");
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamNumber = teamNumberField.getText().toString();
                matchNumber = matchNumberField.getText().toString();
//                namesRef.setValue(nameField.getText().toString());
                namesRef.child("Team " + teamNumber).child("Match " + matchNumber).child("autoBoxesInSwitch").setValue(0);
                namesRef.child("Team " + teamNumber).child("Match " + matchNumber).child("autoBoxesInScale").setValue(convertStringToInt(boxesScaleValue()));
                namesRef.child("Team " + teamNumber).child("Match " + matchNumber).child("teleopBoxes").setValue(9);
            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        namesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = teamNumberField.getText().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void easyToast(String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public String boxesScaleValue(){
        return boxesScaleField.getText().toString();
    }

    public int convertStringToInt(String string){
        return Integer.valueOf(string);
    }
}
