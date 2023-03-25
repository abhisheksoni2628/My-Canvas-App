package com.abhishek.mycanvas

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.abhishek.mycanvas.MyCanvas.Companion.colorList
import com.abhishek.mycanvas.MyCanvas.Companion.currSize
import com.abhishek.mycanvas.MyCanvas.Companion.currentBrush
import com.abhishek.mycanvas.MyCanvas.Companion.pathList
import com.abhishek.mycanvas.databinding.ActivityMainBinding
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    companion object{
        var path = Path()
        var paintBrush = Paint()
    }

    var progressChanged by Delegates.notNull<Float>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.redColor.setOnClickListener {
            paintBrush.color = Color.RED
            currentColor(paintBrush.color)
        }

        binding.blueColor.setOnClickListener {
            paintBrush.color = Color.BLUE
            currentColor(paintBrush.color)
        }

        binding.blackColor.setOnClickListener {
            paintBrush.color = Color.BLACK
            currentColor(paintBrush.color)
        }

        binding.colorSheet.setOnClickListener {
            ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPreferenceName("MyColorPickerDialog")
                .setPositiveButton("Confirm",
                    ColorEnvelopeListener { envelope, fromUser -> currentColor(envelope.color) })
                .setNegativeButton(
                    "Cancel"
                ) { dialogInterface, i -> dialogInterface.dismiss() }
                .attachAlphaSlideBar(true) // the default value is true.
                .attachBrightnessSlideBar(true) // the default value is true.
                .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                .show()

        }

        binding.whiteColor.setOnClickListener {
            pathList.clear()
            colorList.clear()
            path.reset()
        }


    }

//    private fun showDialog() {
//        val alertDialog = AlertDialog.Builder(this)
//        val seekBar = SeekBar(this)
//        seekBar.max = 100
//        seekBar.keyProgressIncrement = 1
//
//        val tv = TextView(this)
//
//        alertDialog.setTitle("Size of Pencil")
//        alertDialog.setView(seekBar)
//
//        seekBar.setOnSeekBarChangeListener(object :
//            SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seek: SeekBar,
//                                           progress: Int, fromUser: Boolean) {
//                Toast.makeText(this@MainActivity, "Value : " + progress , Toast.LENGTH_SHORT).show()
//
//            }
//
//            override fun onStartTrackingTouch(p0: SeekBar?) {
//
//            }
//
//            override fun onStopTrackingTouch(p0: SeekBar?) {
//
//            }
//        })
//        alertDialog.setPositiveButton("Confirm", DialogInterface.OnClickListener { dialog, id ->
//            currSize = progressChanged
//        })
//
//        alertDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
//            dialog.dismiss()
//        })
//
//        alertDialog.show()
//    }


    private fun currentColor(color: Int){
        currentBrush = color
        path = Path()
    }
}