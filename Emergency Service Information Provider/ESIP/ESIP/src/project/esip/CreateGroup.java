package project.esip;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateGroup extends Activity {

	Button BtnCreate;
	EditText GroupName;
	TextView AvailView;

	String reply, groupNameStr, userName;

	public static final String PREFS_NAME = "UserPrefsFile";
	SharedPreferences value;

	private static final String SOAP_ACTION = "http://10.0.2.2:8080/soap/esipCreateGroupService";
	private static final String OPERATION_NAME = "checkAvailibility";
	private static final String WSDL_TARGET_NAMESPACE = "urn:esipCreateGroupService";
	private static final String SOAP_ADDRESS = "http://10.0.2.2:8080/soap/servlet/rpcrouter";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_group);

		BtnCreate = (Button) findViewById(R.id.btnCreate);
		GroupName = (EditText) findViewById(R.id.GroupNameView);
		AvailView = (TextView) findViewById(R.id.avail_result);

		value = getSharedPreferences(PREFS_NAME, 0);
		userName = value.getString("unameKey", "Unknown");

		System.out.println("Username in CreateGroup: " + userName);

		BtnCreate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				groupNameStr = GroupName.getText().toString();
				System.out.println("Group Name: " + groupNameStr);

				if (groupNameStr.equals("")) {
					Toast.makeText(CreateGroup.this,
							"Please enter the group name!", Toast.LENGTH_SHORT)
							.show();
				} else {
					XMLAsyncTask task1 = new XMLAsyncTask();
					task1.execute();
				}
			}
		});
	}

	class XMLAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			System.out.println("Inside doInBackground");
			SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
					OPERATION_NAME);
			request.addProperty("groupName", groupNameStr);
			request.addProperty("CreatedBy", userName);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = false;
			envelope.setOutputSoapObject(request);
			HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);

			try {
				// httpTransport.debug = true;
				httpTransport.call(SOAP_ACTION, envelope);
				// textView.setText("=========" + httpTransport.responseDump);
				Object response = envelope.getResponse();
				// result.setText("" + response);
				System.out.println("Response: " + response);
				reply = "" + response;

			}

			catch (Exception exception) {
				reply = "" + exception;
				System.out.println(reply);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void res) {
			super.onPostExecute(res);
			System.out.println("Reply: " + reply);
			if (reply.equals("Success")) {
				Toast.makeText(getApplicationContext(), "Group Created",
						Toast.LENGTH_SHORT).show();

				Intent i = new Intent(CreateGroup.this, GroupActivity.class);
				startActivity(i);
			} else
				AvailView.setText("Group Name Not Available");
		}
	}
}
