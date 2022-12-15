package com.engin.eagerbeaver.presentation.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.CustomSnackBar
import com.engin.eagerbeaver.common.SnackType
import com.engin.eagerbeaver.common.presentation.adapter.JobAdvertAdapter
import com.engin.eagerbeaver.common.presentation.component.LoadingDialog
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.databinding.FragmentSearchBinding
import com.engin.eagerbeaver.presentation.main.search.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var jobAdvertAdapter: JobAdvertAdapter
    private lateinit var  navController:NavController
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { currentState ->
                if (currentState.isLoading) {
                    LoadingDialog.showLoading(requireContext(), false)
                    return@collect
                } else
                    LoadingDialog.hideLoading()
                currentState.errorMessage?.let {
                    CustomSnackBar.make(
                        requireContext(),
                        requireView(),
                        it.asString(requireContext()),
                        type = SnackType.ERROR
                    ).show()
                }
                if (currentState.jobsList.isNotEmpty())
                    jobAdvertAdapter.updateData(currentState.jobsList)
                currentState.shouldNavigate?.let { route->
                    when(route){
                        is Route.JobDetail -> {
                            goJobDetail( route.jobId)
                        }
                        else->{}
                    }
                }
            }
        }
    }

    private fun goJobDetail(jobId:Long) {
        val bundle = bundleOf("job_id" to jobId)
        navController.navigate(R.id.action_global_jobDetailFragment,bundle)
    }

    private fun init() {
        navController = findNavController()
        jobAdvertAdapter = JobAdvertAdapter(emptyList(),viewModel)
        binding.jobsSearchRv.adapter = jobAdvertAdapter
        binding.jobsSearchRv.layoutManager = LinearLayoutManager(requireContext())
    }


}