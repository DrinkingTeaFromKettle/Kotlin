package lab.lab01.masterand.viewModels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import lab.lab01.masterand.containers.MasterAndContainer

object AppViewModelProvider {
     val Factory = viewModelFactory {
        initializer {
            ProfileViewModel(masterAndContainer().container.usersRepository)
        }
         initializer {
             EntryScreenViewModel(masterAndContainer().container.usersRepository)
         }
         initializer {
             ResultsViewModel(masterAndContainer().container.userScoreRepository)
         }
         initializer {
             GameViewModel(masterAndContainer().container.userScoreRepository)
         }
    }
}
fun CreationExtras.masterAndContainer(): MasterAndContainer =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as
            MasterAndContainer)