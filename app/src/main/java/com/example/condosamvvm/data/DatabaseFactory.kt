package com.example.condosamvvm.data


import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction


object DatabaseFactory {

    fun init() {
        val driverClassName = "org.postgresql.Driver"
        val jdbcURL = "jdbc:postgresql://137.184.120.127:5432/sigcon"
        val user = "modulo4"
        val password = "modulo4"
        Database.connect(jdbcURL, driverClassName, user, password)
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}