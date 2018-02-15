package com.gprosper.smack.dialogs

import android.app.Activity
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.gprosper.smack.R
import com.gprosper.smack.extensions.hideKeyboard
import com.gprosper.smack.model.Channel

/**
 * Created by gprosper on 2/4/18.
 */
class AddChannelDialog : DialogFragment() {

    interface AddChannelDialogInterface {
        fun onChannelAdded(channel: Channel)
        fun onChannelAddCanceled()
    }

    lateinit var addChannelDialogListener: AddChannelDialogInterface

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val dialogView = activity.layoutInflater.inflate(R.layout.add_channel_dialog, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.channelNameEditText)
        val descEditText = dialogView.findViewById<EditText>(R.id.channelDescEditText)

        builder.setView(dialogView)
                .setPositiveButton("Add", null)
                .setNegativeButton("Cancel"){dialogInterface, i ->
                    hideKeyboard()
                    addChannelDialogListener.onChannelAddCanceled()
                }

        return builder.create().apply {
            setOnShowListener { shownDialog ->
                with((shownDialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)) {
                    setOnClickListener {
                        val name = when(nameEditText.text.toString().isNotEmpty()){
                            true -> nameEditText.text.toString()
                            else -> {
                                Toast.makeText(activity, "Please enter name.", Toast.LENGTH_SHORT).show()
                                return@setOnClickListener
                            }
                        }
                        val desc = when(descEditText.text.toString().isNotEmpty()){
                            true -> descEditText.text.toString()
                            else -> {
                                Toast.makeText(activity, "Please enter desc.", Toast.LENGTH_SHORT).show()
                                return@setOnClickListener
                            }
                        }

                        val channel = Channel(name, desc)

                        hideKeyboard()
                        addChannelDialogListener.onChannelAdded(channel)
                        shownDialog.dismiss()
                    }
                }
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            addChannelDialogListener = context as AddChannelDialogInterface
        }
        catch (ex: ClassCastException) {
            throw ClassCastException(activity.toString()
                    + " must implement AddChannelDialogInterface")
        }
    }
}