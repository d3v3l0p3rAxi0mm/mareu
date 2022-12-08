package app.d3v3l.mareu.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import app.d3v3l.mareu.R;

import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.service.MaReuApiService;
import app.d3v3l.mareu.views.viewpager.ViewPagerActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.mainactivity_loginField)
    EditText mLogin;
    @BindView(R.id.mainactivity_passwordField)
    EditText mPassword;
    @BindView(R.id.mainactivity_connexion)
    Button mConnexion;
    @BindView(R.id.mainactivity_smile)
    ImageView mAutoConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call apiService
        MaReuApiService mApiService = DI.getMaReuApiService();

        // Bind widgets
        ButterKnife.bind(this);

        mConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // verify if user is in the list with correct Login/Password
                boolean accessGranted = false;
                List<Participant> participants = mApiService.getParticipants();
                for (Participant p: participants) {
                    if (Objects.equals(p.getLogin(), mLogin.getText().toString())) {
                        if (Objects.equals(p.getPassword(), mPassword.getText().toString())) {
                            //TODO Keep in memory this participant
                            accessGranted = true;
                        }
                    }
                }
                if (accessGranted) {
                    Intent intentConnexionClick = new Intent(view.getContext(), ViewPagerActivity.class);
                    startActivity(intentConnexionClick);
                } else {
                    Toast.makeText(view.getContext(), R.string.connexion_error, Toast.LENGTH_SHORT).show();
                }


            }
        });

        mAutoConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLogin.setText("visitor");
                mPassword.setText("1234");
                mConnexion.performClick();
            }
        });


    }


}