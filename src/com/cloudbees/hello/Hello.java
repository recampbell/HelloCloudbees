package com.cloudbees.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.cloudbees.hello.task.DefaultTaskFactory;
import com.cloudbees.hello.task.TaskFactory;
import com.cloudbees.hello.task.TaskFactory.Task;

/**
 * The main activity
 */
public class Hello extends Activity {

	protected TaskFactory taskFactory;
	
	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		taskFactory = new DefaultTaskFactory(this);
	}

	/**
	 * Called when the build button is clicked
	 * @param view Button view
	 */
	public void build(View view) {
		Task<Void> task = taskFactory.getBuildTask();
		task.perform();
	}

}