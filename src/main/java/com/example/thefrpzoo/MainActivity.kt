package com.example.thefrpzoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var count0Disp: TextView
    private lateinit var count5Disp: TextView
    private lateinit var count10Disp: TextView
    private var sum0 = 0
    private var sum5 = 0
    private var sum10 = 0
    //private val test: View? = toggle0
    private var presses = BehaviorSubject.createDefault<Int>(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        count0Disp = findViewById<TextView>(R.id.count0)
        count5Disp = findViewById<TextView>(R.id.count5)
        count10Disp = findViewById<TextView>(R.id.count10)
        count0Disp.text = "Count: "+sum0.toString()
        count5Disp.text = "Count: "+sum5.toString()
        count10Disp.text = "Count: "+sum10.toString()

        toggle0.setOnClickListener(this);
        button0.setOnClickListener(this)
        toggle5.setOnClickListener(this)
        button5.setOnClickListener(this)
        toggle10.setOnClickListener(this)
        button10.setOnClickListener(this)
        presses.subscribeBy {
            update(it)
        }
    }

    override fun onClick(v: View) {
            presses.onNext(v.id)
    }

    private fun update(disp: Int) {
        if (disp == R.id.button0){
            if (toggle0.isChecked){
                sum0++
            }
        }
        if (disp == R.id.button5){
            if (toggle5.isChecked){
                sum5++
            }
        }
        if (disp == R.id.button10){
            sum10++
        }
        if (disp==R.id.toggle10 || disp==R.id.button10){
            if (toggle10.isChecked){
                count10Disp.text = "Count: "+sum10.toString()
            } else{
                count10Disp.text = "Count: -1"
            }
        }
        if (disp==R.id.toggle5 || disp==R.id.button5){
            if (toggle5.isChecked){
                count5Disp.text = "Count: "+sum5.toString()
            } else{
                count5Disp.text = "Count: -1"
            }
        }
        if (disp==R.id.toggle0 || disp==R.id.button0){
            if (toggle0.isChecked){
                count0Disp.text = "Count: "+sum0.toString()
            } else{
                count0Disp.text = "Count: -1"
                sum0 = 0
            }
        }

    }
}
