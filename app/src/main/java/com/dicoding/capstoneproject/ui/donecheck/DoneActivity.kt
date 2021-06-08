package com.dicoding.capstoneproject.ui.donecheck

import android.content.Intent
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.dicoding.capstoneproject.MainActivity
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.databinding.ActivityDoneBinding


class DoneActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawable = binding.animDone.drawable

        val avd: AnimatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(this, R.drawable.animation_done)!!
        binding.animDone.setImageDrawable(avd)

        (binding.animDone.drawable as? AnimatedVectorDrawableCompat)?.start()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }, 2500)

    }
}