package com.moudy.alshafie.Ui


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.moudy.alshafie.MainActivity
import com.moudy.alshafie.R
import com.moudy.alshafie.ViewPagerTransformer.ForegroundToBackgroundPageTransformer


class StepsActivity : AppCompatActivity() {
    private var viewPager: ViewPager? = null
    private var myViewPagerAdapter: MyViewPagerAdapter? = null
    private var btnNext: Button? = null
    private val about_title_array = arrayOf(
        "هذا هو النص مثال",
        "هذا هو النص مثال",
        "هذا هو النص مثال"
    )
    private val about_description_array = arrayOf(
        "هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص العربى، حيث يمكنك",
        "هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص العربى، حيث يمكنك",
        "هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة، لقد تم توليد هذا النص من مولد النص العربى، حيث يمكنك"
    )
    private val about_images_array = intArrayOf(
     R.drawable.step1,
        R.drawable.step2,
        R.drawable.step3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)
        initComponent()
    }

    private fun initComponent() {
        viewPager = findViewById<View>(R.id.view_pager) as ViewPager
        btnNext = findViewById<View>(R.id.btn_next) as Button

        // adding bottom dots
        bottomProgressDots(0)
        myViewPagerAdapter = MyViewPagerAdapter()
        viewPager!!.adapter = myViewPagerAdapter
        viewPager!!.setPageTransformer(true, ForegroundToBackgroundPageTransformer())
        viewPager!!.addOnPageChangeListener(viewPagerPageChangeListener)
        btnNext!!.setOnClickListener {
            val current = viewPager!!.currentItem + 1
            if (current < MAX_STEP) {
                // move to next screen
                viewPager!!.setPageTransformer(true, ForegroundToBackgroundPageTransformer())
                viewPager!!.currentItem = current
            } else {
                startActivity(Intent(this@StepsActivity, SetUpAccountActivity::class.java))
                finish()
            }
        }
    }

    private fun bottomProgressDots(current_index: Int) {
        val dotsLayout = findViewById<View>(R.id.layoutDots) as LinearLayout
        val dots = arrayOfNulls<ImageView>(MAX_STEP)
        dotsLayout.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            val width_height = 15
            val params =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams(width_height, width_height))
            params.setMargins(10, 10, 10, 10)
            dots[i]!!.layoutParams = params
            dots[i]!!.setImageResource(R.drawable.shape_circle)
            dots[i]!!.setColorFilter(resources.getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN)
            dotsLayout.addView(dots[i])
        }
        if (dots.size > 0) {
            dots[current_index]!!.setImageResource(R.drawable.shape_circle)
            dots[current_index]!!
                .setColorFilter(resources.getColor(R.color.purple_200), PorterDuff.Mode.SRC_IN)
        }
    }

    //  viewpager change listener
    var viewPagerPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            bottomProgressDots(position)
            if (position == about_title_array.size - 1) {
                btnNext!!.text = getString(R.string.GOT_IT)
                btnNext!!.setTextColor(Color.WHITE)
            } else {
                btnNext!!.text = getString(R.string.NEXT)
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    /**
     * View pager adapter
     */
    inner class MyViewPagerAdapter : PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater =
                getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view: View = layoutInflater!!.inflate(R.layout.item_stepper, container, false)
            (view.findViewById<View>(R.id.title) as TextView).text = about_title_array[position]
            (view.findViewById<View>(R.id.description) as TextView).text =
                about_description_array[position]
            (view.findViewById<View>(R.id.image) as ImageView).setImageResource(
                about_images_array[position]
            )
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return about_title_array.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }

    companion object {
        private const val MAX_STEP = 3
    }
}