package com.avengergear;

import android.app.Activity;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

public class CatchMyMoJo extends Activity
{
	private static final String TAG = "CatchMyMoJo:";
	private Camera mCamera;
	private Parameters mCamParameters;
	private CatchMyMoJoPreview mPreview;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mCamera = getCameraInstance();
		getCameraParameters();
		
		mPreview = new CatchMyMoJoPreview(this, mCamera);
		FrameLayout preview = (FrameLayout) findViewById(R.id.catchmymojo_preview);
		preview.addView(mPreview);
	}

	public static Camera getCameraInstance(){
		Camera cam = null;
		Log.d(TAG, " How many camera you get? " + Camera.getNumberOfCameras());
		try{
			cam = Camera.open(Camera.getNumberOfCameras()-1); // open the front cam
		} catch (Exception e){
			// shit no cam, exit
			Log.e(TAG, " Camera is ready! Exiting :" + e.getMessage());
		}
		return cam;
	}

	public void getCameraParameters(){
		if(mCamera != null){
			mCamParameters = mCamera.getParameters();
			for( Camera.Size size : mCamParameters.getSupportedPreviewSizes()) {
				Log.d(TAG, " Supported Sizes:" + size.width + "x" + size.height );
			}
		}
	}
}
