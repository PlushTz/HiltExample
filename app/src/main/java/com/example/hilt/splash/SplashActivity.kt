package com.example.hilt.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hilt.main.MainActivity

/**
 * Desc:
 * @author lijt
 * Created on 2022/10/11 15:44
 * Email: lijt@eetrust.com
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}