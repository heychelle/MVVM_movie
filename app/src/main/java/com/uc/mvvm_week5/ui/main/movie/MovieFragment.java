package com.uc.mvvm_week5.ui.main.movie;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.uc.mvvm_week5.R;
import com.uc.mvvm_week5.adapter.MovieAdapter;
import com.uc.mvvm_week5.model.Movie;
import com.uc.mvvm_week5.ui.splash.SplashFragmentDirections;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link MovieFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MovieFragment extends Fragment {

//    @BindView(R.id.btn_to_detail)
    Button button;

    @BindView(R.id.rv_movie)
    RecyclerView rvMovies;
//    @BindView(R.id.loading)
//    Dialog dialog;

    private MovieViewModel viewModel;
    private MovieAdapter movieAdapter;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
//        showLoading(true);
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        movieAdapter = new MovieAdapter(getActivity());

        viewModel = ViewModelProviders.of(requireActivity()).get(MovieViewModel.class);
        viewModel.getMovieCollection().observe(requireActivity(),observeViewModel);

        //view1 cuma penamaan saja, bisa v juga
//        button.setOnClickListener(view1 -> {
//            NavDirections action = MovieFragmentDirections.actionMovieToDetail(movie);
//            Navigation.findNavController(view).navigate(action);
//        });


    }

    //klo mau jadi Lambda (Lambda itu ->) di alt enter tulisan abu"nya
    private Observer<List<Movie>> observeViewModel = movies -> {
        if (movies !=null){
            movieAdapter.setMovies(movies);
            movieAdapter.notifyDataSetChanged();
            rvMovies.setAdapter(movieAdapter);
//            showLoading(false);
//            Movie movie = movies.get(0);
//            button.setText(movie.getTitle());
//            Toast.makeText(requireActivity(),movie.getTitle(),Toast.LENGTH_SHORT).show();;
            //set adapter
//                adapter.setMovies(movies);
//                adapter.notifySetDataChanged();
//                recyclerView.setAdapter(adapter);
            // add adapter to recycleview
            Log.d("coba berhasil", "movies");

        }

        else{
            Log.d("coba gagal", "aaaaaaaaaaaaa");
        }
    };
    private void showLoading(Boolean state) {
        if (state) {
            rvMovies.setVisibility(View.GONE);
        } else {
            rvMovies.setVisibility(View.VISIBLE);
        }
    }
}