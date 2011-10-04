package com.cloudbees.hello.task;

import android.content.Context;

/**
 * Default tasks factory
 */
public class DefaultTaskFactory implements TaskFactory {

	/** Activity context */
	protected Context context;
	
	public DefaultTaskFactory(Context context) {
		this.context = context;
	}
	
	@Override
	public Task<Void> getBuildTask() {
		return new BuildTask(context);
	}

}
