package org.techtown.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import org.techtown.movie.FirstFragment
import org.techtown.movie.SecondFragment
import org.techtown.movie.ThirdFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)        //툴바 설정

        val toggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()                         //토글기능 구현 -> 바로가기 되도록


        //탭 누르면 바로가기 기능 구현
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 ->{
                    onFragmentSelected(0)
                }
                R.id.item2 ->{
                    onFragmentSelected(1)
                }
                R.id.item3 ->{
                    onFragmentSelected(2)
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)

            return@setNavigationItemSelectedListener true
        }
    }

        //바로가기를 위한 함수
        fun onFragmentSelected(index: Int) {
            var fragment: Fragment = FirstFragment()

            when (index) {
                0 -> {
                    toolbar.title = "첫번째 화면"
                    fragment = FirstFragment()
                }
                1 -> {
                    toolbar.title = "두번째 화면"
                    fragment = SecondFragment()
                }
                2 -> {
                    toolbar.title = "세번째 화면"
                    fragment = ThirdFragment()
                }
            }
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }

        override fun onBackPressed() {    //뒤로가기 -> 부모 클래스에 있기 때문에 override로 구현
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                super.onBackPressed()
            }
        }
    }
