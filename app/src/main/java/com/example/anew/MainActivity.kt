package com.example.anew

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = DrawerLayout()
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener  {
            when(it.itemId)  {
                R.id.profile -> Toast.makeText(applicationContext,"Clicked profile", Toast.LENGTH_SHORT).show()
                R.id.resume -> Toast.makeText(applicationContext,"Clicked resume", Toast.LENGTH_SHORT).show()
                R.id.saved -> Toast.makeText(applicationContext, "Clicked saved", Toast.LENGTH_SHORT).show()
                R.id.settings -> Toast.makeText(applicationContext, "Clicked settings", Toast.LENGTH_SHORT).show()
            }
            true
        }

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()
        val fourthFragment = FourthFragment()
        val fifthFragment = FifthFragment()

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> setCurrentFragment(firstFragment)
                R.id.courses -> setCurrentFragment(secondFragment)
                R.id.search -> setCurrentFragment(thirdFragment)
                R.id.plan -> setCurrentFragment(fourthFragment)
                R.id.jobs -> setCurrentFragment(fifthFragment)
            }
            true
        }
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (toggle.onOptionsItemSelected(item)) {
                return true
            }
            return super.onOptionsItemSelected(item)
        }

        setCurrentFragment(firstFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate((R.menu.app_bar_menu, menu))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.message -> Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show()
            R.id.newPost -> Toast.makeText(this, "Add a new Post", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun setCurrentFragment(fragment: firstFragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flfragment, fragment)
            commit()
        }
    }
}