package com.chertilov.matching

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import com.chertilov.utils.getColorCompat

class ProgressView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val circleData = CircleData()

    private val bodyData = BodyData()

    private val animators = mutableListOf<AnimatorSet>()

    private val arcData = ArcData().apply {
        for (i in 0 until WAVES_COUNT) {
            animatedArcs.add(RectF())
            animatedAlphas.add(MAX_ALPHA)
        }
    }

    private val paint = Paint().apply {
        color = context.getColorCompat(R.color.colorAction)
        style = Paint.Style.STROKE
        strokeWidth = STROKE_WIDTH
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }

    //all numbers are calculated with original vector image
    @Suppress("MagicNumber")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (arcData.update(measuredHeight, measuredWidth)) {
            arcData.updateArcs(
                measuredWidth * 0.2f,
                measuredHeight * 0.2f,
                measuredWidth * 0.8f,
                measuredHeight * 0.8f
            )
        }

        if (circleData.update(measuredHeight, measuredWidth)) {
            circleData.radius = measuredWidth * 0.12f
            circleData.cy = measuredHeight * 0.5f
            circleData.cx = measuredWidth * 0.5f
        }

        if (bodyData.update(measuredHeight, measuredWidth)) {
            val right = measuredWidth * 0.7f
            val left = measuredWidth * 0.3f
            val bottom = measuredHeight * 0.95f
            val top = measuredHeight * 0.3f

            val relativeY = measuredHeight * 0.5f
            val bottomCurveY = measuredHeight * 0.7f
            val topCurveY = measuredHeight * 0.4f

            val topLeftCurveX = measuredWidth * 0.4f
            val topRightCurveX = measuredWidth * 0.6f

            val centerX = measuredHeight * 0.5f

            bodyData.path.apply {
                rewind()

                moveTo(right, relativeY)
                //bottom part (1 - right, 2 - left)
                cubicTo(right, bottomCurveY, centerX, bottom, centerX, bottom)
                cubicTo(centerX, bottom, left, bottomCurveY, left, relativeY)
                //top part (1 - left, 2 - right)
                cubicTo(left, topCurveY, topLeftCurveX, top, centerX, top)
                cubicTo(topRightCurveX, top, right, topCurveY, right, relativeY)
                close()
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //draw body
        canvas.drawCircle(circleData.cx, circleData.cy, circleData.radius, paint)
        canvas.drawPath(bodyData.path, paint)

        //draw arcs
        val initAlpha = paint.alpha

        paint.alpha = arcData.staticAlpha
        canvas.drawArc(arcData.staticArc, arcData.startAngle, arcData.sweepAngle, false, paint)

        arcData.apply {
            animatedArcs.forEachIndexed { index, arc ->
                canvas.drawArc(
                    arc,
                    startAngle,
                    sweepAngle,
                    false,
                    paint.apply { alpha = animatedAlphas[index] })
            }
        }

        paint.alpha = initAlpha
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)

        if (visibility == View.VISIBLE) start() else stop()
    }

    private fun start() {
        arcData.apply {
            animatedArcs.mapIndexedTo(animators) { index, arc ->
                animate(arc) { animatedAlphas[index] = it }.apply {
                    startDelay = index * ANIM_TIME
                    start()
                }
            }
        }
    }

    private fun stop() {
        animators.forEach { it.cancel() }
        animators.clear()
    }

    //all casts are absolutely safe here
    @Suppress("UnsafeCast")
    private fun animate(arc: RectF, updateAlpha: (Int) -> Unit): AnimatorSet {
        val alphaAnimator = ValueAnimator.ofInt(MAX_ALPHA, MIN_ALPHA).apply {
            addUpdateListener {
                updateAlpha(it.animatedValue as Int)
                invalidate()
            }
            doOnRepeat {
                updateAlpha(MAX_ALPHA)
                invalidate()
            }
            doOnFinish {
                updateAlpha(MAX_ALPHA)
                invalidate()
            }
            repeatCount = ValueAnimator.INFINITE
        }

        val sizeAnimator = ValueAnimator.ofFloat(0f, layoutParams.width / 2f).apply {
            addUpdateListener {
                arc.extend(arcData.staticArc, it.animatedValue as Float)
                invalidate()
            }
            doOnRepeat {
                resetArc(arc)
            }
            doOnFinish {
                resetArc(arc)
            }
            repeatCount = ValueAnimator.INFINITE
        }

        return AnimatorSet().apply {
            playTogether(sizeAnimator, alphaAnimator)
            interpolator = AccelerateInterpolator()
            duration = ANIM_TIME * WAVES_COUNT
        }
    }

    private fun resetArc(arc: RectF) {
        arc.set(arcData.staticArc)
        invalidate()
    }

    inner class ArcData(height: Int = -1, width: Int = -1) : UpdatableData(height, width) {
        val staticArc: RectF = RectF()

        val animatedArcs: MutableList<RectF> = mutableListOf()
        val animatedAlphas: MutableList<Int> = mutableListOf()

        val startAngle = START_ANGLE
        val sweepAngle = SWEEP_ANGLE

        val staticAlpha = MAX_ALPHA

        fun updateArcs(left: Float, top: Float, right: Float, bottom: Float) {
            staticArc.set(left, top, right, bottom)
            animatedArcs.forEach { it.set(left, top, right, bottom) }
        }
    }

    inner class CircleData(
        var cx: Float = 0f,
        var cy: Float = 0f,
        var radius: Float = -1f,
        height: Int = -1,
        width: Int = -1
    ) : UpdatableData(height, width)

    inner class BodyData(val path: Path = Path(), height: Int = -1, width: Int = -1) :
        UpdatableData(height, width)

    open inner class UpdatableData(var height: Int = -1, var width: Int = -1) {
        /**
         * @return true if necessary to update path
         */
        fun update(height: Int, width: Int) = if (height != this.height || width != this.width) {
            this.height = height
            this.width = width
            true
        } else {
            false
        }
    }

    companion object {
        private const val ANIM_TIME = 700L

        private const val MAX_ALPHA = 255
        private const val MIN_ALPHA = 0

        private const val START_ANGLE = 170f
        private const val SWEEP_ANGLE = 200f

        private const val WAVES_COUNT = 3

        private const val STROKE_WIDTH = 7f
    }
}

private fun RectF.extend(source: RectF, value: Float) {
    with(source) {
        this@extend.set(left - value, top - value, right + value, bottom + value)
    }
}

fun Animator.doOnFinish(action: () -> Unit) {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            action()
            removeListener(this)
        }

        override fun onAnimationCancel(animation: Animator?) {
            action()
            removeListener(this)
        }
    })
}

fun Animator.doOnRepeat(action: () -> Unit) {
    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationRepeat(animation: Animator?) {
            action()
        }
    })
}