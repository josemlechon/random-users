package com.jml.random.users.users.domain.usecases

import org.mockito.Mockito.`when` as whenever
import com.jml.random.users.home.domain.usecases.GetHomeUsers
import com.jml.random.users.network.exceptions.NetworkException
import com.jml.random.users.users.domain.model.User
import com.jml.random.users.users.domain.model.UserPhotos
import com.jml.random.users.users.domain.repository.UserRepository
import com.nhaarman.mockitokotlin2.mock

import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Assert

import org.junit.Before

import org.junit.Test

internal class GetHomeUsersTest {

    //mocks
    private val userRepo = mock<UserRepository>()
    private lateinit var userMocked: User
    private var userPhotos: UserPhotos = mock()

    private lateinit var getHomeUsers: GetHomeUsers

    private val listUser: MutableList<User> = mutableListOf()


    @Before
    fun setUp() {
        getHomeUsers = GetHomeUsers(userRepo)

        userMocked = mock()
        for (x in 0..10) listUser.add(userMocked)
    }

    @Test
    fun `validate returns list of LOCAL UserBrief`() {

        //Given
        prepareUserMocksForMapping()

        whenever(userRepo.getLocalUsers()).thenReturn(Maybe.just(listUser))
        whenever(userRepo.getUsers()).thenReturn(Single.never())

        //When
        val test = getHomeUsers.execute().test()
        //then
        Assert.assertEquals("failure - Exception should happen", listUser.size, test.values()[0].size)
    }

    @Test
    fun `validate no LOCAL DATA and it returns  list of SERVER ones`() {

        //Given
        prepareUserMocksForMapping()

        whenever(userRepo.getLocalUsers()).thenReturn(Maybe.just(listOf()))
        whenever(userRepo.getUsers()).thenReturn(Single.just(listUser))

        //When
        val test = getHomeUsers.execute().test()
        //then
        Assert.assertEquals("failure - Exception should happen", listUser.size, test.values()[0].size)
    }


    @Test
    fun `validate no LOCAL DATA and No network`() {

        //Given
        prepareUserMocksForMapping()

        whenever(userRepo.getLocalUsers()).thenReturn(Maybe.just(listOf()))
        whenever(userRepo.getUsers()).thenReturn(Single.error(NetworkException()))

        //When
        val test = getHomeUsers.execute().test()

        //then
        test.assertFailure(NetworkException::class.java)
    }


    @Test
    fun `validate LOCAL DATA with No network connection`() {

        //Given
        prepareUserMocksForMapping()

        whenever(userRepo.getLocalUsers()).thenReturn(Maybe.just(listUser))
        whenever(userRepo.getUsers()).thenReturn(Single.error(NetworkException()))

        //When
        val test = getHomeUsers.execute().test()

        //then
        test.assertNoErrors()
        Assert.assertEquals("failure - Exception should happen", listUser.size, test.values()[0].size)
    }


    private fun prepareUserMocksForMapping() {


        whenever(userPhotos.medium).thenReturn("")
        whenever(userPhotos.large).thenReturn("")
        whenever(userPhotos.thumbnail).thenReturn("")

        whenever(userMocked.id).thenReturn("")
        whenever(userMocked.getFullName()).thenReturn("")
        whenever(userMocked.firstName).thenReturn("")
        whenever(userMocked.lastName).thenReturn("")
        whenever(userMocked.titleName).thenReturn("")
        whenever(userMocked.gender).thenReturn("")
        whenever(userMocked.email).thenReturn("")
        whenever(userMocked.phone).thenReturn("")
        whenever(userMocked.pictures).thenReturn(userPhotos)

    }


}