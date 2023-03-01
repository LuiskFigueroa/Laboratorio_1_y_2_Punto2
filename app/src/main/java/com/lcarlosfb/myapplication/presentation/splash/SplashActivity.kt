package com.lcarlosfb.myapplication.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lcarlosfb.myapplication.databinding.ActivitySplashBinding
import com.lcarlosfb.myapplication.presentation.account.RegisterAccountActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

	private lateinit var splashBinding: ActivitySplashBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		splashBinding = ActivitySplashBinding.inflate(layoutInflater)
		val view = splashBinding.root
		setContentView(view)

	val timer = Timer()
	timer.schedule(timerTask {
		val intent = Intent(this@SplashActivity, RegisterAccountActivity::class.java)
		startActivity(intent)
		finish()
	}, 2000
	)

	}
}