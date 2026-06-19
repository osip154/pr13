package com.example.pr713_mirzakamilov_pr_23103;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public boolean Started = false;
    public boolean Finished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Убираем верхнюю панель и делаем на весь экран
        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e) {}
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
    }

    public void Start(View view) {
        Button button = findViewById(R.id.btnStart);
        if (!Finished) {
            if (!Started) {
                button.setBackgroundColor(Color.RED);
                button.setText("Пауза");
                Started = true;
            } else {
                button.setBackgroundColor(Color.GREEN);
                button.setText("Старт");
                Started = false;
            }
        } else {
            // Если кто-то победил, кнопка перезапускает игру
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    // Движение первой машины
    public void Drive1(View view) {
        ImageView car = findViewById(R.id.car1);
        TextView result = findViewById(R.id.tvResult);
        Button btnStart = findViewById(R.id.btnStart);

        if (Started && !Finished) {
            ViewGroup.MarginLayoutParams margin = (ViewGroup.MarginLayoutParams) car.getLayoutParams();
            margin.leftMargin += 30; // Скорость движения
            car.requestLayout();

            // Проверка: доехала ли до края (финиша)
            ImageView finish = findViewById(R.id.background_chess);
            if (car.getX() + car.getWidth() >= finish.getX()) {
                result.setText("ПОБЕДА КРАСНОЙ МАШИНЫ!");
                result.setTextColor(Color.WHITE);
                btnStart.setText("ЗАНОВО");
                Finished = true;
            }
        }
    }

    // Движение второй машины
    public void Drive2(View view) {
        ImageView car = findViewById(R.id.car2);
        TextView result = findViewById(R.id.tvResult);
        Button btnStart = findViewById(R.id.btnStart);

        if (Started && !Finished) {
            ViewGroup.MarginLayoutParams margin = (ViewGroup.MarginLayoutParams) car.getLayoutParams();
            margin.leftMargin += 30;
            car.requestLayout();

            // Проверка: доехала ли до края (финиша)
            ImageView finish = findViewById(R.id.background_chess);
            if (car.getX() + car.getWidth() >= finish.getX()) {
                result.setText("ПОБЕДА ЧЁРНОЙ МАШИНЫ!");
                result.setTextColor(Color.WHITE);
                btnStart.setText("ЗАНОВО");
                Finished = true;
            }
        }
    }
}