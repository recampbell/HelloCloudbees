package com.cloudbees.hello.test;

import java.lang.reflect.Field;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.cloudbees.hello.Hello;
import com.cloudbees.hello.R;
import com.cloudbees.hello.task.TaskFactory;
import com.cloudbees.hello.task.TaskFactory.Task;

/**
 * Tests Hello activity
 */
public class HelloTest extends ActivityInstrumentationTestCase2<Hello> {

    private Hello helloActivity;
    
    private Button buildButton;

    public HelloTest() {
        super("com.cloudbees.hello", Hello.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        
        helloActivity = getActivity();
        
        buildButton = (Button) helloActivity.findViewById(R.id.button);
        
        //Injects the mocked factory in the activity
        Field factory = helloActivity.getClass().getDeclaredField("taskFactory");
        factory.setAccessible(true);
        factory.set(helloActivity, mockTaskFactory);
    }

    /**
     * Tests the initial values of the build button
     */
    public void testPreconditions() {

        assertTrue(buildButton.isClickable());

        assertEquals(buildButton.getVisibility(), View.VISIBLE);
    }

    /**
     * Tests the click on the build button
     */
    public void testClick() {

    	buildPerformed = false;
    	
    	helloActivity.runOnUiThread(
            new Runnable() {
                public void run() {
                    buildButton.requestFocus();
                }
            }
        );

        this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);

        assertTrue(buildPerformed);
    }

    protected boolean buildPerformed = false;
    
    /**
     * Mocked build task
     */
    protected Task<Void> mockBuildTask = new Task<Void>() {

		@Override
		public void perform(Void... params) {
			buildPerformed = true;
		}
    };
    
    /**
     * Mocked tasks factory
     */
    protected TaskFactory mockTaskFactory = new TaskFactory() {

		@Override
		public Task<Void> getBuildTask() {
			return mockBuildTask;
		}
    };
    
}
