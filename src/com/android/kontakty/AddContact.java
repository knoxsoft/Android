package com.android.kontakty;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Intents.Insert;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class AddContact extends Activity implements OnClickListener{
	EditText name;
	EditText phone;
	EditText email;
	Button buttonDodaj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addcontactlayout);
		
		name = (EditText)findViewById(R.id.name);
		phone = (EditText)findViewById(R.id.phone);
		email = (EditText)findViewById(R.id.email);
		buttonDodaj = (Button)findViewById(R.id.buttonDodaj);
		
		buttonDodaj.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		addContactByAL(name.getText().toString(), phone.getText().toString(), email.getText().toString());
	}

	public void addContactByIntent() {
		Intent i = new Intent(Intent.ACTION_INSERT_OR_EDIT);
		i.setType(Contacts.CONTENT_ITEM_TYPE);
		i.putExtra(Insert.NAME, "TESTTEST");
		i.putExtra(Insert.PHONE, "209384");
		startActivity(i);
		
		///////// or
		
		Intent ii = new Intent(Intent.ACTION_INSERT);
		ii.setType(Contacts.CONTENT_TYPE);
		ii.putExtra(Insert.NAME, "Some Contact Name");
		ii.putExtra(Insert.EMAIL, "address@email.com");
		ii.putExtra(Insert.PHONE, "123-456-7890");
		this.startActivity(ii);
	}

	public void addContactByAL(String name,String phone, String email){
		ArrayList op_list = new ArrayList();
		op_list.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
		.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
		.withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
		.build());
		  
		op_list.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
		.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
		.withValue(ContactsContract.Data.MIMETYPE,  ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
		.withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
		.build());

		op_list.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
		.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)  .withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
		.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
		.build());
		  
		op_list.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
		 .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
		 .withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
		 .withValue(ContactsContract.CommonDataKinds.Email.DATA, email)
		    .build());

		try {
		  ContentProviderResult[] results = getContentResolver().applyBatch(ContactsContract.AUTHORITY, op_list);
		  Toast.makeText(this, "Udalo sie dodac nowego uzytkownika pomyslnie", Toast.LENGTH_LONG).show();
		} catch(Exception e){
			Toast.makeText(this, "Nie udalo sie dodac nowego uzytkownika ;/", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	
}
