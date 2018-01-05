package com.example.cz10000_001.mytestapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cz10000_001.mytestapp.view.CollapseView;

public class BActivity extends AppCompatActivity {

    private static final String TAG = BActivity.class.getSimpleName();

    private CollapseView collapseView ;
    private Button btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);


        collapseView = (CollapseView) findViewById(R.id.collav);
        btn  = (Button) findViewById(R.id.bt_concact);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose() ;
            }
        });

        collapseView.setNumber("1");
//        collapseView.setContent("夜空中最亮的星照亮我前行");
        collapseView.setTitle("夜空中最亮的星");
        collapseView.setContent(R.layout.content);
    }

    private void choose() {
        //点击选择通讯录
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示：").setMessage("确定从通讯录选择联系人？").setCancelable(true)
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        startActivityForResult(new Intent(
                                Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
                    }
                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

//    public void changeC(View view) {
//        startActivity(new Intent(this,CActivity.class));

//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            ContentResolver reContentResolverol = getContentResolver();
            Uri contactData = data.getData();
            @SuppressWarnings("deprecation")
            Cursor cursor = managedQuery(contactData, null, null, null, null);
            cursor.moveToFirst();
            String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null,
                    null);
            while (phone.moveToNext()) {
                String usernumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                Log.e(usernumber+" ("+username+")");
                Log.e(TAG, "onActivityResult: "+username+",,"+usernumber);
//                cInviteeUsername.setText(username);
//                cInviteeTel.setText(usernumber);
            }

        }

    }
}




