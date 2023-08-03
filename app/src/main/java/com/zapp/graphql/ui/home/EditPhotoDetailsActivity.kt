package com.zapp.graphql.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.zapp.graphql.R
import com.zapp.graphql.data.dto.Photos
import com.zapp.graphql.databinding.ActivityEditPhotoDetailsBinding
import com.zapp.graphql.utility.loadUrl
import com.zapp.graphql.utility.notShow
import com.zapp.graphql.utility.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPhotoDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditPhotoDetailsBinding
    private val viewModel by viewModels<HomeViewModel>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPhotoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the Intent that started this activity
        val intent = intent
        // Retrieve the data from the Intent using getXXXExtra()
        if (intent != null) {
            val edit = intent.getBooleanExtra("edit",true)
            viewModel.photoUpdated.observe(this) {
                when (it) {
                    is DeleteUiState.Loading -> {
//                        binding.loading.show()
                    }
                    is DeleteUiState.Error -> {
                        Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is DeleteUiState.Deleted -> {
                        binding.loading.notShow()
                        Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
                        finish()

                    }
                }
            }
            viewModel.photoCreated.observe(this) {
                when (it) {
                    is DeleteUiState.Loading -> {
//                        binding.loading.show()
                    }
                    is DeleteUiState.Error -> {
                        Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is DeleteUiState.Deleted -> {
                        binding.loading.notShow()
                        Toast.makeText(this, "Successfully Created", Toast.LENGTH_SHORT).show()
                        finish()

                    }
                }
            }
            if(edit){
              val photos = intent.getSerializableExtra("photo") as Photos

                binding.imageView.loadUrl(photos.url)
                binding.textView.setText(photos.title)
                binding.urlText.setText(photos.url)
                binding.button.text = "Edit"
                binding.button.setOnClickListener{
                    if(binding.button.text.equals("Edit")){
                        if(binding.textView.text!=null){
                            binding.loading.show()
                            viewModel.updatePhoto(photos.id!!,binding.textView.text.toString())
                        }
                    }

                    }
                }
            else{

                binding.button.text = "Create"
                binding.button.setOnClickListener{
                    if(binding.button.text.equals("Create")){
                        if(binding.textView.text!=null && binding.urlText.text!=null){
                            binding.loading.show()
                            viewModel.createPhoto(binding.textView.text.toString(),binding.urlText.text.toString(),binding.urlText.text.toString())


                        }
                    }
                }


            }
            }






}}