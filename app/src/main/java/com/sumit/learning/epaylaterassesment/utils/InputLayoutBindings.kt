package com.sumit.learning.epaylaterassesment.utils

import android.databinding.BindingAdapter
import android.support.design.widget.TextInputLayout

@BindingAdapter(value = ["errorText"])
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage
}
@BindingAdapter(value = ["errorText"])
fun setErrorMessageInt(view: TextInputLayout, errorMessage: Int?) {
    view.error = errorMessage?.let { view.context.getString(errorMessage)}
}