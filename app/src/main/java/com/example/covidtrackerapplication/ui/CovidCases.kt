package com.example.covidtrackerapplication.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtrackerapplication.R
import com.example.covidtrackerapplication.adapter.CovidCaseAdapter
import com.example.covidtrackerapplication.databinding.FragmentCovidCasesBinding
import com.example.covidtrackerapplication.data.CovidData
import com.example.covidtrackerapplication.data.CovidViewModel
import com.example.covidtrackerapplication.util.Resources
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class CovidCases : Fragment() {
 lateinit var binding : FragmentCovidCasesBinding

 //@Inject @RetrofitTwo lateinit var retrofit : Retrofit
    private val viewModel : CovidViewModel by viewModels()
    private  val TAG = "CovidCases"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCovidCasesBinding.inflate(layoutInflater,container,false)
        handleClicks()
        fetchData()



        return binding.root
    }

    private fun handleClicks(){
        binding.apply {
            vaccineView.setOnClickListener {
                changeFragment(VaccineFragment())
            }
            moveToVaccine.setOnClickListener{
                changeFragment(VaccineFragment())
            }
            detailView.setOnClickListener{
                changeFragment(CovidDetail())
            }
            moveToDetails.setOnClickListener {
                changeFragment(CovidDetail())
            }

        }
    }

    private fun changeFragment(fragment: Fragment) {
       val transaction = activity?.supportFragmentManager?.beginTransaction()
        val id = this.id
        transaction?.replace(R.id.fragmentView ,fragment )?.addToBackStack(id.toString())?.commit()

    }


    private fun fetchData(){
        try {

            viewModel.covidData.observe(requireActivity()) { data ->

                Log.d(TAG, "onCreateView: view model")
                val covidCases = data.data
                Log.d(TAG, "onCreateView: ${covidCases.toString()}")
                var totalCases :CovidData? =  null;
                val details = covidCases?.toMutableList()
                Log.d(TAG, "fetchData: $details")
                binding.progressBar2.isVisible = data is Resources.Loading && data.data.isNullOrEmpty()
                if(details?.size!! > 0) {
                    totalCases = details?.removeAt(0)
                    binding.activePercent.text = calcPercent(totalCases?.confirmed,totalCases?.active).toString() +"% active cases"
                    setProgress(totalCases?.confirmed!!,totalCases?.active)
                    binding.TotalCases.text= totalCases.confirmed
                    binding.ActiveCases.text = totalCases.active
                }
                val adapter = CovidCaseAdapter(
                    details as ArrayList<CovidData>,
                    requireContext(),
                    binding.tabLayout
                )

                binding.apply {

                    covidDataView.adapter = adapter
                    covidDataView.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                }
            }
        }
        catch (e : Exception){
            Log.d(TAG, "onCreateView: ${e.localizedMessage}")
        }
    }

    private fun calcPercent(confirmed: String?, active: String?): Float? {
       val confirmedCases = confirmed?.toFloat()
        val activeCases = active?.toFloat()
     val percent = (activeCases!! / confirmedCases!! ) *100
        return percent
    }

   private fun setProgress(confirmed: String , active : String) {
        val circularBar = binding.circularProgressBar
         val confirmedCase = confirmed.toFloat()
        val activeCases = active.toFloat()
        circularBar.apply {
            setProgressWithAnimation(activeCases,1000)
            progressMax = confirmedCase
            roundBorder = true

        }
    }


    }


