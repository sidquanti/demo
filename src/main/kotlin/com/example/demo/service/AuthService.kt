package com.example.demo.service

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import com.example.demo.data.User
import com.example.demo.model.Credential
import com.example.demo.model.GoogleTokenResponse
import com.example.demo.model.Profile
import com.google.gson.Gson
//import com.example.demo.proxy.GoogleTokenProxy
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AuthService {

    companion object {
        private val log = LoggerFactory.getLogger(AuthService::class.java)
    }

/*    @Autowired
    private lateinit var googleTokenProxy: GoogleTokenProxy*/

    @Autowired
    private lateinit var tokenService: TokenService

    @Autowired
    private lateinit var userService: UserService

    fun signIn(credential: Credential): Profile? {

        val user = verifyCredentials(credential) ?: return null

        return getUserProfile(user, credential.app)
    }

    fun makeGetRequest(url: String): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        try {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
                log.info("isSuccessful")
                // If the response is successful, return the response body as a string
                return response.body?.string() ?: ""
            }
        } catch (e: IOException) {
            log.info("isNotSuccessful")
            // Handle any exceptions that may occur during the request
            e.printStackTrace()
        }

        return ""
    }


    fun verifyCredentials(credential: Credential): User? {
        log.info("verifyCredentials ${credential.token}")
        val gtr = makeGetRequest("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=${credential.token}")
        if (gtr == null) {
            log.info("GST verification failed.")
            return null
        }

        val gson = Gson()
        val response =  gson.fromJson(gtr, GoogleTokenResponse::class.java)

        var user = userService.getUserByEmail(response.email)
        if (user == null) {
            log.info("User ${response.email} is not found, registering user")
            user = userService.createUser(User(name = response.name, mobile = null , email = response.email,
                createdOn = LocalDateTime.now(), student = true, library = true))
        }

        return user
    }


    fun getUserProfile(user: User, app: String): Profile? {

        val token: String? = tokenService.generate(app!!, user.id, user.name, user.email!!)
        if (token == null) {
            log.info("Unable to generate token for ${user.email}")
            return null
        }

        return Profile(
            token = token,
            email = user.email,
            name = user.name,
            mobile = user.mobile ?: "",
            library = user.library,
            student = user.student
        )
    }
}
