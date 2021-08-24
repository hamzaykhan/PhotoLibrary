package com.hamza.photolibrary.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hamza.photolibrary.R
import com.hamza.photolibrary.ui.base.BaseFragment
import com.hamza.photolibrary.databinding.HomeFragmentBinding
import com.hamza.photolibrary.utils.gone
import com.hamza.photolibrary.utils.showSnack
import com.hamza.photolibrary.utils.showToast
import com.hamza.photolibrary.utils.visible

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var bi: HomeFragmentBinding

    lateinit var photosAdapter: PhotosAdapter

    var snackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bi = HomeFragmentBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelProvider).get(HomeViewModel::class.java)

        setupViews()
        initObservations()
    }

    fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is LoadingState -> {
                    bi.recyclerPopularPhotos.gone()
                    bi.progressPhotos.visible()
                }

                is LoadingNextPageState -> {
                    showToast(getString(R.string.message_load_photos_str))
                }

                is ContentState -> {
                    bi.recyclerPopularPhotos.visible()
                    bi.progressPhotos.gone()
                }

                is ErrorState -> {
                    bi.progressPhotos.gone()
                    bi.nestedScrollView.showSnack(state.message, getString(R.string.action_retry_str)) {
                        viewModel.retry()
                    }
                }

                is ErrorNextPageState -> {
                    bi.nestedScrollView.showSnack(state.message, getString(R.string.action_retry_str)) {
                        viewModel.retry()
                    }
                }
            }
        }

        viewModel.photosListLiveData.observe(viewLifecycleOwner) { photos ->
            photosAdapter.updateItems(photos)
        }
    }

    fun setupViews() {
        context?.let { ctx ->
            // Photos RecyclerView
            photosAdapter = PhotosAdapter() { photo, position ->
                var bundle = bundleOf("photo" to photo)
                findNavController().navigate(R.id.action_homeFragment_to_pictureDetailsFragment, bundle)
            }
            photosAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            bi.recyclerPopularPhotos.adapter = photosAdapter

            // NestedScrollView
            bi.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    viewModel.loadMorePhotos()
                }
            }
        }
    }

}