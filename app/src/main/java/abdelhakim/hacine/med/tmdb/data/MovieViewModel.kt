package abdelhakim.hacine.med.tmdb.data

import abdelhakim.hacine.med.tmdb.models.MovieList
import abdelhakim.hacine.med.tmdb.utils.Resource
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieViewModel(val movieRepository: MovieRepository) : ViewModel(){
    private  val TAG = "MovieViewModel"
    val movieList : MutableLiveData<Resource<MovieList>> = MutableLiveData()
    //private val _myUiState = MutableLiveData<Response<MovieList>>()
    //var myUiState: LiveData<Response<MovieList>> = _myUiState

    init {
        viewModelScope.launch {

            //its better to use viewmodelscope
           withContext(Dispatchers.IO){
               // Log.d(TAG, "getMovieList: "+ movieList.value.toString())
               val response = movieRepository.getMovieList()

              movieList.postValue(handleMovieList(response))



             //var response2 = movieRepository.getMovieList2(1)
             //            movieList.value = response2
            // Log.d(TAG, "getMovieListt: "+_myUiState.toString())
           }
        }
    }
    private fun handleMovieList(response: Response<MovieList>) : Resource<MovieList> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}