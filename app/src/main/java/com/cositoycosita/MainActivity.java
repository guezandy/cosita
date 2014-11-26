package com.cositoycosita;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cositoycosita.fragment.AlbumFragment;
import com.cositoycosita.fragment.HomeFragment;
import com.cositoycosita.fragment.MapFragment;
import com.cositoycosita.fragment.WhatsappFragment;
import com.parse.ParseObject;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Tag to help in debugging
     */
    private final String TAG = MainActivity.class.getSimpleName();
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        switch(position) {
            case 0:
                getSupportFragmentManager().popBackStackImmediate();
                replaceFragment(new HomeFragment(), true, FragmentTransaction.TRANSIT_FRAGMENT_FADE, getString(R.string.home));
                break;
            case 1:
                replaceFragment(new AlbumFragment(), true, FragmentTransaction.TRANSIT_FRAGMENT_FADE, getString(R.string.albums));
                break;
            case 2:
                replaceFragment(new WhatsappFragment(), true, FragmentTransaction.TRANSIT_FRAGMENT_FADE, getString(R.string.whatsapp));
                break;
            case 3:
                replaceFragment(new MapFragment(), true, FragmentTransaction.TRANSIT_FRAGMENT_FADE, getString(R.string.map));
                break;
            default:
                break;

        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.home);
                break;
            case 2:
                mTitle = getString(R.string.albums);
                break;
            case 3:
                mTitle = getString(R.string.whatsapp);
                break;
            case 4:
                mTitle = getString(R.string.map);
                break;
            default:
                mTitle = getString(R.string.home);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    /**
     * Handles adding all fragments to the view.
     * @param newFragment The fragment to add.
     * @param addToBackstack Whether this Fragment should appear in the backstack or not.
     * @param transition The transition animation to apply
     * @param backstackName The name
     */
    public void replaceFragment(android.support.v4.app.Fragment newFragment, boolean addToBackstack, int transition, String backstackName) {
        // use fragmentTransaction to replace the fragment
        Log.i(TAG, "Initializing Fragment Transaction");
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Log.i(TAG, "Replacing the fragment and calling backstack");
        fragmentTransaction.replace(R.id.container, newFragment, backstackName);
        if (addToBackstack) {
            fragmentTransaction.addToBackStack(backstackName);
        }
        Log.i(TAG, "setting the transition");
        fragmentTransaction.setTransition(transition);
        Log.i(TAG, "Commiting Transaction");
        fragmentTransaction.commit();
    }

    /**
     * Handles adding all fragments to the view.
     * @param newFragment The fragment to add.
     * @param addToBackstack Whether this Fragment should appear in the backstack or not.
     */
    public void replaceFragment2(android.support.v4.app.Fragment newFragment, boolean addToBackstack) {
        // use fragmentTransaction to replace the fragment
        Log.i(TAG, "Initializing Fragment Transaction");
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Log.i(TAG, "Replacing the fragment and calling backstack");
        fragmentTransaction.replace(R.id.container, newFragment);
        Log.i(TAG, "Commiting Transaction");
        fragmentTransaction.commit();
    }
}
