package com.example.ecoguardians

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction


/**
 * A simple [Fragment] subclass.
 * Use the [DetailedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

const val ARG_PARAM1 = "param1"
const val ARG_PARAM2 = "param2"
const val ARG_PARAM3 = "param3"
const val ARG_PARAM4 = "param4"
const val ARG_PARAM5 = "param5"
const val ARG_PARAM6 = "param6"
const val ARG_PARAM7 = "param7"
const val ARG_PARAM8 = "param8"
const val ARG_PARAM9 = "param9"
const val ARG_PARAM10 = "param10"
const val ARG_PARAM11 = "param11"
const val ARG_PARAM12 = "param12"
class DetailedFragment : Fragment() {

    private var currentAnimator: Animator? = null

    private var shortAnimationDuration: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detailed, container, false)

        val scrollView = view.findViewById<ScrollView>(R.id.scrollView)
        val section1 = view.findViewById<TextView>(R.id.section1)
        val section2 = view.findViewById<TextView>(R.id.section2)
        val section3 = view.findViewById<TextView>(R.id.section3)
        val section4 = view.findViewById<TextView>(R.id.section4)

        val textView : TextView = view.findViewById(R.id.detailedActivityTV)
        val imageView : ImageView = view.findViewById(R.id.detailedActivityIV)
        val numberSpeciesTV : TextView = view.findViewById(R.id.numberSpecies)
        val classificationTV : TextView = view.findViewById(R.id.classification)
        val averageLifeTV : TextView = view.findViewById(R.id.averageLife)
        val positionTV : TextView = view.findViewById(R.id.position)

        textView.text = arguments?.getString(ARG_PARAM2)
        arguments?.let { imageView.setImageResource(it.getInt(ARG_PARAM1)) }
        numberSpeciesTV.text = arguments?.getString(ARG_PARAM3)
        classificationTV.text = arguments?.getString(ARG_PARAM4)
        averageLifeTV.text = arguments?.getString(ARG_PARAM6)
        positionTV.text = arguments?.getString(ARG_PARAM5)


        val descriptionTV: TextView = view.findViewById(R.id.content1)
        descriptionTV.text = arguments?.getString(ARG_PARAM7)
        val threatsTV: TextView = view.findViewById(R.id.content2)
        threatsTV.text = arguments?.getString(ARG_PARAM8)
        val whatYouCanDoTV: TextView = view.findViewById(R.id.content3)
        whatYouCanDoTV.text = arguments?.getString(ARG_PARAM9)
        val seriousLinkTV: TextView = view.findViewById(R.id.content4)
        seriousLinkTV.text = arguments?.getString(ARG_PARAM10)

        imageView.setOnClickListener{
            zoomImageFromThumb(imageView, arguments?.getInt(ARG_PARAM1, 0) ?: 0)
        }

        section1.setOnClickListener {
            scrollView.scrollTo(0, view.findViewById<View>(R.id.content1).top)
        }

        section2.setOnClickListener {
            scrollView.scrollTo(0, view.findViewById<View>(R.id.content2).top)
        }
        section3.setOnClickListener {
            scrollView.scrollTo(0, view.findViewById<View>(R.id.content3).top)
        }

        section4.setOnClickListener {
            scrollView.scrollTo(0, view.findViewById<View>(R.id.content4).top)
        }

        // Retrieve and cache the system's default "short" animation time.
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        val animalPositionButton : ImageButton = view.findViewById(R.id.positionButton)

        animalPositionButton.setOnClickListener{
            val mapFragment = Map.newInstance(arguments?.getDouble(ARG_PARAM11)!!,
                arguments?.getDouble(ARG_PARAM12)!!)
            val transaction : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_container, mapFragment)
            transaction.commit()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(image : Int, name: String, numberSpecies: String, classification: String,
                        position: String, averageLife: String, description: String, threats: String,
                        whatYouCanDo: String, seriousLink: String, latitude: Double, longitude: Double) =
            DetailedFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, image)
                    putString(ARG_PARAM2, name)
                    putString(ARG_PARAM3, numberSpecies)
                    putString(ARG_PARAM4, classification)
                    putString(ARG_PARAM5, position)
                    putString(ARG_PARAM6, averageLife)
                    putString(ARG_PARAM7, description)
                    putString(ARG_PARAM8, threats)
                    putString(ARG_PARAM9, whatYouCanDo)
                    putString(ARG_PARAM10, seriousLink)
                    putDouble(ARG_PARAM11, latitude)
                    putDouble(ARG_PARAM12, longitude)
                }
            }
    }
    private fun zoomImageFromThumb(thumbView: View, imageResId: Int) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        currentAnimator?.cancel()
        // Load the high-resolution "zoomed-in" image.
        val expandedImageView: ImageView = requireActivity().findViewById(R.id.expanded_image   )
        expandedImageView.setImageResource(imageResId)

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBoundsInt)
        requireActivity().findViewById<View>(R.id.main_container)
            .getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
            // Extend start bounds horizontally
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // Extend start bounds vertically
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
//        thumbView.alpha = 0f

        expandedImageView.visibility = View.VISIBLE

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.pivotX = 0f
        expandedImageView.pivotY = 0f

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        currentAnimator = AnimatorSet().apply {
            play(
                ObjectAnimator.ofFloat(
                    expandedImageView,
                    View.X,
                    startBounds.left,
                    finalBounds.left)
            ).apply {
                with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top, finalBounds.top))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
            }
            duration = shortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    currentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    currentAnimator = null
                }
            })
            start()
        }

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        expandedImageView.setOnClickListener {
            currentAnimator?.cancel()

            // Animate the four positioning/sizing properties in parallel,
            // back to their original values.
            currentAnimator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                }
                duration = shortAnimationDuration.toLong()
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }
                })
                start()
            }
        }
    }
}
