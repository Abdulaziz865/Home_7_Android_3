package com.example.home_7_android_3.hilt.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.home_7_android_3.databinding.FragmentHomeBinding
import com.example.home_7_android_3.hilt.ui.adapters.HomeAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel by viewModels<HomeViewModel>()
    private var homeAdapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupObserve()
    }

    private fun initialize() {
        binding?.rvPhoto?.adapter = homeAdapter
    }

    private fun setupObserve() {
        if (checkForInternet(requireContext())) {
            viewModel.homeModelLiveData.observe(viewLifecycleOwner) {
                homeAdapter.submitList(it)
                Toast.makeText(requireContext(), "Connection restored", Toast.LENGTH_SHORT).show()
            }
            viewModel.errorItemLiveData.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        } else {
            viewModel.localItemLiveData?.observe(viewLifecycleOwner) {
                homeAdapter.submitList(it)
                Toast.makeText(requireContext(), "Connection interrupted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
