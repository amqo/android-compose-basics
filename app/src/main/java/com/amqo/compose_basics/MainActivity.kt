package com.amqo.compose_basics

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amqo.compose_basics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                MainActivityView()
            }
        }
    }
}

@Composable
fun MainActivityView() {

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen { shouldShowOnboarding = false }
    } else {
        Greetings()
    }
}

@Composable
fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                GreetingColumnContent(expanded = expanded, name = name)
            }
            ExpandButton(onChangeExpand = { expanded = !expanded }, expanded = expanded)
        }
    }
}

@Composable
fun GreetingColumnContent(expanded: Boolean, name: String) {
    Text(text = stringResource(R.string.hello))
    Text(text = name, style = MaterialTheme.typography.h4.copy(
        fontWeight = FontWeight.ExtraBold
    ))
    if (expanded) {
        Text(
            text = ("Composem ipsum color sit lazy, " +
                    "padding theme elit, sed do bouncy. ").repeat(4)
        )
    }
}

@Composable
fun ExpandButton(onChangeExpand: () -> Unit, expanded: Boolean) {
    IconButton(onClick = onChangeExpand) {
        Icon(
            imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore,
            contentDescription = if (expanded) {
                stringResource(R.string.show_less)
            } else {
                stringResource(R.string.show_more)
            }
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
)
@Composable
fun GreetingsPreview() {
    ComposeBasicsTheme {
        Greetings()
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun GreetingsPreviewDark() {
    ComposeBasicsTheme {
        Greetings()
    }
}

@Composable
fun OnboardingScreen(onContinue: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.onboarding_message))
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinue
            ) {
                Text(text = stringResource(R.string.continue_button))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeBasicsTheme {
        OnboardingScreen {}
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 320,
    uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnboardingPreviewDark() {
    ComposeBasicsTheme {
        OnboardingScreen {}
    }
}