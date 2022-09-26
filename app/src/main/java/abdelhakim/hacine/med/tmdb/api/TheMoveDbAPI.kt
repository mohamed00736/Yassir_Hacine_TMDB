package abdelhakim.hacine.med.tmdb.api

import abdelhakim.hacine.med.tmdb.utils.Constants.Companion.API_KEY
import abdelhakim.hacine.med.tmdb.models.MovieList
import retrofit2.Response
import retrofit2.http.*

interface TheMoveDbAPI {

    @GET("3/discover/movie")
    suspend fun getTrendingMovies(@Query("api_key") api_key: String = API_KEY): Response<MovieList>
    suspend fun getTrendingMovies2(@Query("api_key") api_key: String = API_KEY ,@Query("page") page: Int): Response<MovieList>

//    @GET("/3/movies/{movie_id}")
//    suspend fun getMovieById(
//        @Query("movie_id")
//        movie_id: String,
//        @Query("api_key")
//        apiKey: String = API_KEY
//    ): Response<MovieList>
}