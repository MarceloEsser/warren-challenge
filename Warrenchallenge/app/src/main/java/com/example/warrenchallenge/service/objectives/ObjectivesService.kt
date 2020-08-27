package com.example.warrenchallenge.service.objectives

import com.example.warrenchallenge.model.LoginResponse
import com.example.warrenchallenge.model.Objective
import com.example.warrenchallenge.model.UserLogin
import com.example.warrenchallenge.service.NetworkHandler
import com.example.warrenchallenge.service.wrapper.resource.NetworkBoundResource
import com.example.warrenchallenge.service.wrapper.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ObjectivesServiceDelegate {
    suspend fun getObjectives(accessToken: String): Flow<Resource<List<Objective>?>>
}

class ObjectivesService(
    private val mApi: IObjectivesAPI = IObjectivesAPI.api,
) : ObjectivesServiceDelegate {

    override suspend fun getObjectives(accessToken: String): Flow<Resource<List<Objective>?>> {
        return flow {
            NetworkBoundResource(
                collector = this,
                processResponse = { it },
                call = mApi.getObjectives(accessToken)
            ).build()
        }
    }

}