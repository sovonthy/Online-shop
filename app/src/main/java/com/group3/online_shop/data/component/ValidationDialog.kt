package com.group3.online_shop.data.component

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.DialogFragment
import com.group3.online_shop.R
import com.group3.online_shop.databinding.ComponentAlertDialogBinding

class ValidationDialog(private var message: String? = null) : DialogFragment() {

    private var _binding: ComponentAlertDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = ComponentAlertDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.errorMessageTextView.text = message
        binding.okButton.setOnClickListener {
            dialog?.dismiss()
        }
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        dialog!!.window!!.setLayout(280, LinearLayoutCompat.LayoutParams.WRAP_CONTENT)
    }

}
