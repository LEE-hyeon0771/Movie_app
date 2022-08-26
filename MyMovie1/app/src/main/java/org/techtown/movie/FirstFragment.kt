package org.techtown.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_first.view.*
import org.techtown.movie.R


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_first, container, false)

            rootView.pager.adapter = PagerAdapter(requireActivity().supportFragmentManager, requireActivity().lifecycle)
            rootView.pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL  //방향
            rootView.pager.offscreenPageLimit = 3 //페이지가 3개 이므로 페이지 수를 고정
            rootView.pager.clipToPadding = false
            rootView.pager.setPadding(150,0,150,0)

        rootView.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    showToast("페이지 선택 : ${position}")
                }
            })

        return rootView
    }
        //페이지가 변경될 때 마다 알 수 있도록 설정

    fun showToast(message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

        //어댑터 설정하기(클래스로 만들어서 그 객체를 뷰페이저에 설정한다.)
        inner class PagerAdapter: FragmentStateAdapter {
            //Adapter는 생성자의 파라미터로 FragmentActivity를 상속받음
            constructor(fm: FragmentManager, lc:Lifecycle) : super(fm, lc)

            override fun getItemCount(): Int = 3   //fragment 아이템 갯수

            override fun createFragment(position: Int): Fragment {   //fragment 만들기 -> 경우의 수로 나누어줌

                var imageId:Int = 0
                var title:String = ""
                var details1:String = ""
                var details2:String = ""
                var fragment:PageFragment? = null

                when (position) {
                    0 -> {
                        imageId = R.drawable.poster1
                        title = "${position+1}. 결백"
                        details1 = "관객 수 312,745"
                        details2 = "15세 이상 관람가"

                        fragment = PageFragment.newInstance(imageId, title, details1, details2)
                    }
                    1 -> {
                        imageId = R.drawable.poster2
                        title = "${position+1}. 침입자"
                        details1 = "관객 수 166,604"
                        details2 = "15세 이상 관람가"

                        fragment = PageFragment.newInstance(imageId, title, details1, details2)
                    }
                    2 -> {
                        imageId = R.drawable.poster3
                        title = "${position+1}. 에어로너츠"
                        details1 = "관객 수 51,608"
                        details2 = "12세 이상 관람가"

                        fragment = PageFragment.newInstance(imageId, title, details1, details2)
                    }
                    else -> {
                        fragment = PageFragment.newInstance(0, "", "", "")
                    }
                }
                return fragment
            }
        }
    }
