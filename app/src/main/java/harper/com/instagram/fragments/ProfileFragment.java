package harper.com.instagram.fragments;

/**
 * Created: xuemaomao
 * Date: 2019-11-16
 * Package: harper.com.instagram.fragments
 * File: ProfileFragment
 * Description: TODO
 */
public class ProfileFragment extends PostsFragment {

//    @Override
//    protected void queryPosts() {
//        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
//        postQuery.include(Post.KEY_USER);
//        postQuery.setLimit(20);
//        postQuery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
//        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
//        postQuery.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Error with query");
//                    e.printStackTrace();
//                    return;
//                }
//                mPosts.addAll(posts);
//                adapter.notifyDataSetChanged();
//
//                for (int i = 0; i < posts.size(); i++) {
//                    Post post = posts.get(i);
//                    Log.d(TAG, "Post " + post.getDecription() + " username: " + post.getUser().getUsername());
//                }
//            }
//        });
//    }
}

