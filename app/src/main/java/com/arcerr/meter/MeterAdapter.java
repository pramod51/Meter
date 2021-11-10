package com.arcerr.meter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arcerr.meter.databinding.LongHeightItemBinding;
import com.arcerr.meter.databinding.MediumHeightItemBinding;
import com.arcerr.meter.databinding.SmallHeightItemBinding;

public class MeterAdapter extends RecyclerView.Adapter<MeterAdapter.ViewHolder> {
    public interface SelectedPosition{
        void onSelectedPosition(int pos);
    }
    int m=1,l=2,s=0;

    SelectedPosition selectedPosition;

    int leftWidth=0;

    public void setLeftWidth(int leftWidth) {
        this.leftWidth = leftWidth;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        if (position<leftWidth)
            return l;
        if ((position-leftWidth+1)%10==0)
            return m;
        return s;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==m)
        return new ViewHolder(MediumHeightItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
        if (viewType==l)
        return new ViewHolder(LongHeightItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));

        SmallHeightItemBinding binding=SmallHeightItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ViewHolder(binding);

    }


    @Override
    public void onBindViewHolder(@NonNull MeterAdapter.ViewHolder holder, int position) {


    }



    @Override
    public int getItemCount() {
        return 1000;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SmallHeightItemBinding smallHeightBinding;
        private MediumHeightItemBinding mediumHeightBinding;
        public ViewHolder(@NonNull SmallHeightItemBinding smallHeightBinding) {
            super(smallHeightBinding.getRoot());
            this.smallHeightBinding=smallHeightBinding;
        }
        public ViewHolder(@NonNull MediumHeightItemBinding mediumHeightBinding) {
            super(mediumHeightBinding.getRoot());
            this.mediumHeightBinding=mediumHeightBinding;
        }
        public ViewHolder(@NonNull LongHeightItemBinding mediumHeightBinding) {
            super(mediumHeightBinding.getRoot());
        }


    }
}
