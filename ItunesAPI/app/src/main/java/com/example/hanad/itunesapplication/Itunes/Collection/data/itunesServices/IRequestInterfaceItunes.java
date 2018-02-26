package com.example.hanad.itunesapplication.Itunes.Collection.data.itunesServices;

/**
 * Created by hanad on 09/02/2018.
 */

import com.example.hanad.itunesapplication.Itunes.Collection.classicDetails.model.ClassicMusicList;
import com.example.hanad.itunesapplication.Itunes.Collection.popDetails.model.PopMusicList;
import com.example.hanad.itunesapplication.Itunes.Collection.rockDetails.model.RockMusicList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IRequestInterfaceItunes {
    @GET(ApiList.ROCK_API)
    Observable<RockMusicList> getRockMusicList();

    @GET(ApiList.POP_API)
    Observable<PopMusicList> getPopMusicList();

    @GET(ApiList.CLASSIC_API)
    Observable<ClassicMusicList> getClassicMusicList();

//    @GET(ApiList.CONTENT_BASE_URL)
//    Observable<>
}
