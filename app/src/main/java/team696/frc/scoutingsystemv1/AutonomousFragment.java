package team696.frc.scoutingsystemv1;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AutonomousFragment extends Fragment {

    public View view;

    Button submitButton;
    Button boxesScalePlus;
    Button boxesScaleMinus;
    Button boxesSwitchPlus;
    Button boxesSwitchMinus;
    Button boxesExchangeMinus;
    Button boxesExchangePlus;

    TextView boxesScaleLabel;
    TextView boxesSwitchLabel;

    EditText teamNumberField;
    EditText matchNumberField;
    EditText boxesScaleField;
    EditText boxesSwitchField;
    EditText boxesExchangeField;

    RadioGroup allianceColors;
    RadioButton blueAlliance;
    RadioButton redAlliance;

    Spinner startingPosDropDown;
    Spinner switchPosDropDown;
    Spinner scalePosDropDown;
    Spinner autoRunDropDown;

    public static String teamNumber;
    public static String matchNumber;
    public static String selectedAllianceColor;
    public static String startingPos;
    public static String switchPos;
    public static String scalePos;
    public static String autoRun;
    public static int autoSwitch;
    public static int autoScale;
    public static int autoExchange;


    String startingPosItems[] = new String[]{"Select One", "Right", "Center", "Left"};
    String switchPosItems[] = new String[]{"Select One", "Right", "Left"};
    String scalePosItems[] = new String[]{"Select One", "Right", "Left"};
    String autoRunItems[] = new String[]{"Select One", "Yes", "No"};

    ArrayAdapter<String> switchPosAdapter;
    ArrayAdapter<String> startingPosAdapter;
    ArrayAdapter<String> scalePosAdapter;
    ArrayAdapter<String> autoRunAdapter;

    int num = 0;
    int allianceIndex = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.autonomous_fragment, container, false);

        submitButton = (Button) view.findViewById(R.id.realSubmitButton);
        boxesScalePlus = (Button) view.findViewById(R.id.boxesScalePlus);
        boxesScaleMinus = (Button) view.findViewById(R.id.boxesScaleMinus);
        boxesSwitchPlus = (Button) view.findViewById(R.id.boxesSwitchPlus);
        boxesSwitchMinus = (Button) view.findViewById(R.id.boxesSwitchMinus);
        boxesExchangeMinus = (Button) view.findViewById(R.id.boxesExchangeMinus);
        boxesExchangePlus = (Button) view.findViewById(R.id.boxesExchangePlus);

        teamNumberField = (EditText) view.findViewById(R.id.teamNumberField);
        matchNumberField = (EditText) view.findViewById(R.id.matchNumberField);
        boxesScaleField = (EditText) view.findViewById(R.id.boxesScaleField);
        boxesSwitchField = (EditText) view.findViewById(R.id.boxesSwitchField);
        boxesExchangeField = (EditText) view.findViewById(R.id.boxesExchangeField);

        boxesScaleLabel = (TextView) view.findViewById(R.id.boxesScaleLabel);
        boxesSwitchLabel = (TextView) view.findViewById(R.id.boxesSwitchLabel);

        allianceColors = (RadioGroup) view.findViewById(R.id.allianceColors);
        blueAlliance = (RadioButton) view.findViewById(R.id.blueAlliance);
        redAlliance = (RadioButton) view.findViewById(R.id.redAlliance);

        startingPosDropDown = (Spinner) view.findViewById(R.id.startingPosDropDown);
        switchPosDropDown = (Spinner) view.findViewById(R.id.switchPosDropDown);
        scalePosDropDown = (Spinner) view.findViewById(R.id.scalePosDropDown);
        autoRunDropDown = (Spinner) view.findViewById(R.id.autoRunDropDown);

        boxesScaleField.setKeyListener(null);
        boxesSwitchField.setKeyListener(null);

        /*
            Create DropDown Menus
         */

        startingPosAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_dropdown, startingPosItems);
        startingPosAdapter.setDropDownViewResource(R.layout.custom_dropdown);
        startingPosDropDown.setAdapter(startingPosAdapter);
        startingPosDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startingPos = startingPosItems[0];
                        break;

                    case 1:
                        startingPos = startingPosItems[1];
                        break;

                    case 2:
                        startingPos = startingPosItems[2];
                        break;

                    case 3:
                        startingPos = startingPosItems[3];
                        break;

                    default:
                        startingPos = startingPosItems[0];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        switchPosAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_dropdown, switchPosItems);
        switchPosAdapter.setDropDownViewResource(R.layout.custom_dropdown);
        switchPosDropDown.setAdapter(switchPosAdapter);
        switchPosDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        switchPos = switchPosItems[0];
                        break;

                    case 1:
                        switchPos = switchPosItems[1];
                        break;

                    case 2:
                        switchPos = switchPosItems[2];
                        break;

                    default:
                        switchPos = switchPosItems[0];
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        scalePosAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_dropdown, scalePosItems);
        scalePosAdapter.setDropDownViewResource(R.layout.custom_dropdown);
        scalePosDropDown.setAdapter(scalePosAdapter);
        scalePosDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        scalePos = scalePosItems[0];
                        break;

                    case 1:
                        scalePos = scalePosItems[1];
                        break;

                    case 2:
                        scalePos = scalePosItems[2];
                        break;

                    default:
                        scalePos = scalePosItems[0];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        autoRunAdapter = new ArrayAdapter<>(getContext(), R.layout.custom_dropdown, autoRunItems);
        autoRunAdapter.setDropDownViewResource(R.layout.custom_dropdown);
        autoRunDropDown.setAdapter(autoRunAdapter);
        autoRunDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        autoRun = autoRunItems[0];
                        break;

                    case 1:
                        autoRun = autoRunItems[1];
                        break;

                    case 2:
                        autoRun = autoRunItems[2];
                        break;

                    default:
                        autoRun = autoRunItems[0];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        /*
         * Button Listeners
         */

        boxesScalePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = convertStringToInt(boxesScaleValue());
                boxesScaleField.setText(String.valueOf(number + 1));

                autoScale = convertStringToInt(boxesScaleValue());
            }
        });

        boxesScaleMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = convertStringToInt(boxesScaleValue());
                boxesScaleField.setText(String.valueOf(number - 1));

                if (Integer.valueOf(boxesScaleValue()) <= 0) {
                    boxesScaleField.setText("0");
                }

                autoScale = convertStringToInt(boxesScaleValue());
            }
        });

        boxesSwitchPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = convertStringToInt(boxesSwitchValue());
                boxesSwitchField.setText(String.valueOf(number + 1));

                autoSwitch = convertStringToInt(boxesSwitchValue());
            }
        });

        boxesSwitchMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = convertStringToInt(boxesSwitchValue());
                boxesSwitchField.setText(String.valueOf(number - 1));

                if (Integer.valueOf(boxesSwitchValue()) <= 0) {
                    boxesSwitchField.setText("0");
                }

                autoSwitch = convertStringToInt(boxesSwitchValue());
            }
        });

        boxesExchangePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = convertStringToInt(boxesExchangeValue());
                boxesExchangeField.setText(String.valueOf(number + 1));

                autoExchange = convertStringToInt(boxesExchangeValue());
            }
        });

        boxesExchangeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = convertStringToInt(boxesExchangeValue());
                boxesExchangeField.setText(String.valueOf(number - 1));

                if (Integer.valueOf(boxesExchangeValue()) <= 0) {
                    boxesExchangeField.setText("0");
                }

                autoExchange = convertStringToInt(boxesExchangeValue());
            }
        });

        return view;
    }

    public void easyToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public String boxesScaleValue() {
        return boxesScaleField.getText().toString();
    }

    public String boxesSwitchValue() {
        return boxesSwitchField.getText().toString();
    }

    public String boxesExchangeValue() {
        return boxesExchangeField.getText().toString();
    }

    public int convertStringToInt(String string) {
        return Integer.valueOf(string);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        // Make sure that we are currently visible
        if (this.isVisible()) {
            // If we are becoming invisible, then...
            if (!isVisibleToUser) {
                sendData();
            }
        }
    }

    public void sendData() {
        teamNumber = teamNumberField.getText().toString();
        matchNumber = matchNumberField.getText().toString();

        if (blueAlliance.isChecked()) {
            selectedAllianceColor = "blue";
        } else if (redAlliance.isChecked()) {
            selectedAllianceColor = "red";
        } else {
            selectedAllianceColor = "";
        }

        TeleopFragment.startingPos = startingPos;
        TeleopFragment.switchPos = switchPos;
        TeleopFragment.scalePos = scalePos;
        TeleopFragment.autoRun = autoRun;
        TeleopFragment.allianceColor = selectedAllianceColor;
        TeleopFragment.teamNumber = teamNumber;
        TeleopFragment.matchNumber = matchNumber;
        TeleopFragment.autoSwitch = autoSwitch;
        TeleopFragment.autoScale = autoScale;
        TeleopFragment.autoExchange = autoExchange;

    }


}
