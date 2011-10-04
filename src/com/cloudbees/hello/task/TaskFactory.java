package com.cloudbees.hello.task;

/**
 * Tasks factory
 */
public interface TaskFactory {

	Task<Void> getBuildTask();
	
	/**
	 * Task
	 */
	public static interface Task<Params> {

		void perform(Params...params );
		
	}
	
}
