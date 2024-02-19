package app.eduardo.bmi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var weightInputLayout: TextInputLayout
    private lateinit var heightInputLayout: TextInputLayout
    private lateinit var ageInputLayout: TextInputLayout
    private lateinit var muscleMassInputLayout: TextInputLayout
    private lateinit var bodyCompositionInputLayout: TextInputLayout
    private lateinit var fatDistributionInputLayout: TextInputLayout
    private lateinit var fitnessLevelInputLayout: TextInputLayout
    private lateinit var calculateButton: MaterialButton
    private lateinit var resultsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weightInputLayout = findViewById(R.id.weightInputLayout)
        heightInputLayout = findViewById(R.id.heightInputLayout)
        ageInputLayout = findViewById(R.id.ageInputLayout)
        muscleMassInputLayout = findViewById(R.id.muscleMassInputLayout)
        bodyCompositionInputLayout = findViewById(R.id.bodyCompositionInputLayout)
        fatDistributionInputLayout = findViewById(R.id.fatDistributionInputLayout)
        fitnessLevelInputLayout = findViewById(R.id.fitnessLevelInputLayout)
        calculateButton = findViewById(R.id.calculateButton)
        resultsTextView = findViewById(R.id.resultsTextView)

        calculateButton.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {
        val weightString = weightInputLayout.editText?.text.toString()
        val heightString = heightInputLayout.editText?.text.toString()
        val ageString = ageInputLayout.editText?.text.toString()

        if (weightString.isNotEmpty() && heightString.isNotEmpty() && ageString.isNotEmpty()) {
            val weight = weightString.toFloat()
            val height = heightString.toFloat() / 100 // convert height to meters
            val age = ageString.toInt()

            if (weight in 30.0..150.0 && height in 1.0..2.5 && age > 0) {
                val bmi = calculateBMIWithAge(weight, height, age)
                displayBMIResult(bmi)
            } else {
                resultsTextView.text = "Please enter valid weight (30-150 kg), height (100-250 cm), and age (> 0)."
            }
        } else {
            resultsTextView.text = "Please enter weight, height, and age."
        }
    }

    private fun calculateBMIWithAge(weight: Float, height: Float, age: Int): Float {
        // Adjust the BMI calculation based on age or use a more sophisticated formula if needed
        return weight / (height * height) + (age / 10)
    }

    private fun displayBMIResult(bmi: Float) {
        val category = getBMICategory(bmi)
        resultsTextView.text = String.format("Your BMI: %.2f\nCategory: $category", bmi)

        val message = getMotivationalMessage(category)
        resultsTextView.append("\n\n$message")
    }

    private fun getBMICategory(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Normal weight"
            bmi < 29.9 -> "Overweight"
            else -> "Obese"
        }
    }

    private fun getMotivationalMessage(category: String): String {
        return when (category) {
            "Underweight" -> "You're on a journey to a healthier weight. Ensure you're eating nutrient-rich foods."
            "Normal weight" -> "Congratulations! You're maintaining a healthy weight. Keep up the good work!"
            "Overweight" -> "It's time to focus on a balanced diet and regular exercise. Small changes lead to big results."
            "Obese" -> "Let's work towards a healthier lifestyle. Consider consulting a healthcare professional for guidance."
            else -> "Keep striving for a healthier you!"
        }
    }
}




