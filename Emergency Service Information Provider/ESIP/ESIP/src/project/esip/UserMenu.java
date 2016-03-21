package project.esip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class UserMenu extends Activity {

	Button BtnMaps;
	Button BtnGroups;
	Button BtnContact;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_menu);

		BtnMaps = (Button) findViewById(R.id.btnMap);
		BtnGroups = (Button) findViewById(R.id.btnGroup);
		BtnContact = (Button) findViewById(R.id.btnContact);

		BtnMaps.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(UserMenu.this, MyMapActivity.class);
				startActivity(i);
			}
		});

		BtnGroups.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(UserMenu.this, GroupActivity.class);
				startActivity(i);
			}
		});

		BtnContact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(UserMenu.this, ContactUs.class);
				startActivity(i);
			}
		});
	}
}
