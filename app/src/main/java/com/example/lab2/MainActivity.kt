package com.example.lab2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*


class MainActivity : AppCompatActivity() {


    private fun discriminant(a: Double, b: Double, c: Double) = (b*b) - (4 * a * c)
    private fun x1(a: Double, b: Double, c: Double) = ((-b + sqrt(discriminant(a,b,c))) / (2 * a))
    private fun x2(a: Double, b: Double, c: Double) = ((-b - sqrt(discriminant(a,b,c))) / (2 * a))

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_btn = findViewById(R.id.button)

//
        main_btn?.setOnClickListener{
            try{
                var a : Double = findViewById<EditText>(R.id.editTextA).text.toString().toDouble()
                var b : Double = findViewById<EditText>(R.id.editTextB).text.toString().toDouble()
                var c : Double = findViewById<EditText>(R.id.editTextC).text.toString().toDouble()
                //String.format("%.2f", x1(a,b,c).toString())
                if(a != 0.0 && b != 0.0 && c != 0.0){
                    if(discriminant(a,b,c) < 0)         result = "Уравнение не имеет корней (D<0)"
                    else if(discriminant(a,b,c) == 0.0) result = "Уравнение имеет 1 корень \n х1 = х2 = " + intStringFormat(x1(a,b,c))
                    else                                result = "Уравнение имеет 2 корня \nх1 = " + intStringFormat(x1(a,b,c)) + "\nх2 = " + intStringFormat(x2(a,b,c))
                }
                else {
                    if(a == 0.0 && b == 0.0 && c == 0.0)result = "Уравнение является верным при любом значении х"
                    if(a == 0.0 && b == 0.0 && c != 0.0)result = "Уравнение не имеет корней (с!=0)"
                    if(a != 0.0 && b != 0.0 && c == 0.0)result = "Уравнение имеет 2 корня \nх1 = 0\nх2 = " + intStringFormat((-b/a)).toString()
                    if(a == 0.0 && b != 0.0)            result = "Уравнение линейно!(a=0) \n x = "+ intStringFormat((-c / b)).toString()
                }
                Toast.makeText(applicationContext,result,Toast.LENGTH_LONG).show()
            }
            catch (e : Exception){
                Toast.makeText(applicationContext,"Проверьте правильность ввода",Toast.LENGTH_LONG).show()
            }
        }
    }
    private var main_btn: Button? = null
    private var result = ""

    private fun intStringFormat(x : Double): Any {
        return if(x == x.roundToInt().toDouble()) x.roundToInt()
        else String.format("%.2f", x)

    }
}