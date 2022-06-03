package com.fadlurahmanf.starterappmvvm.ui.example.viewmodel

import com.fadlurahmanf.starterappmvvm.base.BaseViewModel
import com.fadlurahmanf.starterappmvvm.data.entity.example.ExampleEntity
import com.fadlurahmanf.starterappmvvm.data.repository.example.ExampleRepository
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    var exampleEntity: ExampleEntity,
    var exampleRepository: ExampleRepository
):BaseViewModel() {

}