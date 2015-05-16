/**
 * 
 */
package com.mandar.kaleidoscope;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.*;
import android.os.Bundle;
import android.view.Window;

/**
 * @author Mandar
 *
 */
public class Kaleidoscope extends Activity {

	private GLSurfaceView m_Surface;

	public Kaleidoscope() {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	requestWindowFeature(Window.FEATURE_NO_TITLE);

		m_Surface = new GLSurfaceView(this);
		
		// Check if the system supports OpenGL ES 2.0.
		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager
				.getDeviceConfigurationInfo();
		final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

		if (supportsEs2) {
			// Request an OpenGL ES 2.0 compatible context.
			m_Surface.setEGLContextClientVersion(2);

			// Set the renderer to our demo renderer, defined below.
			m_Surface.setRenderer(new GLRenderer(this));
		} else {
			// This is where you could create an OpenGL ES 1.x compatible
			// renderer if you wanted to support both ES 1 and ES 2.
			return;
		}

		setContentView(m_Surface);

	}

	@Override
	protected void onPause() {
		super.onPause();
		m_Surface.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		m_Surface.onResume();
	}

}
