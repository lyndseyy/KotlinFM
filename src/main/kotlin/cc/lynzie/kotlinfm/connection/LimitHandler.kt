package cc.lynzie.kotlinfm.connection

import okhttp3.Interceptor
import okhttp3.Response

class LimitHandler : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var response = chain.proceed(chain.request())

        if (!response.isSuccessful && response.code == 429) {
            // Wait 2 seconds to try again in the case of being ratelimited.
            Thread.sleep(2000)
            response = chain.proceed(chain.request())
        }

        return response
    }

}