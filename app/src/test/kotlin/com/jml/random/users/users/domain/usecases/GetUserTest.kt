package com.jml.random.users.users.domain.usecases

import com.jml.random.users.users.domain.model.User
import org.mockito.Mockito.`when` as whenever
import com.jml.random.users.users.domain.repository.UserRepository
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Maybe
import org.junit.Assert

import org.junit.Before
import org.junit.Test

internal class GetUserTest {

    //mocks
    private val userRepo = mock<UserRepository>()
    private val user = mock<User>()
    private lateinit var getUser: GetUser

    @Before
    fun before() {
        getUser = GetUser(userRepo)
    }

    @Test
    fun `validate get user returns an user`() {

        //Given
        val idUser = "xxxxxxx"

        whenever(user.id).thenReturn(idUser)

        //When
        whenever(userRepo.getUser(idUser)).thenReturn(Maybe.just(user))
        val getUserTest = getUser.execute(idUser)
            .test()

        //then
        getUserTest.assertResult(user)
        val userResult = getUserTest.values()[0]
        Assert.assertEquals("failure - User ID does not match", idUser, userResult.id)
    }


    @Test
    fun `validate get user when it returns an exception`() {

        //Given
        val idUser = "xxxxxxx"

        //When
        whenever(userRepo.getUser(idUser)).thenReturn(Maybe.error(Exception("Something went wrong")))
        val getUserTest = getUser.execute(idUser)
            .test()
        //then
        Assert.assertEquals("failure - Exception should happen", 1, getUserTest.errorCount())
    }

    @Test
    fun `validate get user did not found it and throws exception`() {

        //Given
        val idUser = "xxxxxxx"

        //When
        whenever(userRepo.getUser(idUser)).thenReturn(Maybe.empty())
        val getUserTest = getUser.execute(idUser)
            .test()
        //then
        Assert.assertEquals("failure - Exception should happen", 1, getUserTest.errorCount())
    }
}