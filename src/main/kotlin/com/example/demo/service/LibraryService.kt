package com.example.demo.service

import com.example.demo.dao.LibraryRepository
import com.example.demo.data.Library
import com.example.demo.exception.RequestException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LibraryService {

    companion object {
        private val log = LoggerFactory.getLogger(LibraryService::class.java)
    }

    @Autowired
    lateinit var libraryRepository: LibraryRepository

    @Autowired
    lateinit var userService: UserService

    fun getAllLibrariesOfUser(userEmail: String): List<Library> {
        val user = userService.getUserByEmail(userEmail) ?: throw RequestException("User not found for $userEmail")

        return libraryRepository.getAllLibrariesByUserIdOrEmail(user.id!!, userEmail)
    }

    fun getAllLibrariesByUserIdOrEmail(userId: Long, email: String): List<Library> {
        return libraryRepository.getAllLibrariesByUserIdOrEmail(userId, email)
    }

    fun getAllLibrariesByCityId(cityId: Long): List<Library> {
        return libraryRepository.getAllLibrariesByCityId(cityId)
    }

    fun getAllLibrariesByMobileNumber(mobile: String): List<Library> {
        return libraryRepository.getAllLibrariesByMobileNumber(mobile)
    }

    fun getLibraryByID(id: Long): Library {
        log.info("getLibraryByID called")
        return libraryRepository.getOne(id)
    }

    @Transactional
    fun createLibrary(library: Library, userEmail: String) {
        log.info("createLibrary called by $userEmail")

        if (library.name.isNullOrEmpty() || library.address.isNullOrEmpty()) throw RequestException("Name and Address is Mandatory")

        val user = userService.getUserByEmail(userEmail)?: throw RequestException("User not found for $userEmail")
        user.library = true

        if (library.email.isNullOrEmpty()) library.email = userEmail
        library.userId = user.id

        libraryRepository.save(library)
    }

}
