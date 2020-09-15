package com.hanlin.todolist

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_new_task.*
import kotlinx.coroutines.NonCancellable.cancel
import java.lang.ClassCastException

class NewTaskDialogFragment: DialogFragment() {
    interface NewTaskDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment, task: String)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    private var newTaskDialogListener: NewTaskDialogListener? = null

    companion object {
        fun newInstance(title: String): NewTaskDialogFragment {

            val newTaskDialogFragment = NewTaskDialogFragment()
            val args = Bundle()
            args.putString("dialog_title", title)
            newTaskDialogFragment.arguments = args
            return newTaskDialogFragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments?.getString("dialog_title")
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)

        val newTaskDialog = activity?.layoutInflater?.inflate(R.layout.dialog_new_task, null)
        val task = newTaskDialog?.findViewById<EditText>(R.id.task)
        builder.setView(newTaskDialog)
            .setPositiveButton(R.string.save) {dialog, id ->
                newTaskDialogListener?.onDialogPositiveClick(this, task?.text.toString())
            }
            .setNegativeButton(R.string.cancel) {dialog, id ->

            }
        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {
            try {
                newTaskDialogListener = activity as NewTaskDialogListener
            }
            catch (e: ClassCastException) {
                throw ClassCastException("$context must implement NewTaskDialogListener")
            }
        }

    }
}