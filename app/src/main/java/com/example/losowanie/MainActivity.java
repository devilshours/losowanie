package com.example.losowanie;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    private EditText Liczba;
    private GridLayout grid;
    private TextView Wynik;
    private final List<Integer> wyniki = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Liczba = findViewById(R.id.editTextLiczbaKostek);
        grid = findViewById(R.id.gridKostki);
        Wynik = findViewById(R.id.textViewWyniki);
        findViewById(R.id.buttonLosuj).setOnClickListener(v -> wylosuj());
        findViewById(R.id.buttonRosnaco).setOnClickListener(v -> sortuj(true));
        findViewById(R.id.buttonMalejaco).setOnClickListener(v -> sortuj(false));
    }
    private void wylosuj() {
        try {
            int n = Integer.parseInt(Liczba.getText().toString());
            if (n < 1 || n > 100) throw new Exception();
            wyniki.clear();
            Random r = new Random();
            for (int i = 0; i < n; i++) wyniki.add(r.nextInt(6) + 1);
            odswiez();
        } catch (Exception e) {
            Toast.makeText(this, "Wpisz liczbę od 1 do 100!", Toast.LENGTH_SHORT).show();
        }
    }
    private void sortuj(boolean rosnaco) {
        if (wyniki.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (rosnaco) Collections.sort(wyniki);
        else wyniki.sort(Collections.reverseOrder());
        odswiez();
    }
    private void odswiez() {
        grid.removeAllViews();
        grid.setColumnCount(5);
        for (int w : wyniki) {
            TextView tv = new TextView(this);
            tv.setText(String.valueOf(w));
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            tv.setLayoutParams(params);
            grid.addView(tv);
        }
        Wynik.setText(wyniki.toString().replace("[", "").replace("]", ""));
    }
}