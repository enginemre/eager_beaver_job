package com.engin.eagerbeaver.presentation.main.advert_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.activity.addCallback
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.engin.eagerbeaver.R
import com.engin.eagerbeaver.common.CustomSnackBar
import com.engin.eagerbeaver.common.SnackType
import com.engin.eagerbeaver.common.domain.model.Category
import com.engin.eagerbeaver.common.domain.model.JobAdvert
import com.engin.eagerbeaver.common.domain.model.JobPosition
import com.engin.eagerbeaver.common.domain.model.JobType
import com.engin.eagerbeaver.common.presentation.component.LoadingDialog
import com.engin.eagerbeaver.common.presentation.util.Route
import com.engin.eagerbeaver.databinding.FragmentAdvertEditBinding
import com.engin.eagerbeaver.presentation.main.advert_detail.viewmodel.AdvertDetailViewModel
import com.engin.eagerbeaver.presentation.main.home.adpter.CategoryAdapter
import com.engin.eagerbeaver.presentation.main.my_adverts.viewmodel.MyAdvertViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AdvertDetailFragment : Fragment(), MenuProvider {

    private var _binding: FragmentAdvertEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var jobTypeAdapter: ArrayAdapter<String>
    private lateinit var jobPositionAdapter: ArrayAdapter<String>
    private  var categoryAdapter: ArrayAdapter<Category>? = null
    private lateinit var navController: NavController

    private val viewModel: AdvertDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            goBack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdvertEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setMenu()
        init()
        observeData()
    }

    private fun setMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    private fun init() {
        val jobPosition = resources.getStringArray(R.array.job_positions)
        val jobType = resources.getStringArray(R.array.job_types)
        jobPositionAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, jobPosition)
        jobTypeAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, jobType)
        binding.jobPosition.adapter = jobPositionAdapter
        binding.jobTypeSpinner.adapter = jobTypeAdapter
        binding.jobPosition.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        binding.jobTypeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {


                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
        binding.jobCategories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        binding.seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.salary.text = "$progress TL"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        binding.button3.setOnClickListener {
            if (binding.button3.text.toString().startsWith("D")) {
                viewModel.updateAdvert(
                    title = binding.jobTitle.text.toString(),
                    jobPosition = JobPosition.toPosition(binding.jobPosition.selectedItem.toString()),
                    type = JobType.toType(binding.jobTypeSpinner.selectedItem.toString()),
                    category = binding.jobCategories.selectedItem as Category,
                    salary = binding.seekBar2.progress,
                    description = binding.jobDescription.text.toString()
                )

            } else {
                viewModel.createAdvert(
                    title = binding.jobTitle.text.toString(),
                    jobPosition = JobPosition.toPosition(binding.jobPosition.selectedItem.toString()),
                    type = JobType.toType(binding.jobTypeSpinner.selectedItem.toString()),
                    category = binding.jobCategories.selectedItem as Category,
                    salary = binding.seekBar2.progress,
                    description = binding.jobDescription.text.toString()
                )
            }
        }
    }




    private fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { currentState ->
                if (currentState.isLoading) {
                    LoadingDialog.showLoading(requireContext(), false)
                    return@collect
                } else
                    LoadingDialog.hideLoading()
                currentState.errorMessage?.let {
                    CustomSnackBar.make(
                        context = requireContext(),
                        view = requireView(),
                        text = it.asString(requireContext()),
                        type = SnackType.ERROR
                    ).show()
                    viewModel.messageShown()
                }
                currentState.warningMessage?.let {
                    CustomSnackBar.make(
                        context = requireContext(),
                        view = requireView(),
                        text = it.asString(requireContext()),
                        type = SnackType.WARNING
                    ).show()
                    viewModel.messageShown()
                }
                currentState.categoriesList?.let {
                    categoryAdapter =
                        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it)
                    binding.jobCategories.adapter = categoryAdapter
                }
                currentState.route?.let { route ->
                    when (route) {
                        is Route.MyAdverts -> {
                            val action =
                                AdvertDetailFragmentDirections.actionAdvertDetailFragmentToAdvertsFragment(
                                    isRefresh = route.isRefresh
                                )
                            navController.navigate(action)
                        }
                        else -> {}
                    }

                }

            }

        }
        lifecycleScope.launchWhenStarted {
            viewModel.channel.collect{ advert->
                advert?.let {
                    binding.button3.setText(R.string.edit)
                    binding.jobAdvert = advert
                    binding.jobTypeSpinner.setSelection(jobTypeAdapter.getPosition(it.type.fromType()))
                    binding.jobPosition.setSelection(jobPositionAdapter.getPosition(it.position.fromPosition()))
                    categoryAdapter?.let { adapter ->
                        binding.jobCategories.setSelection(adapter.getPosition(it.category))
                    }
                    binding.seekBar2.progress = it.salary
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun goBack() {
        navController.navigateUp()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.advert_edit_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.delete_advert -> {
                viewModel.deleteAdvert()
                true
            }
            else -> false
        }
    }


}