package com.example.cz10000_001.mytestapp;


import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class TSDrawActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private NavigationView navigation;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_tsdraw);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //设置toolbar标题文本
        toolbar.setTitle("首页");
        //设置toolbar
        setSupportActionBar(toolbar);
        //设置左上角图标是否可点击
        getSupportActionBar().setHomeButtonEnabled(true);
        //左上角加上一个返回图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigation = (NavigationView) findViewById(R.id.drawer_navigation);


        //初始化ActionBarDrawerToggle(ActionBarDrawerToggle就是一个开关一样用来打开或者关闭drawer)
        drawerToggle = new ActionBarDrawerToggle(TSDrawActivity.this,drawerLayout,toolbar,R.string.openString,R.string.closeString){
            /*
            * 抽屉菜单打开监听
            * */
            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(TSDrawActivity.this,"菜单打开了",Toast.LENGTH_SHORT).show();
                super.onDrawerOpened(drawerView);
            }
            /*
            * 抽屉菜单关闭监听
            * */
            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(TSDrawActivity.this,"菜单关闭了",Toast.LENGTH_SHORT).show();
                super.onDrawerClosed(drawerView);
            }
        };


          /*
        * NavigationView设置点击监听
        * */
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Toast.makeText(TSDrawActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return false;
            }
        });
        drawerToggle.syncState();
        //设置DrawerLayout的抽屉开关监听
        drawerLayout.setDrawerListener(drawerToggle);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


}
