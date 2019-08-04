package ru.ar4i.colorselectionviewsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.ar4i.colorselectionview.ColorSelectionView;

public class MainActivity extends AppCompatActivity {
    private ColorSelectionView vColorSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vColorSelection = findViewById(R.id.v_color_selection);
    }
}
