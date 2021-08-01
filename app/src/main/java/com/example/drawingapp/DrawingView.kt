package com.example.drawingapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View

class DrawingView(context :Context,attrs :AttributeSet): View (context,attrs){
    private var mPathDraw :Custompath? =null
    private var mCanvasBitMap : Bitmap? =null
    private var mDrawPaint : Paint? =null
    private var mCanvasPaint :Paint? =null
    private var mBrushSize : Float= 0.toFloat()
    private var color =Color.BLACK
    private var Canvas :Canvas? =null
  private  val mpath = ArrayList<Custompath>()
    init {
        setupdrawing()
    }

    private fun setupdrawing() {
        mDrawPaint=Paint()
        mPathDraw=Custompath(color,mBrushSize)
        mDrawPaint!!.color=color
        mDrawPaint!!.style= Paint.Style.STROKE
        mDrawPaint!!.strokeJoin=Paint.Join.ROUND
        mDrawPaint!!.strokeCap=Paint.Cap.ROUND
        mCanvasPaint = Paint(Paint.DITHER_FLAG)
//        mBrushSize= 20.toFloat()

    }
// Change of size of canvas map accoering to the device screen size
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCanvasBitMap= Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
        Canvas =Canvas(mCanvasBitMap!!)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for(path in mpath)
        {
            mDrawPaint!!.strokeWidth=path.brushthickness
            mDrawPaint!!.color=path.color
            canvas.drawPath(path, mDrawPaint!!)


        }
        canvas.drawBitmap(mCanvasBitMap!!, 0f, 0f, mCanvasPaint)
        if (!mPathDraw!!.isEmpty) {
            mDrawPaint!!.strokeWidth=mPathDraw!!.brushthickness
            mDrawPaint!!.color=mPathDraw!!.color
            canvas.drawPath(mPathDraw!!, mDrawPaint!!)
        }
    }
// The Event in which when we give input
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var touchx=event?.x
        val touchy=event?.y

when(event?.action)
{
    MotionEvent.ACTION_DOWN->
    {mPathDraw!!.color=color
        mPathDraw!!.brushthickness=mBrushSize
       mPathDraw!!.reset()
        if(touchx!=null)
            if (touchy!=null) {
                mPathDraw!!.moveTo(touchx!!, touchy!!)
            }
    }
    MotionEvent.ACTION_MOVE->
    {
        if(touchx!=null)
        {
            if(touchy!=null)
            {
                mPathDraw!!.lineTo(touchx,touchy)
            }
        }
    }
    MotionEvent.ACTION_UP->
    {mpath.add(mPathDraw!!)
        mPathDraw=Custompath(color,mBrushSize)

    }
    else ->return false
}

        invalidate()
        return true
    }

    fun setBrushSize (newSize :Float)
    {
mBrushSize=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,newSize,resources.displayMetrics)
        mDrawPaint!!.strokeWidth=mBrushSize
    }
    fun setcolornew (newColor : String){
        color =Color.parseColor(newColor)
        mPathDraw!!.color=color

    }

    internal inner class Custompath (var color :Int,var brushthickness:Float ):Path() {

    }
}


