package com.example.warrenchallenge.service.objectives

import com.example.warrenchallenge.model.objective.ObjectiveResponse
import com.example.warrenchallenge.service.wrapper.resource.NetworkBoundResource
import com.example.warrenchallenge.service.wrapper.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ObjectivesServiceDelegate {
    suspend fun getObjectives(accessToken: String): Flow<Resource<ObjectiveResponse?>>
}

class ObjectivesService(
    private val mApi: IObjectivesAPI
) : ObjectivesServiceDelegate {

    override suspend fun getObjectives(accessToken: String): Flow<Resource<ObjectiveResponse?>> {
        return flow {
            NetworkBoundResource(
                collector = this,
                processResponse = { it },
                call = mApi.getObjectives(accessToken)
            ).build()
        }
    }

}