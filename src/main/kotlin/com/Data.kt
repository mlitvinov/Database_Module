package com

import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction


class Data {

    object Tasks : IntIdTable() {
        val name = varchar("name", 50).index()
        val creator = varchar("creator", 50)
        var performer = varchar("performer", 50)
        val status = varchar("status", 50)
        val content = varchar("content", 50)
        val chat = varchar("chat", 50)
    }

    object People : IntIdTable() {
        val name = varchar("name", 50)
    }

    object Setters {
        fun createTask(
            creatorName: String,
            performerName: String,
            status: String,
            taskName: String,
            content: String,
            chat: String
        ) {
            transaction {
                addLogger(StdOutSqlLogger)
                SchemaUtils.create(People, Tasks)

                val creator = Person.new {
                    this.name = creatorName
                }

                val performer = Person.new {
                    this.name = performerName
                }


                Task.new {
                    this.status = status
                    this.performer = performer.name
                    this.creator = creator.name
                    this.taskName = taskName
                    this.content = content
                    this.chat = chat
                }

            }
        }

        fun dropTask(id: Int){
            try {
                transaction {
                    Task[id].delete()
                }
            } catch (e: EntityNotFoundException) {
                System.err.println("id $id не найден")
            }
        }

        fun setStatus(id: Int, status: String) {
            try {
                transaction {
                    Task[id].status = status
                }
            } catch (e: EntityNotFoundException) {
                System.err.println("id $id не найден")
            }
        }

        fun setPerformer(id: Int, performerName: String) {
            try {
                transaction {
                    Task[id].performer = performerName
                }
            } catch (e: EntityNotFoundException) {
                System.err.println("id $id не найден")

            }
        }

        fun setName(id: Int, taskName: String) {
            try {
                transaction {
                    Task[id].taskName = taskName
                }
            } catch (e: EntityNotFoundException) {
                System.err.println("id $id не найден")
            }
        }

        fun setContent(id: Int, content: String) {
            try {
                transaction {
                    Task[id].content = content
                }
            } catch (e: EntityNotFoundException) {
                System.err.println("id $id не найден")
            }
        }

        fun setChat(id: Int, chat: String) {
            try {
                transaction {
                    Task[id].chat = chat
                }
            } catch (e: EntityNotFoundException) {
                System.err.println("id $id не найден")
            }
        }
    }


    object Getters {
        fun getPerson(id: Int): Person? {
            return transaction {
                try {
                    return@transaction Person[id]
                } catch (e: EntityNotFoundException) {
                    System.err.println("id $id не найден")
                }
                return@transaction null
            }
        }

        fun getTask(id: Int): Task? {
            return transaction {
                try {
                    return@transaction Task[id]
                } catch (e: EntityNotFoundException) {
                    System.err.println("id $id не найден")
                }
                return@transaction null
            }
        }
    }
}