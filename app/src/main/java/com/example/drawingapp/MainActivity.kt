package com.example.drawingapp

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_brush_size.*

class MainActivity : AppCompatActivity() {

    private var mimage_button_current_paint :ImageButton? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mimage_button_current_paint = ll_paint_colors[0] as ImageButton
        mimage_button_current_paint!!.setImageDrawable(
            ContextCompat.getDrawable(this,R.drawable.pallet_pressed)
        )
      canvasbit.setBrushSize(20.toFloat())
        ib_brush.setOnClickListener()
        {
            show_dialog_brush_size()
        }
    }
    private fun show_dialog_brush_size()
    {
        val brushdialog = Dialog(this)
        brushdialog.setContentView(R.layout.dialog_brush_size)
         brushdialog.setTitle("BRUSH SIZE")
        var small = brushdialog.small
        var medium = brushdialog.medium
        var large = brushdialog.large
        small.setOnClickListener()
        {
            canvasbit.setBrushSize(10.toFloat())
            brushdialog.dismiss()
        }
        medium.setOnClickListener()
        {
            canvasbit.setBrushSize(20.toFloat())
            brushdialog.dismiss()
        }
        large.setOnClickListener()
        {
            canvasbit.setBrushSize(30.toFloat())
            brushdialog.dismiss()
        }
        brushdialog.show()
    }
    fun paintclicked(view : View)
    { if(view!=mimage_button_current_paint)
    {
        var imagebuttonpaint =view as ImageButton
        var newcolor =imagebuttonpaint.tag.toString()
        canvasbit.setcolornew(newcolor)
        imagebuttonpaint.setImageDrawable(
            ContextCompat.getDrawable(this,R.drawable.pallet_pressed)
        )

        mimage_button_current_paint!!.setImageDrawable(
            ContextCompat.getDrawable(this,R.drawable.pallet_normal)
        )
        mimage_button_current_paint=view
    }

    }
}