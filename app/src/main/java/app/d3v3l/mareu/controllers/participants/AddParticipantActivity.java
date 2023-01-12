package app.d3v3l.mareu.controllers.participants;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import app.d3v3l.mareu.R;
import app.d3v3l.mareu.di.DI;
import app.d3v3l.mareu.model.Participant;
import app.d3v3l.mareu.service.DummyMaReuGenerator;
import app.d3v3l.mareu.service.MaReuApiService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddParticipantActivity extends AppCompatActivity {

    @BindView(R.id.backFromAddParticipant)
    Button back;
    @BindView(R.id.addParticipantActivity_participantCreate)
    Button create;
    @BindView(R.id.addParticipantActivity_avatar)
    ImageView mAvatar;
    @BindView(R.id.addParticipantActivity_firstName)
    TextView mFirstName;
    @BindView(R.id.addParticipantActivity_lastName)
    TextView mLastName;
    @BindView(R.id.addParticipantActivity_email)
    TextView mEmail;
    @BindView(R.id.addParticipantActivity_login)
    TextView mLogin;
    @BindView(R.id.addParticipantActivity_password)
    TextView mPassword;
    @BindView(R.id.addParticipantActivity_isInternal)
    CheckBox mIsInternal;

    private MaReuApiService mApiService;
    private String mAvatarUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participant);
        ButterKnife.bind(this);
        mApiService = DI.getMaReuApiService();

        String firstName = DummyMaReuGenerator.generateFirstName();
        String lastName = DummyMaReuGenerator.generateLastName();
        String email = firstName + "." + lastName + "@lamzone.com";
        String login = firstName + "." + lastName;

        mFirstName.setText(firstName);
        mLastName.setText(lastName);
        mEmail.setText(email.toLowerCase());
        mLogin.setText(login.toLowerCase());
        mPassword.setText(R.string.password_demo);
        mIsInternal.setChecked(true);
        mAvatarUrl = randomImage();
        Glide.with(this).load(mAvatarUrl).placeholder(R.drawable.ic_baseline_people_alt_24)
                .apply(RequestOptions.circleCropTransform()).into(mAvatar);

        mIsInternal.setOnCheckedChangeListener((compoundButton, b) -> {
            if (!b) {
                mLogin.setEnabled(false);
                mPassword.setEnabled(false);
            } else {
                mLogin.setEnabled(true);
                mPassword.setEnabled(true);
            }
        });

        back.setOnClickListener(view -> AddParticipantActivity.this.finish());

        create.setOnClickListener(view -> {

            // If Participant is not internal, login and password must be empty in order to
            // refuse the connexion
            String loginToSave = "";
            String passwordToSave = "";
            if (!mIsInternal.isChecked()) {
                loginToSave =  mLogin.getText().toString();
                passwordToSave =  mPassword.getText().toString();
            }

            Participant participant = new Participant(
                    mApiService.getLastParticipantId() + 1,
                    mFirstName.getText().toString(),
                    mLastName.getText().toString(),
                    loginToSave,
                    passwordToSave,
                    mEmail.getText().toString(),
                    mAvatarUrl,
                    mIsInternal.isChecked()
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