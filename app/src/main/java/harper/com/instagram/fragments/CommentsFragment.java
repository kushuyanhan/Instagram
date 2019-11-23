package harper.com.instagram.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import harper.com.instagram.R;
import harper.com.instagram.adapter.CommentsAdapter;
import harper.com.instagram.model.Comment;
import harper.com.instagram.model.Post;

/**
 * Created: xuemaomao
 * Date: 2019-11-22
 * Package: harper.com.instagram.fragments
 * File: CommentsFragment
 * Description: TODO
 */
public class CommentsFragment extends Fragment {

    public static final String TAG = "CommentsFragment";

    private RecyclerView rvComments;
    protected CommentsAdapter adapter;
    protected List<Comment> mComments;
    protected  String objectId;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvComments = view.findViewById(R.id.rvComments);

        //create the data source
        mComments = new ArrayList<>();
        //create the adapter
        adapter = new CommentsAdapter(getContext(), mComments, this);
        //set the adapter on the recycler view
        rvComments.setAdapter(adapter);
        //set the layout manager on the recycler view
        rvComments.setLayoutManager(new LinearLayoutManager(getContext()));

        objectId = getActivity().getIntent().getExtras().getString("objectId");
        //getActivity().getIntent().getExtras().getString("likesCount");
        queryComment();
    }

    private void queryComment() {
        ParseQuery<Comment> postQuery = new ParseQuery<Comment>(Comment.class);
        postQuery.include(Comment.KEY_USER);
        postQuery.setLimit(20);
        postQuery.addDescendingOrder(Comment.KEY_CREATED_AT);
        postQuery.whereEqualTo("objectId", objectId);
        postQuery.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> comments, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }
                mComments.addAll(comments);
                adapter.notifyDataSetChanged();

                for (int i = 0; i < comments.size(); i++) {
                    Comment c = comments.get(i);
                    Log.d(TAG, "comment is  " + c.getComment());
                }
            }
        });
    }

    //save comment
    private void savePost(String description, ParseUser parseUser, File photoFile) {
        Post post = new Post();
        post.setDescription(description);
        post.setUser(parseUser);
        post.setImage(new ParseFile(photoFile));
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.d(TAG, "error while saving");
                    e.printStackTrace();
                    return;
                }
                Log.d(TAG, "Success!");
            }
        });
    }
}



