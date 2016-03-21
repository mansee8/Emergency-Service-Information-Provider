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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText username;
	private EditText password;
	private Button submitBtn;
	private TextView result;
	private TextView NewUserView;

	public static final String PREFS_NAME = "UserPrefsFile";
	SharedPreferences value;

	String usernameStr, passwordStr;
	String reply;

	private static final String SOAP_ACTION = "http://10.0.2.2:8080/soap/esipLoginService";
	private static final String OPERATION_NAME = "loginUser";
	private static final String WSDL_TARGET_NAMESPACE = "urn:esipLoginService";
	private static final String SOAP_ADDRESS = "http://10.0.2.2:8080/soap/servlet/rpcrouter";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		linkComponents();
	}

	private void linkComponents() {
		username = (EditText) findViewById(R.id.usernameEditText);
		password = (EditText) findViewById(R.id.passwordEditText);
		submitBtn = (Button) findViewById(R.id.submitButton);
		result = (TextView) findViewById(R.id.resultView);
		NewUserView = (TextView) findViewById(R.id.newUserView);

		submitBtn.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				loadValues();
			}
		});
		
		NewUserView.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, UserRegistration.class);
				startActivity(i);
			}
		});
	}

	private void loadValues() {
		usernameStr = username.getText().toString();
		passwordStr = password.getText().toString();
		reply = "";
		/*
		 * Intent i = new Intent(this, SoapProcess.class);
		 * 
		 * Bundle bun = new Bundle(); bun.putString("unamekey", usernameStr);
		 * bun.putString("passkey", passwordStr);
		 * 
		 * i.putExtras(bun);
		 * 
		 * startActivity(i);
		 */

		// TODO Auto-generated method stub
		XMLAsyncTask task1 = new XMLAsyncTask();
		task1.execute();
	}

	class XMLAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
					OPERATION_NAME);
			request.addProperty("username", usernameStr);
			request.addProperty("password", passwordStr);
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

			if (reply.equals("Authenticated")) {

				value = getSharedPreferences(PREFS_NAME, 0);
				SharedPreferences.Editor myEdit = value.edit();
				myEdit.putString("unameKey", usernameStr);
				myEdit.commit();

				Intent i = new Intent(MainActivity.this, UserMenu.class);
				startActivity(i);
			} else
				result.setText(reply);
		}
	}
}

/*
 * 
 * WSDL_TARGET_NAMESPACE : is targetNamespace in the WSDL.
 * 
 * SOAP_ADDRESS : The URL of WSDL file. In my case it is
 * "http://10.0.2.2:8080/soap/servlet/rpcrouter"
 * 
 * SOAP_ACTION : "soap service deployed"
 * 
 * OPERATION_NAME : WSDL operation. You can find something like <wsdl:operation
 * name="............."> in your WSDL.
 */

/*
 * 
 * Adding an array of complex objects to the request To get this xml:
 * 
 * <users> <user> <name>Jonh</name> <age>12</age> </user> <user>
 * <name>Marie</name> <age>27</age> </user> </users>
 * 
 * 
 * You would do this:
 * 
 * SoapObject users = new SoapObject(NAMESPACE, "users"); SoapObject john = new
 * SoapObject(NAMESPACE, "user"); john.addProperty("name", "john");
 * john.addProperty("age", 12); SoapObject marie = new SoapObject(NAMESPACE,
 * "user"); john.addProperty("name", "marie"); john.addProperty("age", 27);
 * users.addSoapObject(john); users.addSoapObject(marie);
 */