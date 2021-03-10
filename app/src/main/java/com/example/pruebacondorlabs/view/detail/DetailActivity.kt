package com.example.pruebacondorlabs.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.pruebacondorlabs.R
import com.example.pruebacondorlabs.application.MyApplication
import com.example.pruebacondorlabs.db.AppDatabase
import com.example.pruebacondorlabs.db.model.Team
import com.example.pruebacondorlabs.util.TEAM
import com.example.pruebacondorlabs.util.ViewModelFactory
import com.example.pruebacondorlabs.viewmodel.DetailActivityViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject


class DetailActivity : AppCompatActivity(), View.OnClickListener {

    var appDatabase: AppDatabase? = null
        @Inject set
    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var detailActivityViewModel: DetailActivityViewModel? = null

    private val team by lazy { intent.getSerializableExtra(TEAM) as Team }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        (applicationContext as MyApplication).getComponent()?.inject(this)

        detailActivityViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailActivityViewModel::class.java)

        val name = "${getString(R.string.name)}: ${team.strTeam}"
        val yearFormed = "${getString(R.string.yearFormed)}: ${team.intFormedYear}"

        Glide.with(this).load(team.strTeamJersey).fitCenter().into(ivTeamJerseyDescription)
        Glide.with(this).load(team.strTeamBadge).fitCenter().into(ivTeamBadgeDescription)
        tvNameTeamDescription.text = name
        tvFormedTeamDescription.text = yearFormed
        tvFoundDescription.text = team.strDescriptionEs
        socialMediaIsVisible(team.strWebsite, 0)
        socialMediaIsVisible(team.strFacebook, 1)
        socialMediaIsVisible(team.strTwitter, 2)
        socialMediaIsVisible(team.strInstagram, 3)
        socialMediaIsVisible(team.strYoutube, 4)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivGlobe -> intentSocialMedia(team.strWebsite, 0)
            R.id.ivFacebook -> intentSocialMedia(team.strFacebook, 1)
            R.id.ivTwitter -> intentSocialMedia(team.strTwitter, 2)
            R.id.ivInstagram -> intentSocialMedia(team.strInstagram, 3)
            R.id.ivYoutube -> intentSocialMedia(team.strYoutube, 4)
        }
    }

    private fun intentSocialMedia(socialMedia: String?, case: Int) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://$socialMedia")
        try {
            when (case) {
                2 -> intent.setPackage("com.twitter.android")
                3 -> intent.setPackage("com.instagram.android")
                4 -> intent.setPackage("com.google.android.youtube")
            }
            startActivity(intent)
        } catch (e: Exception) {
            Log.e(DetailActivity::class.java.name, "No social media found")
            intent.setPackage(null)
            startActivity(intent)
        }
    }

    private fun socialMediaIsVisible(socialMedia: String?, case: Int) {
        if (socialMedia.isNullOrEmpty())
            when (case) {
                0 -> ivGlobe.visibility = View.GONE
                1 -> ivFacebook.visibility = View.GONE
                2 -> ivTwitter.visibility = View.GONE
                3 -> ivInstagram.visibility = View.GONE
                4 -> ivYoutube.visibility = View.GONE
            }
    }
}