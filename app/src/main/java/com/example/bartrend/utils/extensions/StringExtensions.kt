package com.example.bartrend.utils.extensions

import java.math.BigInteger
import java.security.MessageDigest
import java.util.regex.Pattern

 val EMAIL_PATTERN: String
    get() = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

fun String.isEmailValid(): Boolean {
    val pattern = Pattern.compile(EMAIL_PATTERN)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.toSHA256(): String {
    val md = MessageDigest.getInstance("SHA-256")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}