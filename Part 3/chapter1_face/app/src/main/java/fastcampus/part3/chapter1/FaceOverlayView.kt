package fastcampus.part3.chapter1

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.SizeF
import android.view.View

class FaceOverlayView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		color = Color.BLACK
		alpha = 90
		style = Paint.Style.FILL
	}

	private val facePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		color = Color.GRAY
		style = Paint.Style.STROKE
		strokeWidth = 10F
		pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
	}

	private val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)  // 가운데만 뚫을 수 있음
	}

	private val facePath = Path()

	override fun onDraw(canvas: Canvas) {
		super.onDraw(canvas)
		drawOverlay(canvas)
	}

	fun setSize(rectF: RectF, sizeF: SizeF, pointF: PointF) {
		val topOffset = sizeF.width / 2
		val bottomOffset = sizeF.width / 5

		with(facePath) {
			reset()
			moveTo(pointF.x, rectF.top)             // 포인트를 위쪽 정 가운데로 초기 이동
			cubicTo(                                // 오른쪽 반원
				rectF.right + topOffset,
				rectF.top,
				rectF.right + bottomOffset,
				rectF.bottom,
				pointF.x,
				rectF.bottom
			)
			cubicTo(                                // 왼쪽 반원
				rectF.left - bottomOffset,
				rectF.bottom,
				rectF.left - topOffset,
				rectF.top,
				pointF.x,
				rectF.top
			)
			close()
		}
		postInvalidate()
	}

	fun reset() {
		facePath.reset()
		invalidate()
	}

	private fun drawOverlay(canvas: Canvas) {
		canvas.drawRect(
			0F,
			0F,
			canvas.width.toFloat(),
			canvas.height.toFloat(),
			backgroundPaint
		)
		canvas.drawPath(facePath, maskPaint)
		canvas.drawPath(facePath, facePaint)
	}
}