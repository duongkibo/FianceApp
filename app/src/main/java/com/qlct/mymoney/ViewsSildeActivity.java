package com.qlct.mymoney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.qlct.mymoney.databinding.ActivityViewsSildeBinding;

public class ViewsSildeActivity extends AppCompatActivity {

    private ViewsSliderAdapter mAdapter;
    private TextView[] dots;
    private int[] layouts;
    private ActivityViewsSildeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isfirstrun", true);

        if (isFirstRun) {
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isfirstrun", false).commit();
        } else {
            launchHomeScreen();
        }

        binding = ActivityViewsSildeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
        layouts = new int[]{
                R.layout.slide_one,
                R.layout.slide_two,
                R.layout.slide_three
                //R.layout.slide_four
        };
        mAdapter = new ViewsSliderAdapter();
        binding.viewPager.setAdapter(mAdapter);
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback);

        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    binding.viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });

        addBottomDots(0);
    }

    private int getItem(int i) {
        return binding.viewPager.getCurrentItem() + 1;
    }

    private void launchHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        binding.layoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            binding.layoutDots.addView(dots[i]);

        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);

    }

    ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                binding.btnNext.setText("Got it");
                binding.btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                binding.btnNext.setText("Next");
                binding.btnSkip.setVisibility(View.VISIBLE);
            }
        }
    };


    public class ViewsSliderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public ViewsSliderAdapter() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false);
            return new SliderViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemViewType(int position) {
            return layouts[position];
        }

        @Override
        public int getItemCount() {
            return layouts.length;
        }

        public class SliderViewHolder extends RecyclerView.ViewHolder {
            public TextView title, year, genre;

            public SliderViewHolder(View view) {
                super(view);
            }
        }
    }

}