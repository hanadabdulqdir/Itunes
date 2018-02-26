package com.example.hanad.itunesapplication.Pulse.pulseLive;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hanad.itunesapplication.Pulse.pulseLive.data.service.IRequestInterface;
import com.example.hanad.itunesapplication.Pulse.pulseLive.data.service.ServiceConnection;
import com.example.hanad.itunesapplication.R;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hanad on 09/02/2018.
 */

public class PulseLiveFragment extends Fragment {
    CompositeDisposable compositeDisposable;
    public IRequestInterface iRequestInterface;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshListener;

    // TopRatedMovies topRatedMovies;
    public PulseLiveFragment() {

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iRequestInterface = ServiceConnection.getConnection();
        recyclerView = view.findViewById(R.id.recyclerPulseLive);
        compositeDisposable = new CompositeDisposable();
        refreshListener = view.findViewById(R.id.swiperefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //progress
        CallService();

        refreshListener.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CallService();
                //if (NetworkUtils.isNetworkConnected((getActivity()))) {
                //  displayTopRatedMovies();
                //} else {
                Toast.makeText(getActivity(), "Refreshing", Toast.LENGTH_LONG).show();
                //}
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pulse_live, container, false);
    }

    public void CallService() {
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) {
                        // do something with isConnectedToInternet value
                        if (isConnectedToInternet) {
                            displayPulseLive();
                        } else {
                            Toast.makeText(getActivity(), "Off Line", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    public void displayPulseLive() {
        compositeDisposable.add(
                iRequestInterface.getPulseLiveList()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<PulseLiveList>() {
                                       @Override
                                       public void accept(PulseLiveList pulseLiveList) throws Exception {
                                           recyclerView.setAdapter(new PulseLiveAdapter(PulseLiveFragment.this.getActivity().getApplicationContext(),R.layout.row ,pulseLiveList.getItems(), PulseLiveFragment.this, pulseLiveList));
                                           //recyclerView.setAdapter(new PulseLiveAdapter(getContext(),R.layout.row,pulseLiveList.getItems(),PulseLiveFragment.this,pulseLiveList));
                                           //recyclerView.setAdapter(new TopRatedMoviesAdapter(TopMoviesFragment.this.getActivity().getApplicationContext(), topRatedMovies.getResults(), TopMoviesFragment.this, topRatedMovies, R.layout.row));

                                           refreshListener.setRefreshing(false);
                                       }
                                   },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                    }
                                }));
    }
}