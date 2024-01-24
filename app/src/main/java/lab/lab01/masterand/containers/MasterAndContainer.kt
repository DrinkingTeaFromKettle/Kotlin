package lab.lab01.masterand.containers

import android.app.Application

class MasterAndContainer : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
            container = AppDataContainer(this)


    }
}