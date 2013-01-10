package com.avengergear;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import java.io.IOException;

public class CatchMyMoJoPreview extends SurfaceView implements SurfaceHolder.Callback {
	private SurfaceHolder mSurfaceHolder;
	private Camera mCamera;

	public CatchMyMoJoPreview(Context context, Camera cam){
		super(context);
		mCamera = cam;

		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		//mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public void surfaceCreated(SurfaceHolder holder){
		if (this.getResources().getConfiguration().orientation !=Configuration.ORIENTATION_LANDSCAPE){
			mCamera.setDisplayOrientation(90);
		} else {
			mCamera.setDisplayOrientation(0);
		}
		try{
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		} catch (IOException e){
			// shit can't preview!
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder){
		// don't care for now 
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h){
		if(mSurfaceHolder.getSurface() == null){
			// hack it is not here.. why?
			return;
		}
		try{
			mCamera.stopPreview();
		} catch (Exception e){
			// don't care
		}
		try{
			mCamera.setPreviewDisplay(mSurfaceHolder);
			mCamera.startPreview();
		} catch (Exception e){
			// shit..... preview just won't start
		}
	}
}
