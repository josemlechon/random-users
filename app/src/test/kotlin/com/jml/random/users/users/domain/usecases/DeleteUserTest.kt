package com.jml.random.users.users.domain.usecases

import org.mockito.Mockito.`when` as whenever
import com.jml.random.users.users.domain.repository.UserRepository
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Completable
import org.junit.Assert

import org.junit.Before
import org.junit.Test

class DeleteUserTest {

    //mocks
    private val userRepo = mock<UserRepository>()
    private lateinit var deleteUser: DeleteUser

    @Before
    fun before() {
        deleteUser = DeleteUser(userRepo)
    }


    @Test
    fun `validate user has been deleted`() {

        //Given
        val idUser = "xxxxxxx"

        //When
        whenever(userRepo.deleteUser(idUser)).thenReturn(Completable.complete())
        val deleteTest = deleteUser.execute(idUser)
            .test()
        //then
        deleteTest.assertComplete()
        deleteTest.assertNoErrors()
    }


    @Test
    fun `validate user has NOT been deleted due to exception`() {

        //Given
        val idUser = "xxxxxxx"

        //When
        whenever(userRepo.deleteUser(idUser)).thenReturn(Completable.error(Exception("Something went wrong")))
        val deleteTest = deleteUser.execute(idUser)
            .test()
        //then
        Assert.assertEquals("failure - Exception should happen", 1, deleteTest.errorCount())
    }
}