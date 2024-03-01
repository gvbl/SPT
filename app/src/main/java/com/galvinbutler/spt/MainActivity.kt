package com.galvinbutler.spt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.galvinbutler.spt.ui.theme.SPTTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SPTTheme {
                val timerViewModel: TimerViewModel by viewModels()
                Surface(
                    modifier = Modifier.fillMaxSize().combinedClickable(
                        onClick = {
                            timerViewModel.toggle()
                        },
                        onLongClick = {
                            timerViewModel.reset()
                        }
                    ),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Timer(timerViewModel)
                }
            }
        }
    }
}

@Composable
fun Timer(timerViewModel: TimerViewModel) {
    val time by timerViewModel.time.collectAsState()
    Seconds(value = time)
}

@Composable
fun Seconds(value: Int) {
    val size = if (value < 1000) {
        180.sp
    } else {
        120.sp
    }
    Box(modifier = Modifier
        .fillMaxSize(1.0f)) {
        Text(
            text = "$value", fontSize = size,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}