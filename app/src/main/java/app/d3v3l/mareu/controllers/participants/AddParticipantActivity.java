package app.d3v3l.mareu.controllers.participants;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.databinding.ActivityAddParticipantBinding;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.service.MaReuApiService;

public class AddParticipantActivity extends AppCompatActivity {

    private MaReuApiService mApiService;
    private String mAvatarUrl;
    private ActivityAddParticipantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddParticipantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Call ApiService
        mApiService = DI.getMaReuApiService();

        mAvatarUrl = randomImage();
        Glide.with(this).load(mAvatarUrl).placeholder(R.drawable.ic_baseline_people_alt_24)
                .apply(RequestOptions.circleCropTransform()).into(binding.addParticipantActivityAvatar);

        binding.addParticipantActivityIsInternal.setOnCheckedChangeListener((compoundButton, b) -> {
            if (!b) {
                binding.addParticipantActivityLogin.setEnabled(false);
                binding.addParticipantActivityPassword.setEnabled(false);
            } else {
                binding.addParticipantActivityLogin.setEnabled(true);
                binding.addParticipantActivityPassword.setEnabled(true);
            }
        });

        binding.backFromAddParticipant.setOnClickListener(view -> AddParticipantActivity.this.finish());

        binding.addParticipantActivityParticipantCreate.setOnClickListener(view -> {

            // If Participant is not internal, login and password must be empty in order to refuse the connexion
            String loginToSave;
            String passwordToSave;
            if (!binding.addParticipantActivityIsInternal.isChecked()) {
                loginToSave =  binding.addParticipantActivityLogin.getText().toString();
                passwordToSave =  binding.addParticipantActivityPassword.getText().toString();
            } else {
                loginToSave = "";
                passwordToSave = "";
            }

            Participant participant = new Participant(
                    mApiService.getLastParticipantId() + 1,
                    binding.addParticipantActivityFirstName.getText().toString(),
                    binding.addParticipantActivityLastName.getText().toString(),
                    loginToSave,
                    passwordToSave,
                    binding.addParticipantActivityEmail.getText().toString(),
                    mAvatarUrl,
                    binding.addParticipantActivityIsInternal.isChecked()
            );
            mApiService.createParticipant(participant);
            AddParticipantActivity.this.finish();
        });

    }

    /**
     * Generate a random image. Useful to mock image picker
     * @return String
     */
    String randomImage() {
        return "https://i.pravatar.cc/300?u="+ System.currentTimeMillis();
    }
}