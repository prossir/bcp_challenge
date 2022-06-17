package paolo.bcp.authentication.platform.front.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import paolo.bcp.authentication.R
import paolo.bcp.authentication.databinding.ActivityAuthenticationBinding


@AndroidEntryPoint
class AuthenticationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authentication)
    }

}