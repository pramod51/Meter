package com.arcerr.meter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arcerr.meter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    final String TAG = MainActivity.class.getSimpleName();
    int minWeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        MeterAdapter meterAdapter = new MeterAdapter(this);

        binding.recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //binding.recyclerView.scrollToPosition(30);

                LinearLayoutManager layoutManager = ((LinearLayoutManager) binding.recyclerView.getLayoutManager());
                layoutManager.scrollToPositionWithOffset(40,0);
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                int centerPosition = (firstVisibleItemPosition + lastVisibleItemPosition) / 2;
                minWeight = centerPosition;
                meterAdapter.setLeftWidth(centerPosition);
                binding.textView.setText(String.valueOf(centerPosition - minWeight + 1));
                Log.v(MainActivity.class.getSimpleName(), "center==" + centerPosition);
                binding.recyclerView.setVisibility(View.VISIBLE);

            }
        }, 1);


        binding.recyclerView.setVisibility(View.INVISIBLE);
        binding.recyclerView.setAdapter(meterAdapter);

        int finalHeight = 0;
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                //super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager layoutManager= ((LinearLayoutManager) recyclerView.getLayoutManager());
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                int centerPosition = (firstVisibleItemPosition + lastVisibleItemPosition) / 2;
                binding.textView.setText((centerPosition- minWeight +1)+"");
                if (newState==RecyclerView.SCROLL_STATE_IDLE)
                binding.recyclerView.smoothScrollToPosition(firstVisibleItemPosition);
                Log.v(MainActivity.class.getSimpleName(),"center=="+centerPosition);



            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                //super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                int centerPosition = (firstVisibleItemPosition + lastVisibleItemPosition) / 2;
                binding.textView.setText(String.valueOf(centerPosition - minWeight + 1));

            }
        });

        setGradientOnTextView(binding.textView);
        setGradientOnTextView(binding.textView2);

    }

    public static void setGradientOnTextView(TextView textView) {
        TextPaint paint = textView.getPaint();
        float width = paint.measureText((String) textView.getText());

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        ContextCompat.getColor(textView.getContext(), R.color.start_color),
                        ContextCompat.getColor(textView.getContext(), R.color.end_color),
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);
    }

}