package com.sebastiaomoura.basiccodlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sebastiaomoura.basiccodlab.ui.theme.BasicCodlabTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicCodlabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MyApp(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    // Save the state of the onboarding screen
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(
                onContinueClicked = { shouldShowOnboarding = false }
            )
        } else {

            Column( //modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Greetings()
                MenuBar { println("Clicked") }

            }

        }
    }


}

@Composable
fun MenuBar(
    choose: () -> Unit
) {

    Column() {
        Row(modifier = Modifier.padding(2.dp)) {
            Text(text = "Saldo: ")
            Text(text = "R$ 1000,00")
            Text(text = " Gratidão: ")
            Text(text = "GT$ 1000,00")

        }
        Row() {

            Button(modifier = Modifier.padding(2.dp), onClick = choose) {
                Text(text = "Alimento")
            }
            Button(modifier = Modifier.padding(2.dp), onClick = choose) {
                Text(text = "Atividade")

            }
            Button(modifier = Modifier.padding(2.dp), onClick = choose) {
                Text(text = "Sono")
            }
        }


    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    Column {

        Row(modifier = Modifier.padding(24.dp)) {
            Text(text = "Total de Pessoas: ")
            Text(text = "1000,00")
        }
        LazyColumn(
            modifier = modifier
                .padding(vertical = 4.dp)
                .height(600.dp)
        ) {
            items(items = names) { name ->
                Greeting(name = name)
            }
        }


    }


}

@Composable
fun EatScreen() {

}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.welcome_to_the_basics_codelab))
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    ) {

        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            )
            {
                Text(text = "Qualidade")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }

        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    BasicCodlabTheme {
        // MyApp()
        Greetings()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicCodlabTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview
@Composable
fun MyAppPreview() {
    BasicCodlabTheme {
        MyApp(Modifier.fillMaxSize())
    }
}
