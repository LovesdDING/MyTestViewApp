package com.example.cz10000_001.mytestapp.view.camera;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cz10000_001.mytestapp.R;
import com.example.cz10000_001.mytestapp.view.PermissionUtils;

/**
 * 相机预览
 */
public class CameraActivity extends AppCompatActivity {
    private CameraView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);
        cv = (CameraView) findViewById(R.id.caview);

        PermissionUtils.askPermission(this,new String[]{Manifest.permission.CAMERA,Manifest
                .permission.WRITE_EXTERNAL_STORAGE},10,initViewRunnable);

        cv.switchCamera();
    }


    private Runnable initViewRunnable=new Runnable() {
        @Override
        public void run() {
            setContentView(R.layout.activity_camera);
            cv = (CameraView) findViewById(R.id.caview);
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        cv.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        cv.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("切换摄像头").setTitle("切换摄像头").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String name=item.getTitle().toString();
        if(name.equals("切换摄像头")){
            cv.switchCamera();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode == 10, grantResults, initViewRunnable,
                new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CameraActivity.this, "没有获得必要的权限", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
