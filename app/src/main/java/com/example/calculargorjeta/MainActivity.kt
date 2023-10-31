package com.example.calculargorjeta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculargorjeta.ui.theme.CalcularGorjetaTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalcularGorjetaTheme {
                // A surface container using the 'background' color from the them
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun CalcularGorjeta() {
    var totalConta by remember { mutableStateOf("") }
    val valorGorjeta = calcularGorjeta(totalConta.toDoubleOrNull() ?:0.0)
    Column(

        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()

    ) {
        Text(
            text = "Calcular Gorjeta"
        )
        TextField(
            value = totalConta,
            onValueChange = { totalConta = it },
            label = {
                Text(
                    text = "Valor da Conta"
                )
            },
            isError = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )

        Spacer(
            modifier = Modifier.size(10.dp)
        )
        Text(
            text = stringResource(R.string.valor_gorjeta, valorGorjeta)
        )
    }

}

fun calcularGorjeta(totalConta:Double, porcentagemGorgeta:Double = 15.0):String {
    val gorjeta = porcentagemGorgeta / 100 * totalConta
    return NumberFormat.getCurrencyInstance().format(gorjeta)
}


