package com.trackeg.trackegapps.Utilities

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.trackeg.trackegapps.Utilities.Const.SHARED_PREFERENCE_LANGUAGE_KEY

/**this class set custom font for all textviews  **/

class MyTextViewBold : AppCompatTextView {
    var languge: String?

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    ) {
        languge = SharedPrefHelper.getSharedString(context!!, SHARED_PREFERENCE_LANGUAGE_KEY)
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        languge = SharedPrefHelper.getSharedString(context!!, SHARED_PREFERENCE_LANGUAGE_KEY)
        init()
    }

    constructor(context: Context?) : super(context!!) {
        languge = SharedPrefHelper.getSharedString(context!!, SHARED_PREFERENCE_LANGUAGE_KEY)
        init()
    }

    private fun init() {
        if (!isInEditMode()) {
            if (languge == "ar") {
                val tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/.ttf")
                setTypeface(tf)
            } else {
                val tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/.ttf")
                setTypeface(tf)
            }
        }
    }
}