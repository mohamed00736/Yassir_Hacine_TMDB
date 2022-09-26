package abdelhakim.hacine.med.tmdb.data



import abdelhakim.hacine.med.tmdb.api.RetrofitInstance

class MovieRepository {



    suspend fun getMovieList() =  RetrofitInstance.api.getTrendingMovies()


}