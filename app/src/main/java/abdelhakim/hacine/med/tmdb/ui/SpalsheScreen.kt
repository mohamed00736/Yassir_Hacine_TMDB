package abdelhakim.hacine.med.tmdb

import abdelhakim.hacine.med.tmdb.ui.MainActivity
import abdelhakim.hacine.med.tmdb.utils.ConnectivityStatus
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

class SplashScreen : AppCompatActivity() {
    private lateinit var lottie: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalshe_screen)
         lottie = findViewById<LottieAnimationView>(R.id.animationView)

            checkConnectivity()

    }

    private fun checkConnectivity(){
        val connectivity = ConnectivityStatus(this)
        connectivity.observe(this, Observer {
                isConnected ->
            if(isConnected){

                lottie.setAnimation(R.raw.data)
                lottie.playAnimation()
                Handler(Looper.myLooper()!!).postDelayed({

                    startNextActivity()
                    finish()
                },2000)
            }else{
                lottie.setAnimation(R.raw.nodata)
                lottie.repeatCount = LottieDrawable.INFINITE
                lottie.playAnimation()
              Toast.makeText(this , "No Internet" , Toast.LENGTH_LONG).show()
                //binding.internetStatusTV.setBackgroundColor(ContextCompat.getColor(this,R.color.teal_700))


            }
        })
    }

    private fun startNextActivity(){
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
    }
}