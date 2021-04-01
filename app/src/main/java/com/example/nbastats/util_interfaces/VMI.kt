package com.example.nbastats.util_interfaces

import com.example.nbastats.data.Standings

interface VMI<T> {
    fun onResponse(response:ArrayList<T>)
}