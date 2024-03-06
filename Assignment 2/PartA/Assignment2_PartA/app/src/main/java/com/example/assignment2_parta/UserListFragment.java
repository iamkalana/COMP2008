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
import android.widget.Toast;

import com.example.assignment2_parta.databinding.FragmentUserListBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UserListFragment extends Fragment {

    private FragmentUserListBinding binding;
    private ArrayList<User> userList;
    private ArrayList<String> userNameList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserListBinding.inflate(inflater, container, false);

        userList = new ArrayList<User>();
        userNameList = new ArrayList<String>();

        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<ArrayList<User>> future = service.submit(new FetchUserData());

        try {
            userList = future.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < userList.size(); i++) {
            userNameList.add(userList.get(i).getUserName());
        }

        RecyclerView rv = binding.userListRv;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        UserListAdapter adapter = new UserListAdapter(userNameList);
        rv.setAdapter(adapter);

        return binding.getRoot();
    }

    class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListVH> {

        private ArrayList<String> userNameList;

        UserListAdapter(ArrayList<String> nameList) {
            this.userNameList = nameList;
        }

        @NonNull
        @Override
        public UserListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.user_list_item, parent, false);
            UserListVH userListVH = new UserListVH(view);
            return userListVH;
        }

        @Override
        public void onBindViewHolder(@NonNull UserListVH holder, int position) {
            holder.userListName.setText(userNameList.get(position));
        }

        @Override
        public int getItemCount() {
            return userNameList.size();
        }

        class UserListVH extends RecyclerView.ViewHolder {
            private TextView userListName;

            public UserListVH(@NonNull View itemView) {
                super(itemView);
                userListName = itemView.findViewById(R.id.userListName);
                itemView.setOnClickListener(view -> {
                    // Toast.makeText(getContext(), String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(view);
                    UserListFragmentDirections.ActionUserListFragmentToUserDetailsFragment dire =
                            UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(userList.get(getAdapterPosition()));
                    navController.navigate(dire);
                });
            }
        }

    }
}