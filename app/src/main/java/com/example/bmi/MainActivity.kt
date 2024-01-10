package com.example.bmi

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var gender: String = "Laki-Laki"
    private lateinit var name : EditText
    private lateinit var alamat : EditText
    private lateinit var reset : Button
    private lateinit var height : EditText
    private lateinit var weight : EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById(R.id.editTextName)
        alamat = findViewById(R.id.editTextAddress)
        reset = findViewById(R.id. buttonReset)
        height = findViewById(R.id.editTextWeight)
        weight = findViewById(R.id.editTextWeight)

        //init widget
        val btnCalc = findViewById<Button>(R.id.buttonCalculate)
        val buttonReset = findViewById<Button>(R.id.buttonReset)
        val editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        val editTextWeight = findViewById<EditText>(R.id.editTextWeight)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupGender)

        btnCalc.setOnClickListener {
            val bmi = calculateBMI(editTextHeight, editTextWeight, radioGroup, textViewResult)
            textViewResult.text=bmi
        }
        buttonReset.setOnClickListener {
            resetBMI()
        }

    }
    private fun resetBMI(){

    }

    private fun calculateBMI(editTextHeight: EditText, editTextWeight: EditText, radioGroup: RadioGroup, textViewResult: TextView): String {
        val height = editTextHeight.text.toString().toDouble()
        val weight = editTextWeight.text.toString().toDouble()
        val nama = name.text.toString()
        val alamat = alamat.text.toString()

        // ID RadioButton yang dipilih
        val selectedGenderId = radioGroup.checkedRadioButtonId

        // choose gender
        gender = when (selectedGenderId) {
            R.id.radioButtonMale -> "Laki-laki"
            R.id.radioButtonFemale -> "Perempuan"
            else -> "Laki-laki"
        }

        // BMI menghitung
        val bmi = when (gender) {
            "Laki-laki" -> weight / ((height / 100) * (height / 100))
            "Perempuan" -> weight / ((height / 100) * (height / 100)) * 0.9
            else -> 0.0
        }

        val result = when {
            bmi < 18.5 -> "Cacingan"
            bmi >= 18.5 && bmi < 24.9 -> "Berat badan normal"
            bmi >= 25 && bmi < 29.9 -> "Berat badan berlebih"
            else -> "Obesitas"
        }
        return "BMI: %.2f \nnama= $nama \nalamat= $alamat \n$result".format(bmi)
    }
}