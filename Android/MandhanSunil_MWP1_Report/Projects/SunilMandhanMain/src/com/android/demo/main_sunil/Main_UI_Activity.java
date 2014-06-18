package com.android.demo.main_sunil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.TextView;
import android.widget.TimePicker;

public class Main_UI_Activity extends Activity 
{
	private static final int ACTIVITY_CREATE=0;
	private static final int ACTIVITY_EDIT=1;
	private static final int ACTIVITY_MAP=2;

	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	private static final int EMAIL_ID = Menu.FIRST + 2;

	private Mod3DbAdapter mDbHelper;
	private String body ="";
	private Button mapButton = null;
	private Button notesButton = null;
	private Button emailButton = null;
	private EditText addressEditText = null;

	private TextView main_note_title_TextView = null;
	private TextView main_note_date_TextView = null;
	private TextView main_note_time_TextView = null;
	private TextView main_note_body_TextView = null;

	private TextView main_map_lattitude_TextView = null;
	private TextView main_map_longitude_TextView = null;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_ui);

		mDbHelper = new Mod3DbAdapter(this);
		mDbHelper.open();

		mapButton = (Button)findViewById(R.id.button_main_map1);
		notesButton = (Button)findViewById(R.id.Button_main_notes1);
		emailButton = (Button)findViewById(R.id.Button_main_email);

		addressEditText = (EditText)findViewById(R.id.editText_main_address1);

		main_map_lattitude_TextView = (TextView)findViewById(R.id.textView_main_map_lattitude);
		main_map_longitude_TextView = (TextView)findViewById(R.id.TextView_main_map_longitude);

		main_note_title_TextView = (TextView)findViewById(R.id.TextView_main_note_title);
		main_note_date_TextView = (TextView)findViewById(R.id.TextView_main_note_date);
		main_note_time_TextView = (TextView)findViewById(R.id.TextView_main_note_time);
		main_note_body_TextView  = (TextView)findViewById(R.id.TextView_main_note_body);

		mapButton.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) {
				invokeMaps(); 
			}

		});

		notesButton.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) {
				invokeNotes();
			}

		});

		emailButton.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) {
				invokeEmail(); 
			}

		});
	}

	private void invokeEmail()
	{
		String data = "";

		data += "Address: " + addressEditText.getText().toString() + "\n";

		data += main_map_lattitude_TextView.getText().toString() + "\n";
		data += main_map_longitude_TextView.getText().toString() + "\n";

		data += main_note_title_TextView.getText().toString() + "\n";
		data += main_note_date_TextView.getText().toString() + "\n";
		data += main_note_time_TextView.getText().toString() + "\n";
		data += main_note_body_TextView.getText().toString();

		boolean file_ok = true;
		File file   = null;
		File root   = Environment.getExternalStorageDirectory();
		if (root.canWrite())
		{
			File dir    =   new File (root.getAbsolutePath() + "/SunilMandhan");
			dir.mkdirs();
			file   =   new File(dir, "Data.txt");
			FileOutputStream out   =   null;
			try {
				out = new FileOutputStream(file);
			} 
			catch (FileNotFoundException e) 
			{
				file_ok = false;
			}
			try 
			{
				out.write(data.getBytes());
			} 
			catch (IOException e) 
			{
				file_ok = false;
			}
			try 
			{
				out.close();
			} 
			catch (IOException e) {
				file_ok = false;
			}

		}
		else
		{
			file_ok = false;
		}



		Intent intent = new Intent(Intent.ACTION_SEND);	  

		if(file_ok){
			Uri u1  =   null;
			u1 =   Uri.fromFile(file);
			intent.putExtra(Intent.EXTRA_STREAM, u1);
			intent.setType("text/html");
		}
		else
		{
			Toast.makeText(this, "file permission error, attachment failed, sending normal data in email body", Toast.LENGTH_LONG).show();
			intent.setType("plain/text");
			intent.putExtra(android.content.Intent.EXTRA_TEXT, data);
		}


		startActivity(intent);

	}

	private void invokeNotes()
	{
		Intent i = new Intent(this, Notepadv3.class);

		startActivityForResult(i, ACTIVITY_CREATE);
	}

	private void invokeMaps()
	{
		String location_addr = addressEditText.getText().toString();
		if(!location_addr.matches("[a-zA-Z,]*") || location_addr.isEmpty())
		{
			Toast.makeText(Main_UI_Activity.this, "please enter address in the format: city,country or city or country without space inbetween", Toast.LENGTH_SHORT).show();
			return;	
		}


		Intent i = new Intent(this, HelloMapActivity.class);
		i.putExtra("address", location_addr);

		startActivityForResult(i, ACTIVITY_MAP);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) 
	{
		super.onActivityResult(requestCode, resultCode, intent);
		switch(requestCode) 
		{ 
		case ACTIVITY_CREATE:
			if(resultCode == RESULT_OK)
			{
				String extraData= intent.getStringExtra("row_id");
				int row_id = Integer.parseInt(extraData);
				Cursor note = mDbHelper.fetchNote(row_id);

				main_note_title_TextView.setText("Title: " + note.getString(note.getColumnIndexOrThrow(Mod3DbAdapter.KEY_TITLE)));
				main_note_date_TextView.setText("Date: " + note.getString(note.getColumnIndexOrThrow(Mod3DbAdapter.KEY_DATE)));
				main_note_time_TextView.setText("Time: " + note.getString(note.getColumnIndexOrThrow(Mod3DbAdapter.KEY_TIME)));
				main_note_body_TextView.setText("Body: " + note.getString(note.getColumnIndexOrThrow(Mod3DbAdapter.KEY_BODY)));
			}
			break;

		case ACTIVITY_MAP:
			if(resultCode == RESULT_OK)
			{
				String lattitude = intent.getStringExtra("lattitude");
				String longitude = intent.getStringExtra("longitude");

				double lattitude_double = Integer.parseInt(lattitude);
				double longitude_double = Integer.parseInt(longitude);

				lattitude_double /= 1000000;
				longitude_double /= 1000000;

				main_map_lattitude_TextView.setText("lattitude: " + lattitude_double);
				main_map_longitude_TextView.setText("Longitude: " + longitude_double);
			}
			break;

		}
	}




}
