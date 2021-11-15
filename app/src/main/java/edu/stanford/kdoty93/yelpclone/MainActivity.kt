 package edu.stanford.kdoty93.yelpclone

 import retrofit2.Call
 import retrofit2.Callback
 import retrofit2.Response

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
 import android.text.Editable
 import android.text.TextWatcher
 import android.util.Log
 import android.widget.EditText
 import androidx.recyclerview.widget.LinearLayoutManager
 import androidx.recyclerview.widget.RecyclerView
 import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 private const val TAG = "MainActivity"
 private const val BASE_URL = "https://api.yelp.com/v3/"
 private const val API_KEY = "X8uHXLzIK0qY9h85Ux8ZbhSJFtT2ncOLwZAwcnQcQbwY2bEdn9_iQ7Hri_SC23TfFRboDcFXqgJyhhoTXlIqU0OJFs8Q_l1pU4YJfkDVY-z-8-NbkF12wDechT-PYXYx"
class MainActivity : AppCompatActivity() {

    private lateinit var rvRestaurants: RecyclerView
    private lateinit var etSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvRestaurants = findViewById(R.id.rvRestaurant)
        etSearch = findViewById(R.id.etSearch)

        val restaurants = mutableListOf<YelpRestaurant>()
        val adapter = RestaurantsAdapter(this, restaurants)
        rvRestaurants.adapter = adapter
        rvRestaurants.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
        val yelpService = retrofit.create(YelpService::class.java)
        // searchRestaurants is async process
        yelpService.searchRestaurants("Bearer $API_KEY", "Avocado Toast", "New York").enqueue(object : Callback<YelpSearchResult> {
            override fun onResponse(call: Call<YelpSearchResult>, response: Response<YelpSearchResult>) {
                Log.i(TAG, "onResponse $response")
                val body = response.body()
                if (body == null) {
                    Log.w(TAG, "Did not receive valid response body from Yelp API... exiting")
                    return
                }
                restaurants.addAll(body.restaurants)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<YelpSearchResult>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
            }

        })

        etSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanged")

                val filteredList = mutableListOf<YelpRestaurant>()
                for (restaurant in restaurants) {
                    if (restaurant.name.lowercase().contains(s.toString().lowercase(), true)) {
                        filteredList.add(restaurant)
                    }
                }

                adapter.filterRestaurants(filteredList)
            }

        })

    }
}