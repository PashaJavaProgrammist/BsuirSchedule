package com.haretskiy.pavel.bsuirschedule.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import com.haretskiy.pavel.bsuirschedule.DIALOG_DEFAULT
import com.haretskiy.pavel.bsuirschedule.R

class GroupDefaultDialog : DialogFragment() {

    private lateinit var listener: DefaultGroupListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(activity)
                    .setMessage(getString(R.string.default_group))
                    .setPositiveButton(getString(R.string.yes)) { _, _ ->
                        listener.onClickSave()
                        dismiss()
                    }
                    .setNegativeButton(getString(R.string.no)) { _, _ ->
                        listener.onClickDismiss()
                        dismiss()
                    }
                    .create()

    fun show(fragmentManager: FragmentManager, listener: DefaultGroupListener) {
        this.listener = listener
        show(fragmentManager, DIALOG_DEFAULT)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        listener.onDismiss()
    }

    interface DefaultGroupListener {

        fun onClickSave()
        fun onClickDismiss()

        fun onDismiss()

    }
}