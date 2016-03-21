package project.esip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GroupActivity extends Activity {

	Button BtnCreateGroup;
	Button BtnViewGroups;
	Button BtnSelectGroup;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.group_menu);

		BtnCreateGroup = (Button) findViewById(R.id.btnCreateGroup);
		BtnViewGroups = (Button) findViewById(R.id.btnViewGroups);
		BtnSelectGroup = (Button) findViewById(R.id.btnSelectGroup);

		BtnCreateGroup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GroupActivity.this, CreateGroup.class);
				startActivity(i);
			}
		});

		BtnViewGroups.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GroupActivity.this, ViewGroups.class);
				startActivity(i);
			}
		});

		BtnSelectGroup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(GroupActivity.this, SelectGroup.class);
				startActivity(i);
			}
		});

	}
}
