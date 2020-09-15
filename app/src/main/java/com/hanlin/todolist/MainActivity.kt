package com.hanlin.todolist

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewTaskDialogFragment.NewTaskDialogListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { showNewTaskUi() }
    }

    private fun showNewTaskUi() {
        val newFragment = NewTaskDialogFragment.newInstance(getString(R.string.add_new_dialog_title))
        newFragment.show(supportFragmentManager, "newTask")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, task: String) {
        Snackbar.make(fab, "Task $task Added Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        TODO("Not yet implemented")
    }
}