package com.fadlurahmanf.starterappmvvm.ui.genre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fadlurahmanf.starterappmvvm.base.BaseState
import com.fadlurahmanf.starterappmvvm.base.BaseViewModel
import com.fadlurahmanf.starterappmvvm.data.entity.movie.MovieEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GenreViewModel @Inject constructor(
    var entity: MovieEntity
):BaseViewModel() {
    private var stateData = GenreStateData()

    private var _state:MutableLiveData<GenreStateData> = MutableLiveData<GenreStateData>(stateData)
    val state get() : LiveData<GenreStateData> = _state

    fun getAllGenre(){
        stateData.state = BaseState.LOADING
        _state.value = stateData
        disposable().add(entity.getAllGenre()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.genres != null){
                        stateData.state = BaseState.SUCCESS
                        stateData.genreData = it
                        _state.value = stateData
                    }else{
                        stateData.state = BaseState.FAILED
                        stateData.errorGenreData = "There is no content here!"
                        _state.value = stateData
                    }
                },
                {
                    stateData.state = BaseState.FAILED
                    stateData.errorGenreData = it.message
                    _state.value = stateData
                },
                {
                    stateData.state = BaseState.IDLE
                    _state.value = stateData
                }
            ))
    }
}