package harper.com.instagram.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;
import java.util.List;

/**
 * Created: xuemaomao
 * Date: 2019-11-13
 * Package: harper.com.instagram
 * File: Post
 * Description: TODO
 */
@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_DESCRIPTION = "description";

    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_LIKE = "like";
    public static final String KEY_LIKESCOUNT = "likesCount";


    public String getDecription() {
        return getString(KEY_DESCRIPTION);
    }
    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }
    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }
    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser parseUser) {
        put(KEY_USER, parseUser);
    }
    private Date getCreatedAt(String createdAt) {
        return getCreatedAt(KEY_CREATED_AT);
    }


   public Boolean getLike() {
        return getBoolean(KEY_LIKE);
   }
   public void setLike(boolean like) {
        put(KEY_LIKE, like);
   }
   public int getLikes() {
        return getInt(KEY_LIKESCOUNT);
   }
   public void setLikes(int likes) {
        put(KEY_LIKESCOUNT, likes);
   }
   public List<Comment> getComments() {
        return getList(KEY_COMMENT);
   }
   public void setComments(List<Comment> comments) {
        put(KEY_COMMENT, comments);
   }

}
