package com.avengergear;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.FrameLayout;

public class CatchMyMoJo extends Activity
{

    private Camera mCamera; 
    private CatchMyMoJoPreview mPreview; 

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

	mCamera = getCameraInstance();

	mPreview = new CatchMyMoJoPreview(this, mCamera); 
	FrameLayout preview = (FrameLayout) findViewById(R.id.catchmymojo_preview); 
	preview.addView(mPreview); 
    }

    public static Camera getCameraInstance(){ 
	    Camera cam = null; 
	    try { 
		    cam = Camera.open();
		    Camera.Parameters parameters = cam.getParameters();
		    parameters.set("camera-id", 2); // open the front cam
		    cam.setParameters(parameters);
	    } catch (Exception e){
		    // shit no cam, exit
	    }
	    return cam; 
    }
    
}
