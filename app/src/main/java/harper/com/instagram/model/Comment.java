package harper.com.instagram.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

/**
 * Created: xuemaomao
 * Date: 2019-11-22
 * Package: harper.com.instagram.model
 * File: Comment
 * Description: TODO
 */
@ParseClassName("Comment")
public class Comment extends ParseObject {
    public static final String KEY_USER = "user";
    public static final String KEY_POST = "post";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_TEXT = "text";

//    public ParseFile getImage() {
//        return getParseFile(KEY_IMAGE);
//    }
//
//    public void setImage(ParseFile parseFile) {
//        put(KEY_IMAGE, parseFile);
//    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser parseUser) {
        put(KEY_USER, parseUser);
    }

    public Date getCreatedAt(String createdAt) {
        return getCreatedAt(KEY_CREATED_AT);
    }
//    public ParseObject getPost() {
//        return getParseObject(KEY_POST);
//    }
    public String getComment() {
        return getString(KEY_TEXT);
    }
    public void setComment(String comment) {
        put(KEY_TEXT, comment);
    }

}





