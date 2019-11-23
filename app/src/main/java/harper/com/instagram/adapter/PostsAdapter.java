package harper.com.instagram.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

import org.w3c.dom.Comment;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import harper.com.instagram.R;
import harper.com.instagram.activity.CommentActivity;
import harper.com.instagram.fragments.PostsFragment;
import harper.com.instagram.model.Post;

import static harper.com.instagram.R.drawable.ufi_heart_active;

/**
 * Created: xuemaomao
 * Date: 2019-11-16
 * Package: harper.com.instagram
 * File: PostsAdapter
 * Description: TODO
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>  {

    private static final String TAG = "postsadapater";
    private Context context;
    private List<Post> posts;
    private String description;
    private List<Comment> comments;
    private  Date createdAt;
    private ParseFile image;
    private boolean like;
    private int likesCount;
    private String objectId;

    PostsFragment postsFragment;


    public PostsAdapter(Context context, List<Post> mPosts, PostsFragment postsFragment) {
        this.context = context;
        this.posts = mPosts;
        this.postsFragment = postsFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvHandle;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvCreatedAt;
        private ImageButton ibtnLike;
        private ImageButton ibtnComment;

        private TextView tvLikes;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvHandle = itemView.findViewById(R.id.tvHandle);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedtime);
            ibtnLike = itemView.findViewById(R.id.ibtnLike);
            ibtnComment = itemView.findViewById(R.id.ibtnComment);
            tvLikes = itemView.findViewById(R.id.tvlikes);
        }

        public void bind(final Post post) {
            //todo: bind the view elements to the posts
            tvHandle.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
            tvDescription.setText(post.getDecription());
            createdAt = post.getCreatedAt();
            like = post.getLike();
            likesCount = post.getLikes();
            objectId = post.getObjectId();
            if (like) {
                ibtnLike.setImageResource(R.drawable.ufi_heart_active);
            } else {
                ibtnLike.setImageResource(R.drawable.ufi_heart);
            }
            tvLikes.setText(likesCount + " likes");

            String date = DateFormat.getDateTimeInstance().format(post.getCreatedAt());
            tvCreatedAt.setText(date);
            ibtnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "heart count " + likesCount, Toast.LENGTH_SHORT).show();
                    like = post.getLike();
                    likesCount = post.getLikes();
                    like = !like;
                    if (like) {
                        likesCount++;
                        ibtnLike.setImageResource(ufi_heart_active);
                        Toast.makeText(context, "like " + like + "likes count " + likesCount, Toast.LENGTH_SHORT).show();
                    } else {
                        likesCount--;
                        ibtnLike.setImageResource(R.drawable.ufi_heart);
                        Toast.makeText(context, "like " + like + "likes count " + likesCount, Toast.LENGTH_SHORT).show();

                    }
                    tvLikes.setText(likesCount + " likes");

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                        Post post = posts.get(position);
                        final String objectId = post.getObjectId();
                        post.setLikes(likesCount);
                        post.setLike(like);
                        post.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e != null) {
                                    Log.d(TAG, "error while saving");
                                    e.printStackTrace();
                                    return;
                                }
                                Log.d(TAG, "Success!" + likesCount);

                            }
                        });

                    }
                }
            });

            //go to comment page
            ibtnComment.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent i = new Intent(postsFragment.getActivity(), CommentActivity.class);
                    i.putExtra("objectId", objectId);
                    i.putExtra("likesCount", likesCount);
                    postsFragment.startActivity(i);
                }
            });
//            ibtnComment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
//                        Post post = posts.get(position);
//                        final String objectId = post.getObjectId();
//                        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//                        query.getInBackground(objectId, new GetCallback<Post>() {
//                            public void done(Post item, ParseException e) {
//                                if (e == null) {
//                                    description = item.getDecription();
//                                    //createdAt = item.getCreatedAt();
//                                    //image = item.getImage();
//
//                                    Intent intent = new Intent(context, CommentActivity.class);
//                                    intent.putExtra("objectId", objectId);
//                                    intent.putExtra("description", description);
//                                    //intent.putExtra("comment", (Parcelable) comments);
//                                     postsFragment.startActivity(intent);
//
//                                } else {
//                                    e.printStackTrace();
//                                    return;
//                                }
//                            }
//
//                        });
//                    }
//                }
//            });
        }



        @Override
        public void onClick(View view) {

        }
    }
}