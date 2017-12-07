package com.example.cz10000_001.mytestapp;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cz10000_001.mytestapp.util.DataUtil;
import com.example.cz10000_001.mytestapp.util.PreferenceUtil;
import com.example.cz10000_001.mytestapp.util.ToastUtil;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity3 extends AppCompatActivity {

    private SpringView springView ;
    private RecyclerView recyclerView ;

    private List<String> lists = new ArrayList<>() ;
    private itemAdapter adapter ;
    private Context mcontext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);


        mcontext = this ;

        recyclerView = (RecyclerView) findViewById(R.id.recy);
        initList() ;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new itemAdapter() ;
        recyclerView.setAdapter(adapter);

        springView = (SpringView) findViewById(R.id.springView);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        springView.onFinishFreshAndLoad();
//                    }
//                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
//        springView.setHeader(new DefaultHeader(this));
        springView.setFooter(new DefaultFooter(this));

    }

    private void initList() {
        for (int i = 0; i < 80; i++) {
            lists.add("item"+i) ;

        }
    }



    class  itemAdapter extends RecyclerView.Adapter<itemAdapter.MyViewHolder>{


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.layout_item,parent,false) ;
            MyViewHolder viewHolder = new MyViewHolder(view) ;
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.textView.setText(lists.get(position));
        }

        @Override
        public int getItemCount() {
            return lists.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView textView ;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.layout_item_tv);

            }
        }
    }
}
