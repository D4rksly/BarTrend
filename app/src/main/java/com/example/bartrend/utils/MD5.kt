package utils

import java.math.BigInteger
import java.security.MessageDigest

class MD5 {
    companion object {
        fun encrypt(input: String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}