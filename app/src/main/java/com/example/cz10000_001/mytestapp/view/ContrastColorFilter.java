package com.example.cz10000_001.mytestapp.view;

import android.content.Context;
import android.opengl.GLES20;

/**
 * Created by cz10000_001 on 2018/1/29.
 */

public class ContrastColorFilter extends AFilter{
    private ColorFilter.Filter filter;

    private int hChangeType;
    private int hChangeColor;

    public ContrastColorFilter(Context context, ColorFilter.Filter filter) {
        super(context, "filter/half_color_vertex.sh", "filter/half_color_fragment.sh");
        this.filter=filter;
    }

    @Override
    public void onDrawSet() {
        GLES20.glUniform1i(hChangeType,filter.getType());
        GLES20.glUniform3fv(hChangeColor,1,filter.data(),0);
    }

    @Override
    public void onDrawCreatedSet(int mProgram) {
        hChangeType=GLES20.glGetUniformLocation(mProgram,"vChangeType");
        hChangeColor=GLES20.glGetUniformLocation(mProgram,"vChangeColor");
    }


}
