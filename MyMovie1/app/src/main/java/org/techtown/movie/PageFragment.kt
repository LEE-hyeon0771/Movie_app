package org.techtown.movie

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentHostCallback
import kotlinx.android.synthetic.main.page.view.*


class PageFragment : Fragment() {

    var imageId = 0
    var title: String? = null
    var details1: String? = null
    var details2: String? = null
    var callback: FragmentCallback? = null      //FragmentCallback 인터페이스 타입의 callback 변수

    override fun onAttach(context: Context) {    //onAttach 메서드: 파라미터로 전달받은 액티비티 객체를 callback 변수에 할당
        super.onAttach(context)

        if (context is FragmentCallback) {
            callback = context
        } else {
            Log.d(TAG, "Activity is not FragmentCallback")
        }
    }

    override fun onDetach() {
        super.onDetach()

        if(callback != null){
        callback = null
    }
}
    companion object{        //동반객체로 newInstance 만듦
        private const val TAG = "PageFragment"

        fun newInstance(imageId:Int, title:String?, details1:String?, details2:String?):PageFragment{      //bundle에 새로운 속성들을 담기 put
            val fragment = PageFragment()

            val bundle = Bundle()
            bundle.putInt("imageId", imageId)
            bundle.putString("title", title)
            bundle.putString("details1",details1)
            bundle.putString("details2",details2)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {   //bundle 동반객체가 null이 아닐때 arguments 속성 데이터들을 변수에 할당함.(프래그먼트 안에서 변수에 할당된 값들을 사용할 수 있음.)
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if(bundle != null){
            imageId = bundle.getInt("imageId")
            title = bundle.getString("title")
            details1 = bundle.getString("details1")
            details2 = bundle.getString("details2")
        }

    }

    override fun onCreateView(        //속성값 처리 - 텍스트뷰와 같은 위젯에 표시
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(R.layout.page, container, false)

        rootView.posterimageView.setImageResource(imageId)
        rootView.titletextView.text = title
        rootView.details1TextView.text = details1
        rootView.details2TextView.text = details2

        rootView.detailsButton.setOnClickListener {
            if(callback != null){
                val bundle = Bundle()
                bundle.putInt("index",0)

                callback!!.onFragmentSelected(FragmentCallback.FragmentItem.ITEM2, bundle)
            }
        }

        return rootView
    }
}