package com.example.cz10000_001.mytestapp.aactivity;

import android.util.Log;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.shadows.ShadowLog;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 *
 * Created by cz10000_001 on 2018/2/26.
 */
public class GithubServiceTest {
    private static final String TAG = "GithubServiceTest";
    GithubService githubService;

    @Before
    public void setUp() throws URISyntaxException {
        //输出日志
        ShadowLog.stream = System.out;
        githubService = GithubService.Factory.create();
    }

    @Test
    public void publicRepositories() throws IOException {
        Call<List<Repository>> call = githubService.publicRepositories("geniusmart");
        Response<List<Repository>> execute = call.execute();

        List<Repository> list = execute.body();
        //输出完整的响应结果
        Log.i(TAG,new Gson().toJson(list));
        assertTrue(list.size() > 0);
        assertNotNull(list.get(0).name);
    }

}