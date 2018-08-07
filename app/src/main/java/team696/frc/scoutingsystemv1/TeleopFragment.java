package team696.frc.scoutingsystemv1;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeleopFragment extends Fragment {

    /*
    Database References
     */

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference matchData = databaseReference.child("MatchData");

    /*
    Data from AutonomousFragment
     */

    AutonomousFragment autonomousFragment;

    public static String startingPos;
    public static String switchPos;
    public static String scalePos;
    public static String autoRun;
    public static String allianceColor;
    public static String teamNumber;
    public static String matchNumber;
    public static int autoSwitch;
    public static int autoScale;
    public static int autoExchange;

    /*
    Android Context Classes
     */

    Button button;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teleop_fragment, container, false);
        autonomousFragment = new AutonomousFragment();

        button = (Button)view.findViewById(R.id.submitButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (teamNumber.equals("") || teamNumber.equals("0")) {
                    easyToast("You have not selected one or some of your items.");
                } else if (matchNumber.equals("")) {
                    easyToast("You have not selected one or some of your items.");
                } else if (startingPos.equals("Select One")){
                    easyToast("You have not selected one or some of your items.");
                } else if (switchPos.equals("Select One")) {
                    easyToast("You have not selected one or some of your items.");
                } else if (scalePos.equals("Select One")) {
                    easyToast("You have not selected one or some of your items.");
                } else if (autoRun.equals("Select One")) {
                    easyToast("You have not selected one or some of your items.");
                } else if (allianceColor.equals("")) {
                    easyToast("You have not selected one or some of your items.");
                } else {
                    matchData.child(teamNumber).child(matchNumber).child("startingPos").setValue(startingPos);
                    matchData.child(teamNumber).child(matchNumber).child("switchPos").setValue(switchPos);
                    matchData.child(teamNumber).child(matchNumber).child("scalePos").setValue(scalePos);
                    matchData.child(teamNumber).child(matchNumber).child("autoRun").setValue(autoRun);
                    matchData.child(teamNumber).child(matchNumber).child("allianceColor").setValue(allianceColor);
                    matchData.child(teamNumber).child(matchNumber).child("autoSwitch").setValue(autoSwitch);
                    matchData.child(teamNumber).child(matchNumber).child("autoScale").setValue(autoScale);
                    matchData.child(teamNumber).child(matchNumber).child("autoExchange").setValue(autoExchange);

                }
            }
        });

        matchData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                easyToast("Data Submitted!");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                easyToast("Data Submitted!");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    public void easyToast(String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        Intent intent = new Intent(getActivity().getBaseContext(), MatchForm.class);
//        Bundle args = new Bundle();

        // Make sure that we are currently visible
        if (this.isVisible()) {
            // If we are becoming invisible, then...
            if (!isVisibleToUser) {
            }
        }
    }


}
