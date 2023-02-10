package app.d3v3l.mareu.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.databinding.ActivityMainBinding;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.service.MaReuApiService;
import app.d3v3l.mareu.controllers.viewpager.ViewPagerActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Call apiService
        MaReuApiService mApiService = DI.getMaReuApiService();

        binding.mainactivityConnexion.setOnClickListener(view -> {
            // verify if user is in the list with correct Login/Password
            boolean accessGranted = false;
            // check the auth couple into ApiService ==> This check will be done in Database in production
            List<Participant> participants = mApiService.getParticipants();
            for (Participant p : participants) {
                if (p.isInternal()) { // only internal participant can be logged
                    if (p.getLogin().equals(binding.mainactivityLoginField.getText().toString())) {
                        if (p.getPassword().equals(binding.mainactivityPasswordField.getText().toString())) {
                            mApiService.setConnectedParticipant(p);
                            accessGranted = true;
                        }
                    }
                }
            }
            if (accessGranted) {
                Intent intentConnexionClick = new Intent(view.getContext(), ViewPagerActivity.class);
                startActivity(intentConnexionClick);
            } else {
                Toast.makeText(view.getContext(), R.string.connexion_error, Toast.LENGTH_SHORT).show();
            }


        });

    }


}