package com.jml.random.users.users.data.mapper

import com.jml.random.users.network.model.InfoResponse
import com.jml.random.users.users.data.model.db.pages.PageInfoEntity

object PaggingMapper {

    fun fromUserResponseToEntity(response: InfoResponse ) : PageInfoEntity{

        return PageInfoEntity(
            uuid = PageInfoEntity.PAGE_USERS,
            seed = response.seed,
            results = response.results,
            page =  response.page,
            version = response.version
        )


    }
}