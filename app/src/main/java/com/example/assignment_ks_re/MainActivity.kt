package com.example.assignment_ks_re

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import com.example.assignment_ks_re.data.api.BookApiService
import com.example.assignment_ks_re.data.api.RetrofitInstance
import com.example.assignment_ks_re.data.model.BookSearchData
import com.example.assignment_ks_re.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        showSearchFragment(SearchFragment())


        val call = RetrofitInstance.api.searchBooks("android", "accuracy", 1, 15)
        call.enqueue(object : Callback<BookSearchData> {
            override fun onResponse(
                call: Call<BookSearchData>,
                response: Response<BookSearchData>
            ) {
                val data = response.body()
                val mutableList = mutableListOf<Map<String,String>>()

                data?.documents?.forEach{
                    val map = mapOf("contents" to it.contents,"datetime" to it.datetime)
                    mutableList.add(map)
                }

                val adapter = SimpleAdapter(
                    this@MainActivity,
                    mutableList,
                    android.R.layout.simple_expandable_list_item_2,
                    arrayOf("contents","datetime"),
                    intArrayOf(android.R.id.text1,android.R.id.text2)
                )
                binding.listView.adapter = adapter


            }

            override fun onFailure(call: Call<BookSearchData>, t: Throwable) {
                call.cancel()
            }

        })


    }

//    private fun showSearchFragment(fragment: SearchFragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//            .replace(R.id.frame_layout, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
}
