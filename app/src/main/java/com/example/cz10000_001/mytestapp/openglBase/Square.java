package com.example.cz10000_001.mytestapp.openglBase;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 *
 * 画正方形
 * Created by cz10000_001 on 2017/11/3.
 */

public class Square {
    private float vertices[] = {
         -1.0f, 1.0f, 0.0f, // 0, Top Left
                 -1.0f, -1.0f, 0.0f, // 1, Bottom Left
                 1.0f, -1.0f, 0.0f, // 2, Bottom Right
                 1.0f, 1.0f, 0.0f, // 3, Top Right
                 };



    private short[] indices = { 0, 1, 2, 0, 2, 3};

    private FloatBuffer vertexBuffer;
    private ShortBuffer indexBuffer;

    public Square(){
         ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
         vbb.order(ByteOrder.nativeOrder());
         vertexBuffer = vbb.asFloatBuffer();
         vertexBuffer.put(vertices);
         vertexBuffer.position(0);


        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
         ibb.order(ByteOrder.nativeOrder());
         indexBuffer = ibb.asShortBuffer();
         indexBuffer.put(indices);
         indexBuffer.position(0);


    }


    public void draw(GL10 gl){

         gl.glFrontFace(GL10.GL_CCW);
         // Enable face culling.
         gl.glEnable(GL10.GL_CULL_FACE);
         // What faces to remove with the face culling.
         gl.glCullFace(GL10.GL_BACK);

         // Enabled the vertices buffer for writing
         //and to be used during
         // rendering.
         gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);


        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

         // Disable the vertices buffer.
         gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
         // Disable face culling.
         gl.glDisable(GL10.GL_CULL_FACE);
    }
}
