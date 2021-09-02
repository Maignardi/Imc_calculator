package com.example.calculator

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import java.lang.Float.parseFloat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkButtons()
    }

    private fun imcCal() {
        val weightId = binding.etWeight
        val heightId = binding.etHeight
        val weight = Integer.parseInt(weightId.text.toString())
        val height = parseFloat(heightId.text.toString())
        val result = binding.tvTextInform
        val imc = weight / (height * height)
        val message = when {
            imc <= 18.5 -> "low weight"
            imc <= 24.9 -> "normal weight"
            imc <= 29.9 -> "overweight"
            imc <= 34.9 -> "Obesity 1"
            imc <= 39.9 -> "Obesity 2"
            else -> "morbid obesity 3"
        }
        imc.toString()
        result.setText("IMC: $imc \n $message")
    }

    private fun checkButtons() {
        val bt_calculate = binding.btCalculateImc
        val message = binding.tvTextInform
        bt_calculate.setOnClickListener {
            val editWeight = binding.etWeight.text.toString()
            val editHeight = binding.etHeight.text.toString()

            if (editWeight.isEmpty()) {
                message.text = "Enter your weight"
            } else if (editHeight.isEmpty()) {
                message.text = "Enter your Height"
            } else {
                imcCal()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.reset -> {
                val cleanWeight = binding.etWeight
                val cleanHeight = binding.etHeight
                val cleanMessage = binding.tvTextInform
                cleanHeight.setText("")
                cleanWeight.setText("")
                cleanMessage.setText("")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}