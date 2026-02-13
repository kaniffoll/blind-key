import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.blindkey.app.di.initKoin
import org.blindkey.app.ui.App
import utils.initFireStore
import java.awt.Dimension

fun main() = application {
    initFireStore()
    initKoin()

    Window(
        title = "Blind key",
        state = rememberWindowState(width = 1280.dp, height = 720.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(350, 600)
        App()
    }
}

