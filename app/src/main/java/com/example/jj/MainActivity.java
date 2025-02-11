package com.example.jj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void showHello(View view) {
        clickCount++;
        String text = textView.getText().toString();
        if (text.isEmpty() /* clickCount == 1*/) {
            text = "Hi";
        } else if (clickCount % 2 == 0){
            text = getString(R.string.jeya);
        }
        else{
            text = getString(R.string.nanthu);
        }
        textView.setText(text);
    }

    public void goToLocationActivity(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    public void play(View view) {
        startService(new Intent( this, MusicService.class ) );
    }

    public void stopPlaying(View view) {
        stopService(new Intent( this, MusicService.class ) );
    }
}