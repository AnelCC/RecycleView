package byili.cherryapps.recyclerview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import byili.cherryapps.recyclerview.R;
import byili.cherryapps.recyclerview.adapters.Contact;
import byili.cherryapps.recyclerview.adapters.ContactsAdapter;

public class MainActivity extends AppCompatActivity {


    ArrayList<Contact> contacts;
    ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rv_contact);

        // Initialize contacts
        //contacts = Contact.createContactsList(20);
        contacts = Contact.createContactsList(5);
        contacts.addAll(Contact.createContactsList(5));
        adapter = new ContactsAdapter(this, contacts);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.action_add:
                addItem();
                break;
            case R.id.action_refresh:
                refresh();
                break;
            case R.id.action_delete:
                break;
        }
        return true;
    }

    public void addItem(){
        contacts.add(0, new Contact("Barney", true));
        adapter.notifyItemInserted(0);
    }

    public void refresh(){
        int curSize = adapter.getItemCount();
        ArrayList<Contact> newItems = Contact.createContactsList(20);
        contacts.addAll(newItems);
        adapter.notifyItemRangeInserted(curSize, newItems.size());
    }
}
