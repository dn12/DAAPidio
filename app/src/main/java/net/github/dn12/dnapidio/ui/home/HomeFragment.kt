package net.github.dn12.dnapidio.ui.home

import android.os.Build
import android.os.Bundle
import android.os.ext.SdkExtensions.getExtensionVersion
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import net.github.dn12.dnapidio.R
import net.github.dn12.dnapidio.databinding.FragmentHomeBinding
import org.koin.android.ext.android.inject


private const val ANDROID_R_REQUIRED_EXTENSION_VERSION = 2

class HomeFragment : Fragment() {


    object PhotoPickerAvailabilityChecker {

        fun isPhotoPickerAvailable(): Boolean {
            return when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> true
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                    getExtensionVersion(Build.VERSION_CODES.R) >= ANDROID_R_REQUIRED_EXTENSION_VERSION
                }

                else -> false
            }
        }
    }

    private val viewModel: HomeViewModel by inject()

    private lateinit var binding: FragmentHomeBinding


    private val snackbarLoading: Snackbar by lazy {
        Snackbar.make(binding.root, R.string.location_loading, Snackbar.LENGTH_INDEFINITE)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
    }

    private val snackbarError: Snackbar by lazy {
        Snackbar.make(
            binding.root,
            getString(R.string.message_general_error),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        binding.btScan.setOnClickListener {
//            if (BuildConfig.GALLERY)
//                pickFromGallery()
//            else
//                takePhoto()
//        }
//
//        binding.btCalculate.setOnClickListener {
//            viewModel.calculate(binding.etEquation.text.toString())
//        }
//
//
//        viewModel.equationResultLiveData.observeForever {
//            binding.tvResult.text = it
//        }
//        viewModel.loadingStateLiveData.observeForever {
//            binding.progressBar.isVisible= it
//        }
    }



}
