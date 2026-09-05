package dev.bgamarra.hellojetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.bgamarra.hellojetpackcompose.ui.theme.HelloJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloComposeForm()
                }
            }
        }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloComposeForm(){
    var talla by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf<Double?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(title = {Text("Hola ESAN")})
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(paddingValues = padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Bienvenido a JetPack Compose")
            OutlinedTextField(
                value = talla,
                onValueChange = { talla = it },
                label = { Text("Ingrese talla en cm") }
            )
            OutlinedTextField(
                value = peso,
                onValueChange = { peso = it },
                label = { Text("Ingrese el peso en kg")}
            )
            Button(
                onClick = {
                    val tallam = talla.toDouble()/100
                    val pesokg = peso.toDouble()

                    imc = pesokg / (tallam * tallam)
                },
                enabled = talla.isNotEmpty() && peso.isNotEmpty()
            ) {
                Text("Calcular IMC")
            }
            if (imc != null) {
                Text(
                    text = "Tu IMC es: %.2f".format(imc)
                )
            }
        }
    }
}