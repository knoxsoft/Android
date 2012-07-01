package com.android.kontakty;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemClickListener {
	SimpleCursorAdapter adapter;
	ListView listaKontaktow;
	TextView rowText;
	String[] cos;
	
  

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaKontaktow = (ListView)findViewById(R.id.listaKontaktow);

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[] {Phone._ID, Phone.DISPLAY_NAME, Phone.NUMBER}, null, null, null);

        //startManagingCursor(cursor);
        
        String[] from = new String[] { Phone.DISPLAY_NAME};
        int[] to = new int[] { R.id.layoutName };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.rowfromlist,cursor,from,to);
        
        listaKontaktow.setAdapter(adapter);
        //cos = (String[])adapter.getItem(0);
        listaKontaktow.setOnItemClickListener(this);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.menuDodaj:
				startActivity( new Intent(this,AddContact.class));
			break;
		
		}
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		//listaKontaktow.getId()
//		String[] cos = (String[])listaKontaktow.getAdapter().getItem(arg2);
//		String[] tmp = (String[]) arg0.getItemAtPosition(arg2);
		//rowText = (TextView)findViewById(R.id.textView1);
		//TextView rowText2 = (TextView)arg0.getCount();
	
		//long[] map = listaKontaktow.getCheckItemIds();
		//TextView rowText2 = (TextView)arg0.getItemAtPosition(arg2);
		//String s = arg0.getItemAtPosition(arg2).toString();
		//rowText.setText(s);	
//		rowText.setText(cos[0]);	
	}
	



	

    
}
