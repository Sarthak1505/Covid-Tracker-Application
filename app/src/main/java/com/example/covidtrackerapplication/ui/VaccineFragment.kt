package com.example.covidtrackerapplication.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.transition.Fade
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.LinearInterpolator
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtrackerapplication.adapter.VaccineAdapter
import com.example.covidtrackerapplication.annotation.RetrofitOne
import com.example.covidtrackerapplication.api.VaccineApi
import com.example.covidtrackerapplication.databinding.FragmentVaccineBinding
import com.example.covidtrackerapplication.model.VaccineData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class VaccineFragment : Fragment() {


    @Inject
    @RetrofitOne
    lateinit var retrofit: Retrofit

    lateinit var URL: String
    private val TAG = "VaccineFragment"
    lateinit var binding: FragmentVaccineBinding
    lateinit var recyclerView: RecyclerView
    var fetchedList: MutableList<VaccineData> = ArrayList<VaccineData>()
    lateinit var adapter: VaccineAdapter

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVaccineBinding.inflate(layoutInflater, container, false)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = VaccineAdapter(fetchedList)
        recyclerView.adapter = adapter
        binding.checkBtn.setOnClickListener {
            binding.datePicker.visibility = VISIBLE
            binding.displayView.visibility = INVISIBLE

            fetchData()
        }
        binding.animDisplayView.setOnClickListener{
            fadeAnim(binding.animDisplayView)
            fadeAnim(binding.recyclerView)
            fadeInAnim(binding.displayView)
        }
        binding.animPinCodeLayout.setOnClickListener{
            fadeAnim(binding.animDisplayView)
            fadeAnim(binding.recyclerView)
            fadeInAnim(binding.displayView)
        }
        binding.animPinCodeText.setOnClickListener{
            fadeAnim(binding.animDisplayView)
            fadeAnim(binding.recyclerView)
            fadeInAnim(binding.displayView)
        }
        binding.animCheckBtn.setOnClickListener{
            fadeAnim(binding.animDisplayView)
            fadeAnim(binding.recyclerView)
            fadeInAnim(binding.displayView)

        }



        return binding.root


    }


    @SuppressLint("NotifyDataSetChanged")
    private fun fetchData() {
        val pincode = binding.pinCodeText.text.toString()
        val today = Calendar.getInstance()
        binding.datePicker.init(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DATE)
        ) { view, year, month, date ->
            val month = month + 1;
            var monthValue = ""
            if (month < 10) {
                monthValue = "0$month"
            } else {
                monthValue = month.toString()
            }

            val date = "$date-$monthValue-$year"
            URL = "findByPin?pincode=$pincode&date=$date"
            fadeAnim(binding.datePicker)
            binding.progressBar.visibility = VISIBLE
          CoroutineScope(Dispatchers.Main).launch {
           val apiData = async(Dispatchers.IO){
               makeApiCall()
           }
              apiData.await()
              binding.progressBar.visibility = INVISIBLE
              fadeInAnim(binding.animDisplayView)
              fadeAnim(binding.displayView)
              fadeInAnim(binding.recyclerView)
              binding.datePicker.visibility = INVISIBLE
              binding.recyclerView.visibility = VISIBLE
              adapter.notifyData(fetchedList)
          }

        }


    }

    private suspend fun makeApiCall() {
        val data = retrofit.create(VaccineApi::class.java)
        val vaccineData = data.getVaccine(URL)

        if (vaccineData.isSuccessful) {
            Log.d(TAG, "makeApiCall: ${vaccineData.body()}")

               fetchedList.addAll(vaccineData.body()?.vaccinationCenter!!)
            Log.d(TAG, "makeApiCall: new List $fetchedList")


        } else {
            Log.d(TAG, "makeApiCall: error ${vaccineData.errorBody()}")
        }
    }

  private fun fadeAnim(myView : View){
       val anim = AlphaAnimation(1f,0f)
      anim.interpolator = LinearInterpolator()

         anim.duration = 1000
      myView!!.animation = anim


      myView.visibility = INVISIBLE

  }
  private fun fadeInAnim(animDisplayView: View) {
    val anim = AlphaAnimation(0f,1f)
      anim.interpolator = LinearInterpolator()
      anim.duration = 1000
   animDisplayView.animation = anim


      animDisplayView!!.visibility = VISIBLE

  }



}