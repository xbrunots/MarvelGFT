package br.com.gft.marvel.platform.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


fun EditText.onKeyText(response: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            response(charSequence.toString())
        }

        override fun afterTextChanged(editable: Editable) {}
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    })
}