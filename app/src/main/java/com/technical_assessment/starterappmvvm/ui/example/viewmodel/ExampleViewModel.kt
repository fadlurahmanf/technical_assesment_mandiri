package com.technical_assessment.starterappmvvm.ui.example.viewmodel

import com.technical_assessment.starterappmvvm.base.BaseViewModel
import com.technical_assessment.starterappmvvm.data.entity.example.ExampleEntity
import com.technical_assessment.starterappmvvm.data.repository.example.ExampleRepository
import javax.inject.Inject

class ExampleViewModel @Inject constructor(
    var exampleEntity: ExampleEntity,
    var exampleRepository: ExampleRepository
):BaseViewModel() {

}