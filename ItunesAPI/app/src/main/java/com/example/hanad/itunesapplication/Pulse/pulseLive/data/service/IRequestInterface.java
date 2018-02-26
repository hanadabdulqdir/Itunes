package com.example.hanad.itunesapplication.Pulse.pulseLive.data.service;

import com.example.hanad.itunesapplication.Pulse.pulseLive.PulseLiveList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by hanad on 25/02/2018.
 */

public interface IRequestInterface {
    @GET(ApiList.CONTENT_LIST_ENDPOINT)
    Observable<PulseLiveList> getPulseLiveList();




    //@GET(ApiList.CONTENT_DETAIL_ENDPOINT)
    //Observable<>
    //
    // cant get data from the url its not valid
    //http://dynamic.pulselive.com/test/native/content/{id}.json



}
