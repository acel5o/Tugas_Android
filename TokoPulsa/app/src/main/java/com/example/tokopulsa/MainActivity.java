package com.example.tokopulsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.tokopulsa.adapter.PulsaAdapter;
import com.example.tokopulsa.model.Pulsa;
import com.example.tokopulsa.viewmodel.PulsaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list_pulsa;
    private ArrayList<Pulsa> arrTemp =new ArrayList<>();
    private PulsaAdapter pulsaAdapter;
    private PulsaViewModel pulsaViewModel;
    private List<Pulsa> pulsaList;
    private View viewfocus;
    private RelativeLayout layout_rincian;
    private AppCompatImageView ic_close;
    private AppCompatTextView tv_payment,tv_pulsa;
    private CoordinatorLayout layoutPulsa;
    private LinearLayoutCompat btn_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView(){
        list_pulsa=findViewById(R.id.list_pulsa);
        viewfocus=findViewById(R.id.viewfocus);
        layout_rincian=findViewById(R.id.layout_rincian);
        ic_close=findViewById(R.id.ic_close);
        tv_payment=findViewById(R.id.tv_payment);
        tv_pulsa=findViewById(R.id.tv_pulsa);
        layoutPulsa=findViewById(R.id.layoutPulsa);
        btn_pay=findViewById(R.id.btn_pay);
    }

    private void initData(){
        pulsaViewModel = ViewModelProviders.of(this).get(PulsaViewModel.class);
        if (pulsaAdapter==null){
            pulsaAdapter=new PulsaAdapter(MainActivity.this,arrTemp,layout_rincian,
                    viewfocus,ic_close,tv_payment,layoutPulsa,tv_pulsa,btn_pay,pulsaViewModel);
            list_pulsa.setLayoutManager(new GridLayoutManager(this,2));
            list_pulsa.setAdapter(pulsaAdapter);
            list_pulsa.setHasFixedSize(true);
            list_pulsa.setItemAnimator(new DefaultItemAnimator());
            list_pulsa.setNestedScrollingEnabled(true);
        } else {
            pulsaAdapter.notifyDataSetChanged();
        }
        pulsaViewModel.init();
        pulsaViewModel.getPulsa().observe(this,pulsaResponse -> {
            pulsaList=pulsaResponse.getData();
            arrTemp.clear();
            arrTemp.addAll(pulsaList);
            pulsaAdapter.notifyDataSetChanged();
        });
    }
}
