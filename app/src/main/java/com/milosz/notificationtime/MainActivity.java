package com.milosz.notificationtime;

import android.app.TimePickerDialog;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private EditText editTextTitle;
    private EditText editTextMessage;
    private Button buttonChannel1;
    private Button buttonChannel2;

    private NotificationHelper mNotificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTitle=findViewById(R.id.editTextTitle);
        editTextMessage=findViewById(R.id.editTextMessage);
        buttonChannel1=findViewById(R.id.button_channel1);
        buttonChannel2=findViewById(R.id.button_channel2);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationHelper=new NotificationHelper(this);
        }

        buttonChannel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel1(editTextTitle.getText().toString(),editTextMessage.getText().toString());
            }
        });
        buttonChannel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel2(editTextTitle.getText().toString(),editTextMessage.getText().toString());
            }
        });


        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });
    }

    public void sendOnChannel1(String title,String message) {
        NotificationCompat.Builder nb=mNotificationHelper.getChannel1Notification(title,message);
        mNotificationHelper.getManager().notify(1,nb.build());
    }
    public void sendOnChannel2(String title,String message) {
        NotificationCompat.Builder nb=mNotificationHelper.getChannel2Notification(title,message);
        mNotificationHelper.getManager().notify(2,nb.build());
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView=findViewById(R.id.textView);
        textView.setText("Hour: "+ hourOfDay+" Minute: "+minute);
    }
}
