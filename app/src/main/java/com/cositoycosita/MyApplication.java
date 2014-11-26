package com.cositoycosita;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.cositoycosita.fragment.AlbumFragment;
import com.cositoycosita.model.AlbumItem;
import com.cositoycosita.model.ImageItem;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


/**
 * Created by Owner on 9/16/2014.
 */


public class MyApplication extends Application {
    private final static String TAG = Application.class.getSimpleName();
    public ParseUser user;
    // Key for saving the search distance preference
    private static final String KEY_PARSE_USER = "user";
    private static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");

        /*
         * Registering Parse Subclasses
         *
         */
        ParseObject.registerSubclass(AlbumItem.class);
        //ParseObject.registerSubclass(WhatsApp.class);
        //ParseObject.registerSubclass(MapPoint.class);

        preferences = getSharedPreferences("com.cositoycosita", Context.MODE_PRIVATE);
        /*
            Initialize the ability to store data locally
         */
        Parse.enableLocalDatastore(this);

        /*
         * Parse credentials and initialize
         */
        Parse.initialize(this, "M7vg3gp0O1EkkLvb3b9dlMpNtmlXiMJTcL2o9x3z", "QXRSPvdQBus1R49y5cvo5R6nQxvJTtzRfkpE2gtt");


        /*
         * This app lets an anonymous user create and save photos of meals
         * they've eaten. An anonymous user is a user that can be created
         * without a username and password but still has all of the same
         * capabilities as any other ParseUser.
         *
         * After logging out, an anonymous user is abandoned, and its data is no
         * longer accessible. In your own app, you can convert anonymous users
         * to regular users so that data persists.
         *
         * Learn more about the ParseUser class:
         * https://www.parse.com/docs/android_guide#users
         */
        //ParseUser.enableAutomaticUser();

        /*
         * For more information on app security and Parse ACL:
         * https://www.parse.com/docs/android_guide#security-recommendations
         */
        ParseACL defaultACL = new ParseACL();

        /*
         * If you would like all objects to be private by default, remove this
         * line
         */
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

    }

    public static void setAlbum(String album) {
        preferences.edit().putString("Album", album).commit();
    }

    public static String getAlbum() {
        return preferences.getString("Album", "");
    }
}