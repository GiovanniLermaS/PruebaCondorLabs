package com.example.pruebacondorlabs.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pruebacondorlabs.R
import com.example.pruebacondorlabs.application.MyApplication
import com.example.pruebacondorlabs.db.AppDatabase
import com.example.pruebacondorlabs.db.model.Team
import com.example.pruebacondorlabs.util.*
import com.example.pruebacondorlabs.view.detail.adapter.EventAdapter
import com.example.pruebacondorlabs.viewmodel.DetailActivityViewModel
import com.example.pruebamercadolibre.db.Executor
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.launch
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
        showProgress(this, isAlertInit = true)
        setContentView(R.layout.activity_detail)
        (applicationContext as MyApplication).getComponent()?.inject(this)

        detailActivityViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailActivityViewModel::class.java)
        lifecycleScope.launch {
            if (appDatabase?.teamDao()?.getTeamById(team.idTeam) == null) {
                ivAddFavorite.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_add,
                        null
                    )
                )
            } else {
                ivAddFavorite.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_check,
                        null
                    )
                )
            }
        }
        consumeEventByTeam()
        setPrincipalData()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivAddFavorite -> addOrDeleteFromFavorites()
            R.id.ivGlobe -> intentSocialMedia(team.strWebsite, 0)
            R.id.ivFacebook -> intentSocialMedia(team.strFacebook, 1)
            R.id.ivTwitter -> intentSocialMedia(team.strTwitter, 2)
            R.id.ivInstagram -> intentSocialMedia(team.strInstagram, 3)
            R.id.ivYoutube -> intentSocialMedia(team.strYoutube, 4)
        }
    }

    private fun addOrDeleteFromFavorites() {
        lifecycleScope.launch {
            if (appDatabase?.teamDao()?.getTeamById(team.idTeam) == null) {
                Executor.iOThread { appDatabase?.teamDao()?.setTeam(team) }
                ivAddFavorite.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_check,
                        null
                    )
                )
                Toast.makeText(
                    this@DetailActivity,
                    getString(R.string.addedToFavorite),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                appDatabase?.teamDao()?.deleteTeamById(team.idTeam)
                ivAddFavorite.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_add,
                        null
                    )
                )
                Toast.makeText(
                    this@DetailActivity,
                    getString(R.string.deleteFromFavorite),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun consumeEventByTeam() {
        detailActivityViewModel?.getEventsByTeamId(team.idTeam.toString())
        detailActivityViewModel?.getSuccessDetail()?.observe(this) { listResults ->
            listResults.results.reverse()
            rvListEvents.adapter = EventAdapter(this, listResults.results)
            showProgress(this, isAlertInit = false)
        }
        detailActivityViewModel?.getErrorDetail()?.observe(this) { message ->
            Log.e("Error consume service", message!!)
            showProgress(this, isAlertInit = false)
        }
    }

    private fun setPrincipalData() {
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

    private fun intentSocialMedia(socialMedia: String?, case: Int) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://$socialMedia")
        try {
            when (case) {
                2 -> intent.setPackage(PACKAGE_TWITTER)
                3 -> intent.setPackage(PACKAGE_INSTAGRAM)
                4 -> intent.setPackage(PACKAGE_YOUTUBE)
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