package com.example.warrenchallenge.scenes.objectives

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warrenchallenge.model.objective.Objective
import com.example.warrenchallenge.persistence.PreferencesManager
import com.example.warrenchallenge.service.objectives.ObjectivesServiceDelegate
import com.example.warrenchallenge.util.MyDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ObjectivesViewModel(
    private val service: ObjectivesServiceDelegate,
    private val dispatcher: MyDispatcher,
    private val preferences: PreferencesManager
) : ViewModel() {

    val objectivesList: MutableLiveData<List<Objective>> = MutableLiveData()

    var totaIncome: Double = 0.0

    fun loadObjectives() {
        viewModelScope.launch(dispatcher.IO) {
            service.getObjectives(preferences.accessToken ?: "").collect { resource ->

                if (resource.data?.objectives != null) {

                    resource.data.objectives.forEach {
                        totaIncome += it.totalBalance
                    }

                    objectivesList.postValue(resource.data.objectives)
                }
            }
        }
    }
}