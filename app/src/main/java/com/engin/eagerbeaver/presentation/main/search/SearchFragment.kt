package com.engin.eagerbeaver.presentation.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.widget.SearchView
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
import com.engin.eagerbeaver.common.domain.model.JobType
import com.engin.eagerbeaver.common.presentation.adapter.JobAdvertAdapter
import com.engin.eagerbeaver.common.presentation.component.LoadingDialog
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.databinding.FilterSheetBinding
import com.engin.eagerbeaver.databinding.FragmentSearchBinding
import com.engin.eagerbeaver.presentation.main.search.adapter.FilterAdapter
import com.engin.eagerbeaver.presentation.main.search.component.FilterClick
import com.engin.eagerbeaver.presentation.main.search.component.FilterItem
import com.engin.eagerbeaver.presentation.main.search.viewmodels.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment(), FilterClick {

    private var _binding: FragmentSearchBinding? = null
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var jobAdvertAdapter: JobAdvertAdapter
    private lateinit var navController: NavController
    private var bottomSheetDialog: BottomSheetDialog? = null
    private lateinit var categoryAdapter: FilterAdapter
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
                jobAdvertAdapter.updateData(currentState.jobsList)
                currentState.shouldNavigate?.let { route ->
                    when (route) {
                        is Route.JobDetail -> {
                            goJobDetail(route.jobId)
                        }
                        else -> {}
                    }
                }
                currentState.categoryList?.let {
                    categoryAdapter.updateData(it.map { cat ->
                        FilterItem(cat, false, cat.id)
                    })
                }
                currentState.filter?.let {
                    if (it.type == null) {
                        viewModel.getJobs(it.category_id)
                    } else {
                        viewModel.getJobs(it.category_id, JobType.toType(it.type!!))
                    }
                    viewModel.clearFilter()
                }
            }
        }
    }

    private fun createFilterDialog() {
        viewModel.getCategories()
        bottomSheetDialog = BottomSheetDialog(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val bindingSheet = FilterSheetBinding.inflate(inflater)
        bindingSheet.filterCategoryRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter =
            FilterAdapter(viewModel.state.value.categoryList?.map { FilterItem(it, false, it.id) }
                ?: emptyList<FilterItem>(), this)
        bottomSheetDialog?.setContentView(bindingSheet.root)
        bindingSheet.filterCategoryRv.adapter = categoryAdapter
        bindingSheet.filiterSearch.setOnClickListener {
            val id = bindingSheet.jobTypeRv.checkedRadioButtonId
            val selectedType: String?
            if (id != -1) {
                val jobType = bindingSheet.root.findViewById<RadioButton>(id)
                selectedType = jobType.text.toString()
            } else {
                selectedType = null
            }
            bottomSheetDialog?.hide()
            val selected = categoryAdapter.data.firstOrNull { it.selected }
            viewModel.applyFilter(selectedType, selected?.id)
        }
        bindingSheet.clearFilter.setOnClickListener {
            bottomSheetDialog?.hide()
            bindingSheet.jobTypeRv.clearCheck()
        }
        bottomSheetDialog?.show()
        bottomSheetDialog?.setCancelable(true)
        bottomSheetDialog?.behavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun goJobDetail(jobId: Long) {
        val bundle = bundleOf("job_id" to jobId)
        navController.navigate(R.id.action_global_jobDetailFragment, bundle)
    }

    private fun init() {
        navController = findNavController()
        jobAdvertAdapter = JobAdvertAdapter(emptyList(), viewModel)
        binding.jobsSearchRv.adapter = jobAdvertAdapter
        binding.jobsSearchRv.layoutManager = LinearLayoutManager(requireContext())
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }


        })
        binding.searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                viewModel.getJobs(null)
                return false
            }

        })
        binding.filterButton.setOnClickListener {
            createFilterDialog()
        }
    }

    override fun selectCategory(item: FilterItem) {
        item.selected = !item.selected
    }


}