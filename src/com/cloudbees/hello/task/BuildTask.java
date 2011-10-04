package com.cloudbees.hello.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.widget.Toast;

import com.cloudbees.hello.R;
import com.cloudbees.hello.task.TaskFactory.Task;

/**
 * A build Task
 */
public class BuildTask extends AsyncTask<Void, Void , Void> implements OnCancelListener, Task<Void> {
    
	/** Activity context */
	protected Context context;
	
	/** Progress dialog */
	protected ProgressDialog buildingDialog;
	
	public BuildTask(Context context) {
		super();
		this.context = context;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		try {
			Thread.sleep(5000); //The app is building...
		}
		catch (InterruptedException ex) {
			
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		buildingDialog.dismiss();
		showBuildMessage();
	}
	
	@Override
	public void onCancel(DialogInterface dialog) {
		cancel(true);
		dialog.dismiss();
		showCanceledMessage();
	}
	
	/**
     * Create a building progressDialog
     */
    protected void showBuildingDialog() {
    	buildingDialog = ProgressDialog.show(context, context.getString(R.string.app_name), context.getString(R.string.building), true, true);
    	buildingDialog.setOnCancelListener(this);
    }
	
	/**
	 * Shows a message when the app is build
	 */
	protected void showBuildMessage() {
    	Toast.makeText(context, context.getString(R.string.appBuild), Toast.LENGTH_LONG).show();
    }
    
	/**
	 * Shows a message when the build is canceled
	 */
    protected void showCanceledMessage() {
    	Toast.makeText(context, context.getString(R.string.buildCanceled), Toast.LENGTH_LONG).show();
    }

	@Override
	public void perform(Void... params) {
		showBuildingDialog();
		execute(params);
	}
}
