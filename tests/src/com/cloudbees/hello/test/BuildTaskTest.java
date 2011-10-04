package com.cloudbees.hello.test;

import android.app.ProgressDialog;
import android.test.ActivityInstrumentationTestCase2;

import com.cloudbees.hello.Hello;
import com.cloudbees.hello.task.BuildTask;

public class BuildTaskTest extends ActivityInstrumentationTestCase2<Hello> {

	private Hello helloActivity;
	
	public BuildTaskTest() {
        super("com.cloudbees.hello", Hello.class);
    }
	
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        
        helloActivity = getActivity();
    }
	
	/**
	 * Tests the building dialog
	 */
	public void testBuildingDialog() {
		final ProgressDialog progressDialog = new ProgressDialog(helloActivity);
		BuildTask buildTask = new BuildTask(helloActivity) {
						
			protected void showBuildingDialog() {
				buildingDialog = progressDialog;
				buildingDialog.show();
		    }
		};
		
		assertFalse(progressDialog.isShowing());
		
		buildTask.perform();
		
		assertTrue(progressDialog.isShowing());
	}
	
}
