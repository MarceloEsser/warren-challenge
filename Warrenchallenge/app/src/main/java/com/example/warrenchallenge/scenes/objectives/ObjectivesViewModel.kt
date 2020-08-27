package com.example.warrenchallenge.scenes.objectives

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warrenchallenge.model.objective.Objective
import com.example.warrenchallenge.model.objective.ObjectiveResponse
import com.example.warrenchallenge.persistence.PreferencesManager
import com.example.warrenchallenge.service.objectives.ObjectivesServiceDelegate
import com.example.warrenchallenge.service.wrapper.resource.Resource
import com.example.warrenchallenge.service.wrapper.resource.Status
import com.example.warrenchallenge.util.MyDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ObjectivesViewModel(
    private val service: ObjectivesServiceDelegate,
    private val dispatcher: MyDispatcher,
    private val preferences: PreferencesManager
) : ViewModel() {

    val objectivesList: MutableLiveData<List<Objective>> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    var totaIncome: Double = 0.0
    var hasObjectives: Boolean = false

    fun loadObjectives() {
        viewModelScope.launch(dispatcher.IO) {
            service.getObjectives(preferences.accessToken ?: "").collect { resource ->

                if (hasObjectives(resource)) {

                    resource.data?.objectives?.forEach {
                        totaIncome += it.totalBalance
                    }

                    hasObjectives = true
                    objectivesList.postValue(resource.data?.objectives)
                }

                if (requestFailed(resource)) {
                    if (resource.message != null) {
                        errorMessage.postValue(resource.message)
                        objectivesList.postValue(listOf())
                        hasObjectives = false
                    }
                }

            }
        }
    }

    private fun requestFailed(resource: Resource<ObjectiveResponse?>) =
        resource.requestStatus == Status.error

    private fun hasObjectives(resource: Resource<ObjectiveResponse?>) =
        resource.requestStatus == Status.success && resource.data?.objectives != null
}