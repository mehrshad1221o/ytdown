package com.pinky.downloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.chaquo.python.PyException
import com.chaquo.python.Python
import com.pinky.downloader.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fetchButton.setOnClickListener {
            val url = binding.urlEditText.text.toString()
            if (url.isNotBlank()) {
                fetchVideoInfo(url)
            } else {
                binding.urlInputLayout.error = "Please enter a URL"
            }
        }
    }

    private fun fetchVideoInfo(url: String) {
        binding.urlInputLayout.error = null
        // Show loading indicator - for now, just disable the button
        binding.fetchButton.isEnabled = false
        binding.fetchButton.text = "Fetching..."

        lifecycleScope.launch(Dispatchers.Main) {
            val videoInfoJson = withContext(Dispatchers.IO) {
                try {
                    val python = Python.getInstance()
                    val youtubeModule = python.getModule("main")
                    youtubeModule.callAttr("get_video_info", url).toString()
                } catch (e: PyException) {
                    Log.e("MainActivity", "Python error: ${e.message}")
                    "{\"error\": true, \"message\": \"${e.message}\"}"
                } catch (e: Exception) {
                    Log.e("MainActivity", "Error calling Python", e)
                    "{\"error\": true, \"message\": \"An unexpected error occurred: ${e.message}\"}"
                }
            }

            // Restore button state
            binding.fetchButton.isEnabled = true
            binding.fetchButton.text = getString(R.string.fetch_formats)

            try {
                val jsonObject = JSONObject(videoInfoJson)
                if (jsonObject.has("error")) {
                    val errorMessage = jsonObject.getString("message")
                    Log.e("MainActivity", "Error from yt-dlp: $errorMessage")
                    Toast.makeText(this@MainActivity, "Error: $errorMessage", Toast.LENGTH_LONG).show()
                } else {
                    val title = jsonObject.optString("title", "No title")
                    binding.urlInputLayout.helperText = title
                    Log.d("MainActivity", "Successfully got info for: $title")
                    Toast.makeText(this@MainActivity, "Success: $title", Toast.LENGTH_SHORT).show()
                    // Here I would parse the formats and update the RecyclerView
                    // For now, logging the full JSON
                    Log.d("MainActivity", "Full Info: $videoInfoJson")
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "JSON Parsing error", e)
                Toast.makeText(this@MainActivity, "Failed to parse video information.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
