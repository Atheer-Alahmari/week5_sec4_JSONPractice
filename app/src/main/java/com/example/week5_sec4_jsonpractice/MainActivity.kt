package com.example.week5_sec4_jsonpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var noED:EditText
    lateinit var btnG:Button
    lateinit var name:TextView
    private val apiInterface by lazy { APIClient().getClient()?.create(APIInterface::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noED=findViewById(R.id.number_ED)
        btnG=findViewById(R.id.btn_get)
        name=findViewById(R.id.name_TV)

        btnG.setOnClickListener {
            getNameJ()
        }
    }
    private fun getNameJ() {

        apiInterface?.getNameJ()?.enqueue(object : Callback<ArrayList<NameJ?>?> {
            override fun onResponse(
                call: Call<ArrayList<NameJ?>?>,
                response: Response<ArrayList<NameJ?>?>
            ) {

                var input = noED.text.toString().toInt()
                if (input > 0 && input < 14) {
                    val datumList = response.body()!![--input]?.name
                    name.text = datumList
                }
                else
                    Toast.makeText(this@MainActivity, "Enter Number from 1 to 13", Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: Call<ArrayList<NameJ?>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Unable to get data", Toast.LENGTH_LONG).show()
            }
        })

    }
}