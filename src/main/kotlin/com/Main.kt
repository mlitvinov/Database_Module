package com

import com.Data.Getters.getPerson
import com.Data.Getters.getTask
import com.Data.Setters.createTask
import com.Data.Setters.dropTask
import com.Data.Setters.setChat
import com.Data.Setters.setContent
import com.Data.Setters.setName
import com.Data.Setters.setPerformer
import com.Data.Setters.setStatus
import org.jetbrains.exposed.sql.Database


fun main() {
    initConnection()
    dropTask(1)
    createTask(
        creatorName = "Person", performerName = "Person", status = "Done",
        taskName = "someTask", content = "someContent", chat = "1686228"
    )
    setStatus(1,"update")
    setPerformer(1,"update")
    setContent(1,"update")
    setChat(1,"update")
    setName(1,"update")
    println(getTask(1)?.status)
    println(getPerson(1)?.name)
}

fun initConnection() {
    Database.connect(
        "jdbc:postgresql://85.192.33.28/postgres",
        driver = "org.postgresql.Driver",
        user = "",
        password = ""
    )
}


