package com.trackeg.trackegapps.Utilities

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.trackeg.trackegapps.Utilities.Const.SHARED_PREFERENCE_LANGUAGE_KEY
import com.trackeg.trackegapps.Utilities.SharedPrefHelper.Companion.getSharedString

/**this class set custom font for all Buttons **/
class MyButton : AppCompatButton {
    var languge: String?

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        languge = getSharedString(context, SHARED_PREFERENCE_LANGUAGE_KEY)
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        languge = getSharedString(context, SHARED_PREFERENCE_LANGUAGE_KEY)
        init()
    }

    constructor(context: Context?) : super(context!!) {
        languge = getSharedString(context, SHARED_PREFERENCE_LANGUAGE_KEY)
        init()
    }

    private fun init() {
        if (!isInEditMode) {
            typeface = if (languge == "ar") {
                val tf = Typeface.createFromAsset(context.assets, "fonts/.ttf")
                tf
            } else {
                val tf = Typeface.createFromAsset(context.assets, "fonts/.ttf")
                tf
            }
        }
    }
}
