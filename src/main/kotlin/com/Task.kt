package com

import com.Data.Tasks
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Task(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Task>(Tasks)

    var taskName by Tasks.name
    var content by Tasks.content
    var status by Tasks.status
    var chat by Tasks.chat
    var creator by Tasks.creator
    var performer by Tasks.performer
}