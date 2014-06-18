/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.demo.main_sunil;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class NoteEdit extends Activity {
	private static final int ACTIVITY_CREATE=0;

	private EditText mTitleText;
	private EditText mBodyText;
	private Long mRowId;
	private Mod3DbAdapter mDbHelper;
	private DatePicker datepicker;
	private TimePicker timepicker;
	private Button confirmButton;
	private Button dateButton;
	private Button timeButton;

	private int mYear;
	private int mMonth;
	private int mDay;

	private int mHour;
	private int mMinute;
	private int mSecond;

	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDbHelper = new Mod3DbAdapter(this);
		mDbHelper.open();

		setContentView(R.layout.note_edit);
		setTitle(R.string.edit_note);

		mTitleText = (EditText) findViewById(R.id.title);
		mBodyText = (EditText) findViewById(R.id.body);


		confirmButton = (Button) findViewById(R.id.confirm);
		dateButton = (Button) findViewById(R.id.button1);
		timeButton = (Button) findViewById(R.id.button2);

		mRowId = (savedInstanceState == null) ? null :
			(Long) savedInstanceState.getSerializable(Mod3DbAdapter.KEY_ROWID);
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(Mod3DbAdapter.KEY_ROWID)
					: null;
		}

		populateFields();

		confirmButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				setResult(RESULT_OK);
				finish();
			}

		});

		dateButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});


		timeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		});

		//        // get the current date
		//        final Calendar c = Calendar.getInstance();
		//        mYear = c.get(Calendar.YEAR);
		//        mMonth = c.get(Calendar.MONTH);
		//        mDay = c.get(Calendar.DAY_OF_MONTH);
		//        
		//        mHour = c.get(Calendar.HOUR);
		//        mMinute = c.get(Calendar.MINUTE);
		//        mSecond = c.get(Calendar.SECOND);

		//updateDisplay();
	}

	private void updateDisplay() {
		dateButton.setText(
				new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth).append("-")
				.append(mDay).append("-")
				.append(mYear).append(" "));

		//        timeButton.setText(
		//                new StringBuilder()
		//                        .append(pad(mHour)).append(":")
		//                        .append(pad(mMinute)));

		timeButton.setText(
				new StringBuilder()
				.append(mHour).append(":")
				.append(mMinute));
	}

	private static String pad(int c) {
		//if (c >= 10)
		return String.valueOf(c);
		//else
		// return "0" + String.valueOf(c);
	}

	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener =
			new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear + 1;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
			new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			updateDisplay();
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this,
					mDateSetListener,
					mYear, mMonth - 1, mDay);
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this,
					mTimeSetListener, mHour, mMinute, false);

		}
		return null;
	}


	private void populateFields() {
		if (mRowId != null) {
			Cursor note = mDbHelper.fetchNote(mRowId);
			startManagingCursor(note);
			mTitleText.setText(note.getString(
					note.getColumnIndexOrThrow(Mod3DbAdapter.KEY_TITLE)));
			mBodyText.setText(note.getString(
					note.getColumnIndexOrThrow(Mod3DbAdapter.KEY_BODY)));

			//Then, you can get all the date fields you want, like, for example:
				String date = note.getString(note.getColumnIndexOrThrow(Mod3DbAdapter.KEY_DATE)); 
			String time =  note.getString(note.getColumnIndexOrThrow(Mod3DbAdapter.KEY_TIME));   // Current time
			timeButton.setText(time);
			dateButton.setText(date);

			String[] date_splitted = date.split("-");
			String[] time_splitted = time.split(":");

			mYear = Integer.parseInt(date_splitted[0]);
			mMonth = Integer.parseInt(date_splitted[1]);
			mDay = Integer.parseInt(date_splitted[2]);

			mHour = Integer.parseInt(time_splitted[0]);
			mMinute = Integer.parseInt(time_splitted[1]);

		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(Mod3DbAdapter.KEY_ROWID, mRowId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateFields();
	}

	private void saveState() {
		String title = mTitleText.getText().toString();
		String body = mBodyText.getText().toString();

		//Then, you can get all the date fields you want, like, for example:
		String date = mYear + "-" + mMonth + "-" + mDay;  // Current date
		String time = mHour + ":" + mMinute;  // Current time

		if (mRowId == null) {


			long id = mDbHelper.createNote(title, body, date, time);
			if (id > 0) {
				mRowId = id;
			}
		} else {
			mDbHelper.updateNote(mRowId, title, body, date, time);
		}
	}

}
