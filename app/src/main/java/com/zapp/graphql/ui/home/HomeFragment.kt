package com.zapp.graphql.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zapp.graphql.data.dto.Photos
import com.zapp.graphql.databinding.FragmentHomeBinding
import com.zapp.graphql.utility.notShow
import com.zapp.graphql.utility.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), HomeAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy { HomeAdapter(emptyList(), this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewState()
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.fabButton.setOnClickListener{
            val intent = Intent(context, EditPhotoDetailsActivity::class.java)

            // Put data in the Intent using putExtra()

            // Put data in the Intent using putExtra()
            intent.putExtra("edit", false)

            // Start the ReceiverActivity

            // Start the ReceiverActivity
            startActivity(intent)

        }
    }

    private fun viewState() {
        viewModel.photo.observe(viewLifecycleOwner) {
            when (it) {
                is HomeUiState.Loading -> {
                    binding.loading.show()
                }
                is HomeUiState.Error -> {
                    Toast.makeText(requireContext(), "Something Went Wrong", Toast.LENGTH_SHORT)
                        .show()
                }
                is HomeUiState.Success -> {
                    binding.loading.notShow()
                    binding.rcvPhotos.adapter = adapter
                    it.data?.let { it1 -> adapter.updateData(it1) }
                }
            }
        }
        viewModel.photoDeleted.observe(viewLifecycleOwner) {
            when (it) {
                is DeleteUiState.Loading -> {
                    binding.loading.show()
                }
                is DeleteUiState.Error -> {
                    Toast.makeText(requireContext(), "Something Went Wrong", Toast.LENGTH_SHORT)
                        .show()
                }
                is DeleteUiState.Deleted -> {
                    binding.loading.notShow()
                    Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show()
                    viewModel.getAllPhotos()

//                    binding.rcvPhotos.adapter = adapter
//                    it.data?.let { it1 -> adapter.updateData(it1) }
                }
            }
        }

    }

    override fun onDelete(photo: Photos) {
        viewModel.deletePhoto(photo.id)

    }

    override fun onEdit(photos: Photos) {
        openEditActivity(photos)

    }

    private fun openEditActivity(photos: Photos) {
        // Create an Intent to start the ReceiverActivity
        // Create an Intent to start the ReceiverActivity
        val intent = Intent(context, EditPhotoDetailsActivity::class.java)

        // Put data in the Intent using putExtra()

        // Put data in the Intent using putExtra()
        intent.putExtra("photo", photos)
        intent.putExtra("edit", true)

        // Start the ReceiverActivity

        // Start the ReceiverActivity
        startActivity(intent)
    }


}