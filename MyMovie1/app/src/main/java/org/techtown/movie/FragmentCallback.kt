package org.techtown.movie

import android.os.Bundle

interface FragmentCallback {

    enum class FragmentItem{
        ITEM1, ITEM2, ITEM3
    }

    fun onFragmentSelected(item: FragmentItem, bundle: Bundle?)       //프래그먼트 선택 함수 - 프래그먼트 간 전환
}