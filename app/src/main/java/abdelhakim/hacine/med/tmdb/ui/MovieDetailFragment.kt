package abdelhakim.hacine.med.tmdb.ui

import abdelhakim.hacine.med.tmdb.R
import abdelhakim.hacine.med.tmdb.utils.Constants.Companion.Image_baseURL
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import abdelhakim.hacine.med.tmdb.databinding.*
import android.widget.ImageView

import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {
    val args :MovieDetailFragmentArgs by navArgs()
            private lateinit var binding: FragmentMovieDetailBinding
    private  val TAG = "MovieDetailFragment"

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
              var binding: FragmentMovieDetailBinding  = FragmentMovieDetailBinding.inflate(layoutInflater)
            val title = getView()?.findViewById<TextView>(R.id.detailtitle)
            val overview = getView()?.findViewById<TextView>(R.id.detailoverview)
            val year = getView()?.findViewById<TextView>(R.id.detailyear)
            val poster = getView()?.findViewById<ImageView>(R.id.posterImgDetail)

            // the view binding did not work for some reason

             title?.text= args.title
            overview?.text=args.overview
            year?.text=args.year
            val posterlink =Image_baseURL + args.poster
            Picasso.get().load(posterlink).into(poster)


           // Log.d(TAG, "onViewCreated: "+posterlink.toString())



        }
    }



