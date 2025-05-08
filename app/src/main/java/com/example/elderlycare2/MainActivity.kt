import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.elderlycare2.data.repository.AuthRepository
import com.example.elderlycare2.navigation.AppNav
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Solution: Move token check outside composition
        val token = authRepository.getToken()

        setContent {
            AppNav(initialToken = token)
        }
    }
}

//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    @Inject
//    lateinit var authRepository: AuthRepository
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            val token = authRepository.getToken()
//            AppNav(token)
//        }
//    }
//}