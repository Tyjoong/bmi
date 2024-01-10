package com.example.bmi

package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var gender = "Laki - laki"
    private lateinit var calculateBtn: Button
    private lateinit var resetBtn: Button
    private lateinit var editTextheight: EditText
    private lateinit var editTextWeight: EditText
    private lateinit var editTextName: EditText
    private lateinit var editTextAlamat: EditText
    private lateinit var genders: RadioGroup
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Move the initialization of properties here
        calculateBtn = findViewById(R.id.buttonCalculate)
        resetBtn = findViewById(R.id.buttonReset)
        editTextheight = findViewById(R.id.editTextHeight)
        editTextWeight = findViewById(R.id.editTextWeight)
        editTextName = findViewById(R.id.editTextName)
        editTextAlamat = findViewById(R.id.editTextAddress)
        genders = findViewById(R.id.radioGroupGender)
        resultText = findViewById(R.id.result)

        calculateBtn.setOnClickListener { calculate() }
        resetBtn.setOnClickListener { reset() }
    }

    private fun calculate() {
        val height = editTextheight.text.toString().toDouble()
        val weight = editTextWeight.text.toString().toDouble()
        val name = findViewById<EditText>(R.id.editTextName).text.toString()
        val alamat = findViewById<EditText>(R.id.editTextAddress).text.toString()
        val selectedGender = genders.checkedRadioButtonId


        gender = when (selectedGender) {
            R.id.radioButtonMale -> "Laki-laki"
            R.id.radioButtonFemale -> "Perempuan"
            else -> "Laki-laki"
        }

        val bmi = when (gender) {
            "Laki-laki" -> weight / ((height / 100) * (height / 100))
            "Perempuan" -> weight / ((height / 100) * (height / 100)) * 0.9
            else -> 0.0
        }

        val result = when {
            bmi < 18.5 -> "Kekurangan gizi"
            bmi >= 18.5 && bmi < 24.9 -> "Normal"
            bmi >= 25 && bmi < 29.9 -> "Kelebihan gizi"
            else -> "Obesitas"
        }

        // Append name, alamat, and BMI to the result string
        val finalResult = "Hasil BMI: $bmi\nStatus: $result\nName: $name\nAlamat: $alamat"

        resultText.text = finalResult
    }

    private fun reset() {
        // Clear input fields and result text
        editTextheight.text.clear()
        editTextWeight.text.clear()
        editTextName.text.clear()
        editTextAlamat.text.clear()
        genders.clearCheck()
        resultText.text = ""
    }
}
