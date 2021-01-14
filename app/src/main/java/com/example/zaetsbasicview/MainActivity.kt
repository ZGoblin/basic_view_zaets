package com.example.zaetsbasicview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import com.example.zaetsbasicview.databinding.ActivityMainBinding
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListener()
    }

    private fun setupListener() {
        binding.button.setOnClickListener {
            showProgressBar()
        }
    }

    private fun showProgressBar() {
        try {
            var n = binding.textView.text.toString().toLong()
            val time: Long = (n*1000+1000) / 10
            binding.textView.text = n.inc().toString()
            binding.progressBar.visibility = ProgressBar.VISIBLE
            Timer("ProgBarGoned", false).schedule(time) {
                runOnUiThread {
                    binding.progressBar.visibility = ProgressBar.GONE
                }
            }
        }
        //Если не сможем скастовать стрингу в лонг
        catch (exception: NumberFormatException) {
            binding.textView.text = "0"
            Toast.makeText(this@MainActivity, "Cannot cast ${binding.textView.text} to long", Toast.LENGTH_LONG).show()
        }
        catch (exception: Exception) {
            binding.textView.text = "0"
            Toast.makeText(this@MainActivity, "What happened?", Toast.LENGTH_LONG).show()
        }
    }
}