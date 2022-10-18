package com.example.laboratorio03
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemTouchHelper(
    dragDirs: Int,
    swipeDirs: Int,
    private val listener: RecyclerItemTouchHelperListener
) :
    ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
    private var foregroundView: View? = null
    private var backgroundViewDelete: View? = null
    private var backgroundViewEdit: View? = null
    private val dragColor = Color.rgb(102, 102, 255)
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }
    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags)
    }
    override fun onMove(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder != null) {
            backgroundViewDelete =
                (viewHolder as AdaptadorVuelos.ViewHolderSolicitud).viewDelete
            foregroundView = (viewHolder as AdaptadorVuelos.ViewHolderSolicitud).viewItem
            backgroundViewEdit = (viewHolder as AdaptadorVuelos.ViewHolderSolicitud).editItem
            //Selected item
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                //fancy color picked
                val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.WHITE, dragColor)
                colorAnimation.duration = 250 // milliseconds
                colorAnimation.addUpdateListener { animator ->
                    foregroundView!!.setBackgroundColor(
                        animator.animatedValue as Int
                    )
                }
                colorAnimation.start()
            }
            getDefaultUIUtil().onSelected(foregroundView)
            super.onSelectedChanged(viewHolder, actionState)
        }
    }
    override fun onChildDrawOver(
        c: Canvas, recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
        actionState: Int, isCurrentlyActive: Boolean
    ) {
        getDefaultUIUtil().onDrawOver(
            c, recyclerView, foregroundView, dX, dY,
            actionState, isCurrentlyActive
        )
    }
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        //clear view with fancy animation
        var color = Color.TRANSPARENT
        val background = foregroundView!!.background
        if (background is ColorDrawable) color = background.color
        //check color
        if (color == dragColor) {
            val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), dragColor, Color.WHITE)
            colorAnimation.duration = 250 // milliseconds
            colorAnimation.addUpdateListener { animator ->
                foregroundView!!.setBackgroundColor(
                    animator.animatedValue as Int
                )
            }
            colorAnimation.start()
        }
        super.clearView(recyclerView, viewHolder)
        getDefaultUIUtil().clearView(foregroundView)
    }
    override fun onChildDraw(
        c: Canvas, recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
        actionState: Int, isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            drawBackground(dX)
            getDefaultUIUtil().onDraw(
                c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive
            )
        }
    }
    private fun drawBackground(dX: Float) {
        if (dX > 0) {
            backgroundViewDelete!!.visibility = View.GONE
            backgroundViewEdit!!.visibility = View.VISIBLE
        } else {
            backgroundViewDelete!!.visibility = View.VISIBLE
            backgroundViewEdit!!.visibility = View.GONE
        }
        //}
    }
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
      //  listener.onSwiped(viewHolder, direction, viewHolder.adapterPosition)
    }
    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }
    interface RecyclerItemTouchHelperListener {
      //  fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int)
        fun onItemMove(source: Int, target: Int)
    }
}