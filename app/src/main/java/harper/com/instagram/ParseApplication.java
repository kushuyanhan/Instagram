package harper.com.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import harper.com.instagram.model.Post;

/**
 * Created: xuemaomao
 * Date: 2019-11-13
 * Package: harper.com.instagram
 * File: ParseApplication
 * Description: TODO
 */
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);

        // set applicationId, and server server based on the values in the Heroku settings.
        // clientKey is not needed unless explicitly configured
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("kun-instagram-codepath") // should correspond to APP_ID env variable
                .clientKey("CodepathInstagramMoveFast")  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("http://kun-instagram-codepath.herokuapp.com/parse").build());
    }
}
