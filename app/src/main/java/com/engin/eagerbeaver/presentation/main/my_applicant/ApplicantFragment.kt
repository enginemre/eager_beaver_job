package com.engin.eagerbeaver.presentation.main.my_applicant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.CustomSnackBar
import com.engin.eagerbeaver.common.SnackType
import com.engin.eagerbeaver.common.presentation.adapter.JobAdvertAdapter
import com.engin.eagerbeaver.common.presentation.component.LoadingDialog
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.databinding.FragmentApplicantBinding
import com.engin.eagerbeaver.presentation.main.home.HomeFragmentDirections
import com.engin.eagerbeaver.presentation.main.my_applicant.adapter.ApplicantAdapter
import dagger.hilt.android.AndroidEntryPoint
import hilt_aggregated_deps._com_engin_eagerbeaver_presentation_SplashActivity_GeneratedInjector
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ApplicantFragment : Fragment() {


    private var _binding: FragmentApplicantBinding? = null
    val binding get() = _binding!!

    private val viewModel:ApplicantViewModel by viewModels()

    private lateinit var adapter: ApplicantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentApplicantBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observeData()
    }

    private fun observeData(){
        lifecycleScope.launchWhenStarted {
            lifecycleScope.launchWhenStarted {
                viewModel.state.collect{currentState ->
                    if(currentState.isLoading){
                        LoadingDialog.showLoading(requireContext(),false)
                        return@collect
                    }
                    else
                        LoadingDialog.hideLoading()
                    currentState.myApplicant?.let { list->
                        adapter.updateData(list)
                    }
                    currentState.errorMessage?.let {uiText->
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
    }

    private fun init(){
        adapter = ApplicantAdapter(emptyList())
        binding.myApplicantRv.adapter = adapter
        binding.myApplicantRv.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }

}