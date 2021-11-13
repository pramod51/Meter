package com.arcerr.meter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.arcerr.meter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    final String TAG=MainActivity.class.getSimpleName();
    int minWeight=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);




        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        MeterAdapter meterAdapter=new MeterAdapter(this);

        binding.recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {

                binding.recyclerView.scrollToPosition(50);
                LinearLayoutManager layoutManager= ((LinearLayoutManager) binding.recyclerView.getLayoutManager());
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                int centerPosition = (firstVisibleItemPosition + lastVisibleItemPosition) / 2;
                minWeight=centerPosition;
                meterAdapter.setLeftWidth(centerPosition);
                meterAdapter.setMiddlePos(centerPosition);
                binding.textView.setText((centerPosition- minWeight +1)+" Kg");
                Log.v(MainActivity.class.getSimpleName(),"center=="+centerPosition);
                binding.recyclerView.setVisibility(View.VISIBLE);
                meterAdapter.notifyItemChanged(centerPosition);

            }
        },1);


        binding.recyclerView.setVisibility(View.INVISIBLE);
        binding.recyclerView.setAdapter(meterAdapter);

        int finalHeight = 0;
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                //super.onScrollStateChanged(recyclerView, newState);

                /*LinearLayoutManager layoutManager= ((LinearLayoutManager) recyclerView.getLayoutManager());
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                int centerPosition = (firstVisibleItemPosition + lastVisibleItemPosition) / 2;
                binding.textView.setText((centerPosition- minWeight +1)+" Kg");
                if (newState==RecyclerView.SCROLL_STATE_IDLE)
                binding.recyclerView.smoothScrollToPosition(firstVisibleItemPosition);
                Log.v(MainActivity.class.getSimpleName(),"center=="+centerPosition);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );*/
                /*if ((firstVisibleItemPosition-minWeight+1))
                params.setMargins(8,0,0,0);
                binding.longHeight.setLayoutParams(params);*/


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                //super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager= ((LinearLayoutManager) recyclerView.getLayoutManager());
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                int centerPosition = (firstVisibleItemPosition + lastVisibleItemPosition) / 2;
                binding.recyclerView.smoothScrollToPosition(firstVisibleItemPosition);
                meterAdapter.setMiddlePos(centerPosition);
                binding.textView.setText((centerPosition- minWeight +1)+" Kg");
                //meterAdapter.notifyItemChanged(centerPosition);
                meterAdapter.notifyDataSetChanged();
            }
        });






    }

}