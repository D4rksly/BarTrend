package com.example.bartrend.utils

import java.lang.NullPointerException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.full.declaredMembers

@Suppress("unused")
object Connector {

    private var conn: Connection? = null

    private const val USERNAME = "6Y7DlX2Fnq"
    private const val PASSWORD = "UMQ09Ei02b"

    private const val SERVER = "remotemysql.com"
    private const val PORT = "3306"
    private const val DATABASE = "6Y7DlX2Fnq"

    private fun connect(): Boolean {
        val connectionProps = Properties()
        connectionProps["user"] = USERNAME
        connectionProps["password"] = PASSWORD

        return try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://$SERVER:$PORT/$DATABASE",
                connectionProps
            )
            true
        } catch (ex: Exception) {
            ex.printStackTrace()
            false
        }
    }

    @Throws(SQLException::class)
    fun executeQuery(query: String): ResultSet {
        return try {
            if(conn != null || connect()) {
                conn!!.createStatement().executeQuery(query)
            } else {
                throw NullPointerException()
            }
        } catch (ex: NullPointerException) {
            throw NullPointerException("The connector was not instantiated")
        } catch (ex: SQLException) {
            throw ex
        }
    }

    @Throws(SQLException::class)
    fun execute(query: String): Boolean  {
        return try {
            if(conn != null || connect()) {
                conn!!.createStatement().execute(query)
            } else {
                throw NullPointerException()
            }
        } catch (ex: NullPointerException) {
            throw NullPointerException("The connector was not instantiated")
        } catch (ex: SQLException) {
            throw ex
        }
    }

    //region SELECT

    fun select(table: String): ResultSet {
        return executeQuery("SELECT * FROM $table")
    }

    fun <T> select(table: String, comparable: Pair<String, T>): ResultSet {
        return executeQuery("SELECT * FROM $table WHERE ${comparable.first} = \"${comparable.second.toString()}\"")
    }

    fun <T> select(table: String, comparables: HashMap<String, T>): ResultSet {
        var query = "SELECT * FROM $table WHERE "

        comparables.forEach {
            query += "${it.key} = \"${it.value.toString()}\" AND "
        }

        query = query.removeRange(query.length - 5, query.length)

        return executeQuery(query)
    }
    //endregion

    //region INSERT
    fun <T> insert(table: String, insertion: HashMap<String, T>): Boolean {
        var keys = ""
        var values = ""

        insertion.forEach {
            keys += "${it.key}, "
            values += "\"${it.value.toString()}\", "
        }

        if(keys.length >= 2) keys = keys.removeRange(keys.length - 2, keys.length)
        if(values.length >= 2) values = values.removeRange(values.length - 2, values.length)

        return execute("INSERT INTO $table ($keys) VALUES ($values)")
    }

    inline fun <reified T> insert(table: String, insertion: T): Boolean {
        var keys = ""
        var values = ""

        for(it in T::class.declaredMembers) {
            if(it.name == "component1") break

            keys += "${it.name}, "
            values += "\"${it.call(insertion).toString()}\", "
        }

        if(keys.length >= 2) keys = keys.removeRange(keys.length - 2, keys.length)
        if(values.length >= 2) values = values.removeRange(values.length - 2, values.length)

        return execute("INSERT INTO $table ($keys) VALUES ($values)")
    }
    //endregion

    //region UPDATE
    fun <T, U> update(table: String, insertion: HashMap<String, T>, comparable: Pair<String, U>): Boolean {
        var pairs = ""

        insertion.forEach {
            pairs += "${it.key} = \"${it.value.toString()}\", "
        }

        if(pairs.length >= 2) pairs = pairs.removeRange(pairs.length - 2, pairs.length)

        return execute("UPDATE $table SET $pairs WHERE ${comparable.first} = \"${comparable.second.toString()}\"")
    }

    fun <T, U> update(table: String, insertion: HashMap<String, T>, comparables: HashMap<String, U>): Boolean {
        var pairs = ""
        var compare = ""

        insertion.forEach {
            pairs += "${it.key} = \"${it.value.toString()}\", "
        }

        comparables.forEach {
            compare += "${it.key} = \"${it.value.toString()}\" AND "
        }

        if(pairs.length >= 2) pairs = pairs.removeRange(pairs.length - 2, pairs.length)
        if(compare.length >= 5) compare = compare.removeRange(compare.length - 5, compare.length)

        return execute("UPDATE $table SET $pairs WHERE $compare")
    }

    inline fun <reified T, U> update(table: String, insertion: T, comparable: Pair<String, U>): Boolean {
        var pairs = ""

        for(it in T::class.declaredMembers) {
            if(it.name == "component1") break
            pairs += "${it.name} = \"${it.call(insertion).toString()}\", "
        }

        if(pairs.length >= 2) pairs = pairs.removeRange(pairs.length - 2, pairs.length)

        return execute("UPDATE $table SET $pairs WHERE ${comparable.first} = \"${comparable.second.toString()}\"")
    }

    inline fun <reified T, U> update(table: String, insertion: T, comparables:  HashMap<String, U>): Boolean {
        var pairs = ""
        var compare = ""

        for(it in T::class.declaredMembers) {
            if(it.name == "component1") break
            pairs += "${it.name} = \"${it.call(insertion).toString()}\", "
        }

        comparables.forEach {
            compare += "${it.key} = \"${it.value.toString()}\" AND "
        }

        if(pairs.length >= 2) pairs = pairs.removeRange(pairs.length - 2, pairs.length)
        if(compare.length >= 5) compare = compare.removeRange(compare.length - 5, compare.length)

        return execute("UPDATE $table SET $pairs WHERE $compare")
    }
    //endregion

    //region DELETE
    fun <T> delete(table: String, comparable: Pair<String, T>): Boolean {
        return execute("DELETE FROM $table WHERE ${comparable.first} = \"${comparable.second.toString()}\"")
    }

    fun <T> delete(table: String, comparables: HashMap<String, T>): Boolean {
        var query = "DELETE FROM $table WHERE "

        comparables.forEach {
            query += "${it.key} = \"${it.value.toString()}\" AND "
        }

        query = query.removeRange(query.length - 5, query.length)

        return execute(query)
    }
    //endregion
}