package com.engin.eagerbeaver.presentation.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.engin.eagerbeaver.common.CustomSnackBar
import com.engin.eagerbeaver.common.SnackType
import com.engin.eagerbeaver.common.presentation.component.LoadingDialog
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.databinding.FragmentHomeBinding
import com.engin.eagerbeaver.presentation.main.home.adpter.CategoryAdapter
import com.engin.eagerbeaver.common.presentation.adapter.JobAdvertAdapter
import com.engin.eagerbeaver.presentation.main.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryAdapter:CategoryAdapter
    private lateinit var advertAdapter: JobAdvertAdapter
    private lateinit var navController: NavController
    private val viewModel:HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observe()
    }

    private fun init() {
        navController = findNavController()
        categoryAdapter = CategoryAdapter(categories =  emptyList(), listener = viewModel.categoryCardListener)
        advertAdapter = JobAdvertAdapter(adverts = emptyList(), listener = viewModel.jobCardListener)
        binding.categoriesRv.adapter = categoryAdapter
        binding.categoriesRv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.latestJobRv.adapter = advertAdapter
        binding.latestJobRv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.viewAllLatestJob.setOnClickListener { viewModel.navigateJobs() }
    }

    private fun observe(){
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect{
                if(it.isLoading){
                    LoadingDialog.showLoading(requireContext(),false)
                    return@collect
                }
                else
                    LoadingDialog.hideLoading()
                if(it.categories.isNotEmpty())
                    categoryAdapter.updateData(it.categories)
                if(it.lastAdverts.isNotEmpty())
                    advertAdapter.updateData(it.lastAdverts)
                it.shouldNavigate?.let {route->
                    when(route){
                        is Route.Jobs -> {
                            val action = HomeFragmentDirections.actionHomeFragmentToSearchFlow(route.category_id)
                            navController.navigate(action)
                            viewModel.navigated()
                        }
                        is Route.JobDetail -> {
                            val action = HomeFragmentDirections.actionGlobalJobDetailFragment(route.jobId, comingFrom = Route.Home().route)
                            navController.navigate(action)
                            viewModel.navigated()
                        }
                        else -> {}
                    }
                }
                it.errorMessage?.let {uiText->
                    CustomSnackBar.make(
                        context = requireContext(),
                        view = requireView(),
                        text = uiText.asString(requireContext()),
                        type = SnackType.ERROR
                    ).show()
                    viewModel.messageShown()
                }
                it.warningMessage?.let {uiText->
                    CustomSnackBar.make(
                        context = requireContext(),
                        view = requireView(),
                        text = uiText.asString(requireContext()),
                        type = SnackType.ERROR
                    ).show()
                    viewModel.messageShown()
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}