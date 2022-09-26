package abdelhakim.hacine.med.tmdb.adapter

import abdelhakim.hacine.med.tmdb.utils.Constants.Companion.Image_baseURL
import abdelhakim.hacine.med.tmdb.models.Movie
import abdelhakim.hacine.med.tmdb.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(context : Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var adaptercontext = context
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    private val differCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return  MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cardviewitem,
                parent ,
                false
            )
        )
    }
    private var onItemClickListener: ((Movie) -> Unit)? = null

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie_ = differ.currentList[position]

        val movie_title = holder.itemView.findViewById<TextView>(R.id.rcname2)
        val movie_year = holder.itemView.findViewById<TextView>(R.id.rctime2)
        val movie_image = holder.itemView.findViewById<ImageView>(R.id.rcimg2)

         movie_title.text= movie_.title.toString()
         movie_year.text= movie_.releaseDate.toString()
        Glide.with(adaptercontext).load(Image_baseURL +movie_.posterPath).centerInside().into(movie_image)


        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(movie_) }

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }


}