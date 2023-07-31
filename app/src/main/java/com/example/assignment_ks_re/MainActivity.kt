package com.example.assignment_ks_re

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment_ks_re.data.adapter.BookSearchAdapter
import com.example.assignment_ks_re.data.api.BookApiService
import com.example.assignment_ks_re.data.api.RetrofitInstance
import com.example.assignment_ks_re.data.model.BookSearchData
import com.example.assignment_ks_re.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var bookSearchAdapter: BookSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        showSearchFragment(SearchFragment())

//        binding.rvSearchResult.adapter = BookSearchAdapter()
//        binding.rvSearchResult.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val call = RetrofitInstance.api.searchBooks("android", "accuracy", 1, 15)
        call.enqueue(object : Callback<BookSearchData> {
            override fun onResponse(
                call: Call<BookSearchData>,
                response: Response<BookSearchData>
            ) {
//                val data = response.body()
//                val mutableList = mutableListOf<Map<String,String>>()
//
//                data?.documents?.forEach{
//                    val map = mapOf("contents" to it.contents,"datetime" to it.datetime)
//                    mutableList.add(map)
//                }
//
//                val adapter = SimpleAdapter(
//                    this@MainActivity,
//                    mutableList,
//                    android.R.layout.simple_expandable_list_item_2,
//                    arrayOf("contents","datetime"),
//                    intArrayOf(android.R.id.text1,android.R.id.text2)
//                )
//                binding.listView.adapter = adapter

                setupRecyclerView()



            }

            override fun onFailure(call: Call<BookSearchData>, t: Throwable) {
                call.cancel()
            }

        })




    }
// 초기화면을 프래그먼트로 띄우려다가 실패
//    private fun showSearchFragment(fragment: SearchFragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//            .replace(R.id.frame_layout, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }

    fun setupRecyclerView() {
        bookSearchAdapter = BookSearchAdapter()
        binding.rvSearchResult.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            binding.rvSearchResult.adapter = bookSearchAdapter

    }


}
