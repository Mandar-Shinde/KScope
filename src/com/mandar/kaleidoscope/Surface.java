package com.mandar.kaleidoscope;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class Surface extends GLSurfaceView 
{

	public Surface(Context context) 
	{
		super(context);	
		
		// Creating OpenGL ES2.0 Version Context
		setEGLContextClientVersion(2);
		
		// Creating a Renderer
		setRenderer(new GLRenderer(context));		
		setRenderMode(RENDERMODE_WHEN_DIRTY);
	}

	public Surface(Context context, AttributeSet attrs) 
	{
		super(context, attrs);	
		// Creating OpenGL ES2.0 Version Context
				setEGLContextClientVersion(2);
				
				// Creating a Renderer
				setRenderer(new GLRenderer(context));		
				setRenderMode(RENDERMODE_WHEN_DIRTY);
	}
	
}
