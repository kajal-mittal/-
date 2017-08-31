package com.kmdev.sandesh.utils

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.kmdev.sandesh.R

/**
 * Created by Kajal_Mittal on 29/08/17.
 */
/**
 * Singleton is a very useful pattern, This is called an object declaration, and it always has a name following the object keyword. Just like a variable declaration, an object declaration is not an expression, and cannot be used on the right hand side of an assignment statement.

If you need to write a function that can be called without having a class instance but needs access to the internals of a class (for example, a factory method), you can write it as a member of an object declaration inside that class.

 */
object Utils {

    var mAlertDialog: AlertDialog? = null
    var mLoadingDialog: ProgressDialog? = null

    fun displayDialog(context: Context, title: String, content: String, cancelable: Boolean) {
        mAlertDialog = AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setCancelable(cancelable)
                .setNegativeButton(R.string.dismiss, DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                }).create()
        mAlertDialog!!.show()
    }


    fun displayLoadingDialog(context: Context?, isCancellable: Boolean) {
        mLoadingDialog = ProgressDialog(context)
        mLoadingDialog?.setTitle(R.string.loading)
        mLoadingDialog!!.setMessage(context!!.getString(R.string.please_wait))
        mLoadingDialog!!.isIndeterminate = true
        mLoadingDialog!!.setCancelable(isCancellable)
        mLoadingDialog!!.show()

    }


    fun dismissLoadingDialog() {
        mLoadingDialog!!.dismiss()
        mLoadingDialog = null

    }
}