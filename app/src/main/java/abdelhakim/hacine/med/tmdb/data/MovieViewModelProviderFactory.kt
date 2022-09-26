package abdelhakim.hacine.med.tmdb.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieViewModelProviderFactory (
    val movieRepository: MovieRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieViewModel(movieRepository) as T
        }
}