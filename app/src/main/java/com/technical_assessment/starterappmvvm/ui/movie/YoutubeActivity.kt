package com.technical_assessment.starterappmvvm.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.technical_assessment.starterappmvvm.BuildConfig
import com.technical_assessment.starterappmvvm.R
import com.technical_assessment.starterappmvvm.databinding.ActivityYoutubeBinding

class YoutubeActivity : YouTubeBaseActivity() {
    companion object {
        const val KEY_VIDEO = "KEY_VIDEO"
    }
    private lateinit var binding:ActivityYoutubeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var key:String? = intent.getStringExtra(KEY_VIDEO)

        if (key != null){
            binding.ypv.initialize(BuildConfig.YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener{
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    p1?.cueVideo(intent.getStringExtra(KEY_VIDEO))
                    p1?.setFullscreen(true)
                    p1?.setPlayerStateChangeListener(object : YouTubePlayer.PlayerStateChangeListener{
                        override fun onLoading() {

                        }

                        override fun onLoaded(p0: String?) {
                            p1.play()
                        }

                        override fun onAdStarted() {

                        }

                        override fun onVideoStarted() {

                        }

                        override fun onVideoEnded() {

                        }

                        override fun onError(p0: YouTubePlayer.ErrorReason?) {

                        }

                    })
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Log.e("onInitializationFailure", "${p1?.name} dan ${p1?.isUserRecoverableError}")
                    Snackbar.make(binding.ypv, "Oops.. something happen, please try again later", Snackbar.LENGTH_LONG).show()
                }
            })
        }else{
            Snackbar.make(binding.ypv, "Key video is missiong", Snackbar.LENGTH_LONG).show()
        }
    }
}