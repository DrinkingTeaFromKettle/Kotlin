package lab.lab01.masterand.containers

import lab.lab01.masterand.repositories.UsersRepository
import lab.lab01.masterand.repositories.UsersScoreRepository

interface AppContainer {
    val usersRepository: UsersRepository
    val userScoreRepository: UsersScoreRepository
}