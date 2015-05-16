//package com.mandar.kaleidoscope;
//
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//import java.nio.FloatBuffer;
//
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.microedition.khronos.opengles.GL10;
//
//import android.opengl.GLES20;
//import android.opengl.GLUtils;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//
//public class Shape 
//{
//	
//	 static float shapeCoords[] = { // in counterclockwise order:
//         0.0f,  1f, 0.0f,   // top
//        -0.5f, -0.3f, 0.0f,   // bottom left
//         0.5f, -0.3f, 0.0f    // bottom right
//    };
//	 
//	 public final FloatBuffer mbuff_vertex; 
//	 
//	 private final String vertexShaderCode =
//		        "attribute vec4 vPosition;" +
//		         "attribute vec2 tex;" +
//		        "void main() {" +
//		        "  gl_Position = vPosition;"
//		        + " v_TexCoordinate = tex;" +
//		        "}";
//
//		    private final String fragmentShaderCode =
//		        "precision mediump float;" +
//		        "uniform vec4 vColor;"
//		        + "varying vec2 v_TexCoordinate;"
//		        + "uniform sampler2D tex; " +
//		        "void main() {" +
//		        "     gl_FragColor =texture2D(tex, v_TexCoordinate); "+//texture2D(tex, TexCoord);"+// gl_FragColor = vColor;" +
//		        "}";
//
//		    private final FloatBuffer vertexBuffer=null;
//		    private int mProgram=0;
//		    private int mPositionHandle=0;
//		    private int mPositionHandle2=0;
//		    private int mColorHandle=0;
//			  List ax = new ArrayList();
//			  List ay = new ArrayList();
//		    float[] color={1,0,0,1};
//	 
//		    float[] m = new float[72];
//		    float[] t = new float[6];
//		    Bitmap bmpimg;
//		    
//		    /** The texture pointer */
//		    private int[] textures = new int[1];
//		    private FloatBuffer textureBuffer;  // buffer holding the texture coordinates
//
//		    
//	public Shape() 
//	{
//		
//
//		
//		int j=0;
//		  for (int i=0;i<360;i=i+15)
//		  {
//			  ax.add( (java.lang.Math.cos(i* Math.PI / 180F)*1f));
//			  ay.add((java.lang.Math.sin(i* Math.PI / 180F)*1f));
//			  
//			  m[j]=(float) ( java.lang.Math.cos(i* Math.PI / 180F)*1f); j++;
//			  m[j]=(float) (java.lang.Math.sin(i* Math.PI / 180F)*1f); j++;
//			  m[j]=0; j++;
//		  }  
//	      
//
//		
//		// use vertex byte buffer
//		ByteBuffer bb = ByteBuffer.allocateDirect(m.length*4);
//		
//		// use native byte order
//		bb.order(ByteOrder.nativeOrder()); // little/big endian 
//		
//		mbuff_vertex = bb.asFloatBuffer();	// Make Space
//		mbuff_vertex.put(m);		// fill Coord
//		mbuff_vertex.position(0);			// set first coord
//		
//		// Attach Shaders
//		   // prepare shaders and OpenGL program
//        int vertexShader = GLRenderer.LoadShader(GLES20.GL_VERTEX_SHADER,vertexShaderCode);
//        int fragmentShader = GLRenderer.LoadShader(GLES20.GL_FRAGMENT_SHADER,fragmentShaderCode);
//
//        mProgram = GLES20.glCreateProgram();             // create empty OpenGL Program
//        GLES20.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
//        GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
//        GLES20.glLinkProgram(mProgram);                  // create OpenGL program executables
//        
//
//
//	}
//	 public void loadGLTexture(GL10 gl, Context context) 
//	 {
//		 try {
//			 bmpimg = BitmapFactory.decodeStream( context.getAssets().open("test2.jpg"));
//			 
//			 float centerx =0.5f;
//			 float centery =1f;
//			 
//			 float edgex =0.5f;
//			 float edgey =0f;
//			 
//			 int k=0;
//			 
//			 t[k] = edgex; k++;
//			 t[k] = edgey; k++;
//			 
//			 t[k] = centerx; k++;
//			 t[k] = centery; k++;
//			 	  
//			 t[k]= ((float) ( java.lang.Math.cos(255* Math.PI / 180F)))+centerx; k++;
//			 t[k]=((float) (java.lang.Math.sin(255* Math.PI / 180F)))+centery; k++;
//			 
//			 ByteBuffer byteBuffer= ByteBuffer.allocateDirect(t.length * 4);
//			 byteBuffer.order(ByteOrder.nativeOrder());
//			 textureBuffer = byteBuffer.asFloatBuffer();
//			 textureBuffer.put(t);
//			 textureBuffer.position(0);
//
//			 
//			 GLES20.glEnable(GLES20.GL_TEXTURE_2D);
//				
//			 gl.glGenTextures(1, textures, 0);
//			     // ...and bind it to our array
//			 gl.glBindTexture(GLES20.GL_TEXTURE_2D, textures[0]);
//			     // create nearest filtered texture
//			 gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
//			 gl.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
//			 // Use Android GLUtils to specify a two-dimensional texture image from our bitmap 
//			 GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmpimg, 0);
//			      
//			     // Clean up
//			 bmpimg.recycle();
//
//			 
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	 }
//	   float triangleVertices[] = {
//		        100,   100,
//		        150,  0,
//		        200,   100
//		    };
//	   
//	   float vertexcoords[] = {       //square
//				-2.0f, -1.0f,  0.0f,
//				 0.0f,  1.0f,  0.0f,
//				-2.0f,  1.0f,  0.0f,
//				-2.0f, -1.0f,  0.0f,
//				 0.0f, -1.0f,  0.0f,
//				 0.0f,  1.0f,  0.0f,};
//	   float texcoords[] = { //square
//				0.0f,  0.0f,
//				1.0f,  1.0f,
//				0.0f,  1.0f,
//				0.0f,  0.0f,
//				1.0f,  0.0f,
//				1.0f,  1.0f, };
//
//	   public FloatBuffer f1;
//
//	public FloatBuffer f2;
//	   
//	public void DrawShape()
//	{
//
//		try
//		{
//			
//			 GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
//			
//			GLES20.glUseProgram(mProgram);
//			mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
//
//			GLES20.glEnableVertexAttribArray(0);
//			GLES20.glEnableVertexAttribArray(textures[0]);
//			
//			
//			ByteBuffer bb = ByteBuffer.allocateDirect(vertexcoords.length*4);			
//			bb.order(ByteOrder.nativeOrder()); // little/big endian 
//			f1 = bb.asFloatBuffer();	// Make Space
//			f1.put(vertexcoords);		// fill Coord
//			f1.position(0);			// set first coord
//			
//			
//			   GLES20.glVertexAttribPointer(0, 2, GLES20.GL_FLOAT, false, 0, f1);
//
//		   
//				ByteBuffer bb1 = ByteBuffer.allocateDirect(texcoords.length*4);			
//				bb1.order(ByteOrder.nativeOrder()); // little/big endian 
//				f2 = bb1.asFloatBuffer();	// Make Space
//				f2.put(texcoords);		// fill Coord
//				f2.position(0);			// set first coord
//				
//		    GLES20.glVertexAttribPointer(1, 2, GLES20.GL_FLOAT, false, 0, f2);
//
//		    
//			mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");			
//			// Set color for drawing the triangle
//	        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
//	        
//		   	        
////	        GLES20.glUniform1i(GLES20.glGetUniformLocation(mProgram, "tex"), 0);
//		    
//		    // Draws a short triangle strip
//		    GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
//			
////			GLES20.glVertexAttribPointer(mPositionHandle,3 , GLES20.GL_FLOAT, false, 3*4, mbuff_vertex);
////			
////			GLES20.glVertexAttribPointer(0,2 , GLES20.GL_FLOAT, false, 3*4, mbuff_vertex);
////			mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");			
////			
////	
////			// Set color for drawing the triangle
////	        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
////	        
////	        // Draw the triangle
////	        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, (m.length/3));
////
////	        // Disable vertex array
////	        GLES20.glDisableVertexAttribArray(mPositionHandle);
////	        
//		}catch(Exception e)
//		{
//			
//		}
//	}
//	
//}
