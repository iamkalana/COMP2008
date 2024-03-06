package com.example.assignment2_parta;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment2_parta.databinding.FragmentUserPostsBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UserPostsFragment extends Fragment {

    FragmentUserPostsBinding binding;
    private Integer userID;
    private ArrayList<Post> allPosts;
    private ArrayList<Post> postList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserPostsBinding.inflate(inflater, container, false);

        allPosts = new ArrayList<Post>();
        postList = new ArrayList<Post>();

        userID = UserPostsFragmentArgs.fromBundle(getArguments()).getUserID();

        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<ArrayList<Post>> future = service.submit(new FetchPostData());

        try {
            allPosts = future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < allPosts.size(); i++) {
            if (allPosts.get(i).userId == userID) {
                postList.add(allPosts.get(i));
            }
        }

        RecyclerView rv = binding.postRv;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        UserPostsFragment.PostListAdapter adapter = new PostListAdapter(postList);
        rv.setAdapter(adapter);

        return binding.getRoot();
    }

    class PostListAdapter extends RecyclerView.Adapter<UserPostsFragment.PostListAdapter.PostVH> {

        private ArrayList<Post> postList;

        PostListAdapter(ArrayList<Post> postList) {
            this.postList = postList;
        }

        @NonNull
        @Override
        public UserPostsFragment.PostListAdapter.PostVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.post_item, parent, false);
            UserPostsFragment.PostListAdapter.PostVH postVH = new PostVH(view);
            return postVH;
        }

        @Override
        public void onBindViewHolder(@NonNull UserPostsFragment.PostListAdapter.PostVH holder, int position) {
            holder.title.setText(postList.get(position).title);
            holder.body.setText(postList.get(position).body);
        }

        @Override
        public int getItemCount() {
            return postList.size();
        }

        class PostVH extends RecyclerView.ViewHolder {
            private TextView title;
            private TextView body;

            public PostVH(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.postTitle);
                body = itemView.findViewById(R.id.postBody);
            }
        }

    }
}