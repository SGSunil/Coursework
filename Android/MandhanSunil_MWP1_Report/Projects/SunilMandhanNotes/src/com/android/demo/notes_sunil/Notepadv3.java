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

package com.android.demo.notes_sunil;

import com.android.demo.notes_sunil.R;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.TextView;
import android.widget.TimePicker;

public class Notepadv3 extends ListActivity {
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;

    private static final int INSERT_ID = Menu.FIRST;
    private static final int DELETE_ID = Menu.FIRST + 1;
    private static final int EMAIL_ID = Menu.FIRST + 2;

    private Mod3DbAdapter mDbHelper;
    private String body ="";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list);
        mDbHelper = new Mod3DbAdapter(this);
        mDbHelper.open();
        fillData();
        registerForContextMenu(getListView());
    }

    private void fillData() {
        Cursor notesCursor = mDbHelper.fetchAllNotes();
        startManagingCursor(notesCursor);

        // Create an array to specify the fields we want to display in the list (only TITLE)
        String[] from = new String[]{Mod3DbAdapter.KEY_TITLE , Mod3DbAdapter.KEY_DATE , Mod3DbAdapter.KEY_TIME};

        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.text1, R.id.text2, R.id.text3 };

        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter notes = 
            new SimpleCursorAdapter(this, R.layout.notes_row, notesCursor, from, to);
        
        setListAdapter(notes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID, 0, R.string.menu_insert);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case INSERT_ID:
                createNote();
                return true;
        }

        return super.onMenuItemSelected(featureId, item);
    }

    
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) 
    {
    	
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Note operations");
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
        menu.add(1, EMAIL_ID, 0, R.string.menu_email);        
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        
        switch(item.getItemId()) {
            case DELETE_ID:
                mDbHelper.deleteNote(info.id);
                fillData();
                return true;
                
            case EMAIL_ID:
	              Cursor note = mDbHelper.fetchNote(info.id);
	              String text = "TITLE: " + note.getString(note.getColumnIndexOrThrow(Mod3DbAdapter.KEY_TITLE)) + "\n\n";
	              text += "BODY: " + note.getString(note.getColumnIndexOrThrow(Mod3DbAdapter.KEY_BODY))+ "\n\n" ;
	              text += "DATE: " + note.getString(note.getColumnIndexOrThrow(Mod3DbAdapter.KEY_DATE))+ "\n";
	              text += "TIME:" + note.getString(note.getColumnIndexOrThrow(Mod3DbAdapter.KEY_TIME));
	              
            	 Intent intent = new Intent(Intent.ACTION_SEND);
                 intent.setType("plain/text");
               
                 intent.putExtra(android.content.Intent.EXTRA_TEXT, text);

                 startActivity(intent);

                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void createNote() {
        Intent i = new Intent(this, NoteEdit.class);
        startActivityForResult(i, ACTIVITY_CREATE);
    }
    

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(this, NoteEdit.class);
        i.putExtra(Mod3DbAdapter.KEY_ROWID, id);
        startActivityForResult(i, ACTIVITY_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch(requestCode) 
        { 
        case ACTIVITY_EDIT:
        	fillData();
        	break;
       // case ACTIVITY_GET_BODY:
        	//break;
        }
    }
}
