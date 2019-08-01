package br.com.gft.data.source.local

import android.util.Log
import br.com.gft.data.R
import br.com.gft.data.platform.context.GlobalContext
import br.com.gft.data.platform.hash.md5
import br.com.gft.data.source.local.sp.SharedPreferenceManager
import com.google.gson.Gson
import io.reactivex.Flowable
import retrofit2.Response

inline fun <reified T : Any> findCacheByRequest(request: String): T? {
    return try {
        val requestKey = request.md5()
        val cache: String? = SharedPreferenceManager.get(requestKey)
        return Gson().fromJson(cache, T::class.java)
    } catch (e: Exception) {
        Log.e(
            GlobalContext.context().getString(R.string.tag_cache_error),
            GlobalContext.context().getString(R.string.cache_error).plus(T::class.qualifiedName)
        )
        null
    }
}

inline fun <reified T : Any> findCacheByModel(): T? {
    return try {
        val cache: String? = SharedPreferenceManager.get(T::class.qualifiedName.toString())
        return Gson().fromJson(cache, T::class.java)
    } catch (e: Exception) {
        Log.e(
            GlobalContext.context().getString(R.string.tag_cache_error),
            GlobalContext.context().getString(R.string.cache_error).plus(T::class.qualifiedName)
        )
        return null
    }
}

inline fun <reified T : Any> findCacheByName(name: String): T? {
    return try {
        val cache: String? = SharedPreferenceManager.get(T::class.qualifiedName.toString())
        return Gson().fromJson(cache, T::class.java)
    } catch (e: Exception) {
        Log.e(
            GlobalContext.context().getString(R.string.tag_cache_error),
            GlobalContext.context().getString(R.string.cache_error).plus(T::class.qualifiedName)
        )
        null
    }
}

inline fun <reified T : Any> cache(data: Any) {
    SharedPreferenceManager.set(T::class.qualifiedName.toString(), Gson().toJson(data))
}

fun cache(name: String, data: Any) {
    SharedPreferenceManager.set(name, Gson().toJson(data))
}