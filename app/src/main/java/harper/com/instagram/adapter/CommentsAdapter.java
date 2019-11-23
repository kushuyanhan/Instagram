package harper.com.instagram.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import harper.com.instagram.R;
import harper.com.instagram.fragments.CommentsFragment;
import harper.com.instagram.model.Comment;

/**
 * Created: xuemaomao
 * Date: 2019-11-22
 * Package: harper.com.instagram.adapter
 * File: CommentsAdapter
 * Description: TODO
 */
public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private Context context;
    private List<Comment> comments;
    private CommentsFragment commentsFragment;

    public CommentsAdapter(@NonNull Context context, List<Comment> mComments, CommentsFragment commentsFragment) {
        this.context = context;
        this.comments = mComments;
        this.commentsFragment = commentsFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView ivProfile;
        private TextView tvComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.ivProfle);
            tvComment = itemView.findViewById(R.id.tvComments);
        }

        public void bind(final Comment comment) {
            //ivProfile.setImageResource();
            tvComment.setText(comment.getComment());


        }

        @Override
        public void onClick(View view) {

        }
    }
}
