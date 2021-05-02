package com.hamza.photolibrary.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.hamza.photolibrary.base.BaseFragment
import com.hamza.photolibrary.databinding.PictureDetailFragmentBinding
import com.hamza.photolibrary.model.PhotoModel

class PictureDetailFragment : BaseFragment() {

    private lateinit var viewModel: PictureDetailViewModel
    private lateinit var bi: PictureDetailFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bi = PictureDetailFragmentBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelProvider).get(PictureDetailViewModel::class.java)

        var photo = arguments?.getParcelable<PhotoModel>("photo")
        if (photo == null) {
            findNavController().popBackStack()
            return
        }

        setupViews()
        initObservations()

        viewModel.initPhotoModel(photo)
    }

    fun setupViews() {

    }

    fun initObservations() {
        viewModel.photoModelLiveData.observe(viewLifecycleOwner) { photo ->
            bi.photoView.load(photo.urls.full)
        }
    }

}