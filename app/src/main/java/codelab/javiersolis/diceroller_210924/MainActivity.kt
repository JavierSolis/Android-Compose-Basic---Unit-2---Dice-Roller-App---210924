package codelab.javiersolis.diceroller_210924

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import codelab.javiersolis.diceroller_210924.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                Scaffold { innerPadding->
                    DiceRollerApp(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun DiceRollerApp(modifier: Modifier) {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var isRolling by remember { mutableStateOf(false) }

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Crossfade(targetState = result) { diceValue ->
            DiceImage(result = diceValue)
        }

        Spacer(modifier = Modifier.height(16.dp))

        RollButton(onRollClick = {
            isRolling = true
            result = (1..6).random()
            Log.d("DiceRollerApp", "Dice Rolled: $result")
        })
    }
}

@Composable
fun DiceImage(result:Int) {
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Image(painter = painterResource(id = imageResource), contentDescription = "$result")
}
@Composable
fun RollButton(onRollClick:()->Unit = {}) {
    Button(onClick = onRollClick) {
        Text(
            text = stringResource(id = R.string.roll),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiceRollerApp() {
    DiceRollerTheme {
        DiceRollerApp(modifier = Modifier.fillMaxSize())
    }
}