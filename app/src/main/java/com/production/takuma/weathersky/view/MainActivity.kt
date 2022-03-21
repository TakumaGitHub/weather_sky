package com.production.takuma.weathersky.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.production.takuma.weathersky.R
import com.production.takuma.weathersky.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /** ViewBinding用のbindingクラス **/
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Activityで使用するbindingクラスのインスタンスを作成し、rootをsetContentViewに渡して画面上でアクティブビューにする。
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        // BottomNavigationViewにNavControllerをセット。下タブ切替でfragmentが切り替わるようにする。
        binding.bottomNavigation.setupWithNavController(
            Navigation.findNavController(this@MainActivity, R.id.nav_host_fragment_container)
        )
    }
}