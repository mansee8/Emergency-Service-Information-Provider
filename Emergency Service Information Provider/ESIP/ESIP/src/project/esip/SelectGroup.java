package project.esip;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class SelectGroup extends Activity {

	Button BtnMaps;
	Button BtnGroups;
	Button BtnContact;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_us);
	}
}
