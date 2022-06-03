package com.fadlurahmanf.starterappmvvm.ui.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fadlurahmanf.starterappmvvm.base.BaseState
import com.fadlurahmanf.starterappmvvm.base.BaseViewModel
import com.fadlurahmanf.starterappmvvm.data.entity.movie.MovieEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    var entity: MovieEntity
):BaseViewModel() {
    private var stateData = MovieStateData()

    private var _state:MutableLiveData<MovieStateData> = MutableLiveData<MovieStateData>(stateData)
    val state get() : LiveData<MovieStateData> = _state

    fun getAllGenre(){
        stateData.getGenreState = BaseState.LOADING
        _state.value = stateData
        disposable().add(entity.getAllGenre()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.genres != null){
                        stateData.getGenreState = BaseState.SUCCESS
                        stateData.genreData = it
                        _state.value = stateData
                    }else{
                        stateData.getGenreState = BaseState.FAILED
                        stateData.errorGenreData = "There is no content here!"
                        _state.value = stateData
                    }
                },
                {
                    stateData.getGenreState = BaseState.FAILED
                    stateData.errorGenreData = it.message
                    _state.value = stateData
                },
                {
                    stateData.getGenreState = BaseState.IDLE
                    _state.value = stateData
                }
            ))
    }

    fun discoverMovie(genre:String, page:Int){
        stateData.getDiscoverState = BaseState.LOADING
        _state.value = stateData
        disposable().add(entity.discoverMovie(genre, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.results != null){
                        stateData.getDiscoverState = BaseState.SUCCESS
                        stateData.discoverData = it
                        _state.value = stateData
                    }else{
                        stateData.getDiscoverState = BaseState.IDLE
                        stateData.errorDiscoverData = "There is no result here!"
                        _state.value = stateData
                    }
                },
                {
                    stateData.getDiscoverState = BaseState.FAILED
                    stateData.errorDiscoverData = it.message
                    _state.value = stateData
                },
                {
                    stateData.getDiscoverState = BaseState.IDLE
                    _state.value = stateData
                }
            ))
    }
}