package com.wmc.composebasiccodelabe

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.wmc.composebasiccodelabe.ui.theme.ComposeBasicCodeLabeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   enableEdgeToEdge()
        setContent {
            ComposeBasicCodeLabeTheme {
                MyAppDesign(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
@Composable
private fun onBoardingScreen(onNextClicked:()->Unit) {
    Column (
        Modifier
            .padding(15.dp)
            .fillMaxWidth(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(text = "Welcome to the Basics Jetpack Compose!" , style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(20.dp), textAlign = TextAlign.Center)
        Button(modifier = Modifier.padding(15.dp),onClick =  onNextClicked ) {
            Text(text = "Next")
        }
    }
}


@Composable
fun MyAppDesign(modifier: Modifier=Modifier){
    var showOnBoardingScreen  by rememberSaveable { mutableStateOf(true) }
    Surface(modifier=modifier) {
        if (showOnBoardingScreen){
            onBoardingScreen(onNextClicked = {
                showOnBoardingScreen = false
            })
        }else{
            showGreeting()
        }
    }



}
@Composable
fun showGreeting(){
    //val names: List<String> = listOf("Compose","World")

   val names: List<String> = List(1000) { "$it" }
  //  Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn (modifier = Modifier.padding(10.dp)){
            items(items = names){ name->
              //  for (name in names) {
                    Greeting(name =  name)
                //}
            }
        }
   // }

}

@Composable
fun Greeting(name: String) {
    Card(colors = CardDefaults.cardColors(
        contentColor = MaterialTheme.colorScheme.tertiary,
    ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        content(name

        )
    }

}

@Composable
fun content(name: String, modifier: Modifier = Modifier) {
    val expand = rememberSaveable {
        mutableStateOf(false)
    }
    //  val extraPadding = if(expand.value) 48.dp else 0.dp
    val extraPadding by animateDpAsState(targetValue =if(expand.value) 48.dp else 10.dp ,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )

    )
    Row(modifier = modifier.padding(15.dp)) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))
                .weight(1f)
        ) {
            Text(text = "Now !", style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ))
            Text(text = "$name")
            if(expand.value){
                Text(
                   color = Color.Black ,
                    text = ("Composem ipsum color sit lazy, " +
                        "padding theme elit, sed do bouncy. ").repeat(4),)
            }
        }

        IconButton(onClick = {
            expand.value = !expand.value
        }) {
          Icon(imageVector = if(expand.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
              contentDescription =if (expand.value)"Show less" else  "Show more" )

        }
    }

}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "greating dark"

)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    ComposeBasicCodeLabeTheme {
        showGreeting()
       //MyAppDesign(modifier = Modifier.fillMaxSize())
    }


}
@Preview(showBackground = true , widthDp = 320, heightDp = 320)
@Composable
fun onBoardingPreview() {
    ComposeBasicCodeLabeTheme {
        onBoardingScreen(onNextClicked = {})
    }
}