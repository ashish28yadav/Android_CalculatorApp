package com.example.calculator2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputTextView: TextView
    private lateinit var resultTextView: TextView
    private var currentInput = ""
    private var lastOperator = ""
    private var firstNumber = ""
    private var secondNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize TextViews
        inputTextView = findViewById(R.id.inputTextView)
        resultTextView = findViewById(R.id.resultTextView)

        // Number buttons
        val numberButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in numberButtons) {
            findViewById<Button>(id).setOnClickListener {
                val number = (it as Button).text.toString()
                currentInput += number
                inputTextView.text = currentInput
            }
        }

        // Operation buttons
        val operationButtons = listOf(
            R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide
        )

        for (id in operationButtons) {
            findViewById<Button>(id).setOnClickListener {
                if (currentInput.isNotEmpty()) {
                    firstNumber = currentInput
                    lastOperator = (it as Button).text.toString()
                    currentInput = ""
                    inputTextView.text = "$firstNumber $lastOperator "
                }
            }
        }

        // Equals button
        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            if (currentInput.isNotEmpty() && firstNumber.isNotEmpty()) {
                secondNumber = currentInput
                val result = calculateResult(firstNumber.toDouble(), secondNumber.toDouble(), lastOperator)
                resultTextView.text = "= $result"
                currentInput = result.toString()
                firstNumber = ""
                secondNumber = ""
                lastOperator = ""
            }
        }

        // Clear (AC) button
        findViewById<Button>(R.id.btnAC).setOnClickListener {
            currentInput = ""
            firstNumber = ""
            secondNumber = ""
            lastOperator = ""
            inputTextView.text = ""
            resultTextView.text = ""
        }

        // Decimal button
        findViewById<Button>(R.id.btnDot).setOnClickListener {
            if (!currentInput.contains(".")) {
                currentInput += "."
                inputTextView.text = currentInput
            }
        }
    }

    private fun calculateResult(num1: Double, num2: Double, operator: String): Double {
        return when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "X" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else Double.NaN
            else -> 0.0
        }
    }
}
