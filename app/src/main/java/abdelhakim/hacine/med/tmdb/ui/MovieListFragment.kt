package abdelhakim.hacine.med.tmdb.ui

import abdelhakim.hacine.med.tmdb.data.MovieRepository
import abdelhakim.hacine.med.tmdb.data.MovieViewModel
import abdelhakim.hacine.med.tmdb.data.MovieViewModelProviderFactory
import abdelhakim.hacine.med.tmdb.R
import abdelhakim.hacine.med.tmdb.adapter.MovieAdapter
import abdelhakim.hacine.med.tmdb.databinding.FragmentMovieListBinding
import abdelhakim.hacine.med.tmdb.utils.Resource
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MovieListFragment : Fragment(R.layout.fragment_movie_list) {
    private  val TAG = "MovieListFragment"
    lateinit var viewModel : MovieViewModel
    lateinit var movieAdapter: MovieAdapter
lateinit var  binding: FragmentMovieListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        setup()
        val movieRepository = MovieRepository()
        val viewModelProviderFactory = MovieViewModelProviderFactory(movieRepository)
        this.viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MovieViewModel::class.java)




        movieAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {

                //her iam not using the second endpoint
                // since the details and overview of the moview are availabe
                // in the first endpoint , so there is no need for more network calls on the details fragment
                // its enough to pass the data  bundeled via args

                putSerializable("title", it.title)
                putSerializable("year", it.releaseDate)
                putSerializable("poster", it.posterPath)
                putSerializable("overview", it.overview)
            }
            findNavController().navigate(
                R.id.action_movieListFragment_to_movieDetailFragment,
                bundle // Inflate the layout for this fragment
            )
        }


                viewModel.movieList.observe(viewLifecycleOwner , Observer {
                response ->

                    when(response) {

                     is Resource.Success -> {

                     response.data?.let { newsResponse ->


                         movieAdapter.differ.submitList(response.data.results)
                       // Log.d(TAG, "onCreaterrr: " + newsResponse.results[0])

                    }
                }
                is Resource.Error -> {


                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    Log.e(TAG, "Loaing: ")
                }
            }


        })

    }

    private fun setup() {

        movieAdapter = context?.let { MovieAdapter(it) }!!

        val recyclerView = getView()?.findViewById<RecyclerView>(R.id.pp)
        recyclerView?.apply {
            adapter = movieAdapter
            recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

    }
}