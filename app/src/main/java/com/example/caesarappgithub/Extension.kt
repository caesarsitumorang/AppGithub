package com.example.caesarappgithub

import android.view.View
fun View.showLoading(isLoading : Boolean){
    this.visibility = when(isLoading){
        true -> View.VISIBLE
        false -> View.INVISIBLE
    }
}