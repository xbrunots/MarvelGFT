package br.com.gft.marvel.platform.extension

import java.security.NoSuchAlgorithmException

fun String.toMd5(): String {
    return try {
        val MD5 = "MD5"
        // Create MD5 Hash
        val digest = java.security.MessageDigest
            .getInstance(MD5)
        digest.update(this.toByteArray())
        val messageDigest = digest.digest()

        // Create Hex String
        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2)
                h = "0$h"
            hexString.append(h)
        }
        return hexString.toString()

    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
        return ""
    }
}