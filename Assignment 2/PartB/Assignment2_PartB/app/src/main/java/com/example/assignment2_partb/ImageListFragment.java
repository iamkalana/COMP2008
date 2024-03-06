package com.example.assignment2_partb;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.assignment2_partb.databinding.FragmentImageListBinding;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ImageListFragment extends Fragment {

    FragmentImageListBinding binding;
    private ArrayList<Bitmap> arrayList = new ArrayList<>();
    private ImageListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentImageListBinding.inflate(inflater, container, false);
        binding.progressBar.setVisibility(View.INVISIBLE);

        binding.searchBtn.setOnClickListener(view -> {
            String searchKey = binding.searchBar.getText().toString();
            if (!searchKey.isEmpty()) {
                arrayList.clear();
                adapter.notifyDataSetChanged();
                searchImage(searchKey);
            } else {
                arrayList.clear();
                adapter.notifyDataSetChanged();
            }
        });

        binding.clearBtn.setOnClickListener(view -> {
            binding.searchBar.getText().clear();
        });

        RecyclerView rv = binding.imageListRv;
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.viewBtn.setOnClickListener(view1 -> {
            if (binding.viewBtn.getTag().equals("list")) {
                rv.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
                binding.viewBtn.setImageResource(R.drawable.ic_list);
                binding.viewBtn.setTag("grid");
            } else {
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.viewBtn.setImageResource(R.drawable.ic_grid);
                binding.viewBtn.setTag("list");
            }
        });

        adapter = new ImageListAdapter(arrayList);
        rv.setAdapter(adapter);

        return binding.getRoot();
    }

    public void searchImage(String searchKey) {
        binding.progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), "Searching...", Toast.LENGTH_SHORT).show();
        Log.d("Debug Notifications: ", "Searching starts");

        SearchTask searchTask = new SearchTask(getActivity());
        searchTask.setSearchkey(searchKey);
        Single<String> searchObservable = Single.fromCallable(searchTask);
        searchObservable = searchObservable.subscribeOn(Schedulers.io());
        searchObservable = searchObservable.observeOn(AndroidSchedulers.mainThread());
        searchObservable.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
            }

            @Override
            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull String s) {
                Log.d("Debug Notifications: ", "Searching Ends");
                binding.progressBar.setVisibility(View.INVISIBLE);
                loadImage(s);
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                Toast.makeText(getContext(), "Searching Error", Toast.LENGTH_SHORT).show();
                Log.d("Debug Notifications: ", "Searching Error " + e.getMessage());
                binding.progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void loadImage(String response) {
        ImageRetrievalTask imageRetrievalTask = new ImageRetrievalTask(getActivity());
        imageRetrievalTask.setData(response);

        Log.d("Debug Notifications: ", "Image loading starts");
        binding.progressBar.setVisibility(View.VISIBLE);

        Single<ArrayList<Bitmap>> searchObservable = Single.fromCallable(imageRetrievalTask);
        searchObservable = searchObservable.subscribeOn(Schedulers.io());
        searchObservable = searchObservable.observeOn(AndroidSchedulers.mainThread());
        searchObservable.subscribe(new SingleObserver<ArrayList<Bitmap>>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull ArrayList<Bitmap> bitmap) {
                binding.progressBar.setVisibility(View.INVISIBLE);

                if(!bitmap.isEmpty()){
                    for (int i = 0; i < 15; i++) {
                        arrayList.add(bitmap.get(i));
                    }
                }
                adapter.notifyDataSetChanged();

                Log.d("Debug Notifications: ", "Image loading Ends");
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                Toast.makeText(getContext(), "Image loading error, search again", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.INVISIBLE);
                Log.d("Debug Notifications: ", "Image loading error, search again " + e.getMessage());
            }
        });
    }

    class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageListVH> {
        private ArrayList<Bitmap> arrayList;

        public ImageListAdapter(ArrayList<Bitmap> arrayList) {
            this.arrayList = arrayList;
        }

        @NonNull
        @Override
        public ImageListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.image_list_item, parent, false);
            ImageListVH imageListVH = new ImageListVH(view);
            return imageListVH;
        }

        @Override
        public void onBindViewHolder(@NonNull ImageListVH holder, int position) {
            holder.imageView.setImageBitmap(arrayList.get(position));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class ImageListVH extends RecyclerView.ViewHolder {
            private ImageView imageView;

            public ImageListVH(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.list_image);
                imageView.setOnClickListener(view -> {
                    NavController navController = Navigation.findNavController(view);
                    ImageListFragmentDirections.ActionImageListFragmentToImageSaveFragment dire =
                            ImageListFragmentDirections.actionImageListFragmentToImageSaveFragment(arrayList.get(getAdapterPosition()));
                    navController.navigate(dire);
                });
            }
        }
    }
}