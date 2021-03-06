package com.example.esens.observablesample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.esens.observablesample.Fragment.ObserverFragment;
import com.example.esens.observablesample.Fragment.SecondFragment;
import com.example.esens.observablesample.Fragment.itemFragment;
import com.example.esens.observablesample.dummy.DummyContent;
import com.example.esens.observablesample.inteface.Observer;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements itemFragment.OnListFragmentInteractionListener {
    public Observable<String> simpleObservable;
    private ObserverFragment observerFragment;
    private static final String TAG = "ESNES::"+MainActivity.class.getSimpleName();

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        final String stringItem = item.content;
        observerFragment.notifyObservers();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        Fragment itemfragment = new itemFragment();

        observerFragment = new ObserverFragment();
        observerFragment.register((Observer)itemfragment);
        observerFragment.register(new SecondFragment());

        manager.beginTransaction().replace(R.id.container,itemfragment).commit();

    }
}
