package com.example.cz10000_001.mytestapp.aactivity;

import android.app.Activity;
import android.test.AndroidTestCase;
import android.widget.Button;
import android.widget.EditText;

import com.example.cz10000_001.mytestapp.MyApp;
import com.example.cz10000_001.mytestapp.R;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Created by cz10000_001 on 2018/2/26.
 */
public class LoginActivityTest extends AndroidTestCase{
    private EditText emailView;
    private EditText passwordView;
    private Button button;
    private Activity activity;

    @Before
    public void setUp() {

         activity = Robolectric.setupActivity(LoginActivity.class);
        button = (Button) activity.findViewById(R.id.email_sign_in_button);
        emailView = (EditText) activity.findViewById(R.id.email);
        passwordView = (EditText) activity.findViewById(R.id.password);
    }

    /**
     * 测试成功的通行证
     */
    @Test
    public void loginSuccess() {
        emailView.setText("447081738@qq.com");
        passwordView.setText("123456");
        button.performClick();

        MyApp application = MyApp.getInstance();
        assertThat("Next activity has started", application.getNextStartedActivity(), is(notNullValue()));
    }

    /**
     * 测试账户号和密码为空时的处理
     */
    @Test
    public void loginWithEmptyUsernameAndPassword() {
        button.performClick();

        MyApp application = MyApp.getInstance();
        assertThat("Next activity should not started", application.getNextStartedActivity(), is(nullValue()));
        assertThat("Show error for Email field ", emailView.getError(), is(notNullValue()));
        assertThat("Show error for Password field ", passwordView.getError(), is(notNullValue()));

        assertEquals(emailView.getError().toString(), activity.getString(R.string.error_field_required));
    }

    /**
     * 测试无效的通行证
     */
    @Test
    public void loginFailure() {
        emailView.setText("invalid@email");
        passwordView.setText("invalidpassword");
        button.performClick();

        MyApp application = MyApp.getInstance();
        assertThat("Next activity should not started", application.getNextStartedActivity(), is(nullValue()));
        assertThat("Show error for Email field ", emailView.getError(), is(notNullValue()));
        assertThat("Show error for Password field ", passwordView.getError(), is(notNullValue()));


    }


}