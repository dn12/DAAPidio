

package net.github.dn12.dnapidio.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.github.dn12.dnapidio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}
