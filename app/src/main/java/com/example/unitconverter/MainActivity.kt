@file:Suppress("UNUSED_EXPRESSION")

package com.example.unitconverter

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.absoluteValue
import kotlin.math.roundToInt



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unitConverter()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun unitConverterPreview() {
    unitConverter()
}

val customTextStyle= androidx.compose.ui.text.TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 16.sp,
    color = Color.Blue
)

@Composable
fun unitConverter() {
    var inputValue by remember { mutableStateOf(" ") }
    var outputValue by remember { mutableStateOf(" ") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnity by remember { mutableStateOf("Meters") }
    var iExpand by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oconversionFactor = remember { mutableStateOf(1.00) }





    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value*100.0.roundToInt()/100.0 / oconversionFactor.value).roundToInt()
        outputValue = result.toString()

    }




    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
     horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "Unit Converter", modifier = Modifier.padding(15.dp), style =MaterialTheme.typography.headlineMedium, color = Color.Blue)


        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue=it
            convertUnits()
        }, label ={ Text(text = "Enter Value")} )
        
        Row {
            //input box
            Box {
                //input button
                Button(onClick = { iExpand = true}) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand= false }) {
                    DropdownMenuItem(text = { "Centimeters" },
                        onClick = {
                            iExpand= false
                        inputUnit = "Centimeter"
                        conversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { "Feets" },
                        onClick = {
                            iExpand= false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(text = { "Meters" },
                        onClick = {
                            iExpand= false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        })


                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Box {//output button
                Button(onClick = { oExpanded = true })//output button
                {
                    Text(text = "outputUnit")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")

                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { "Centimeters" },
                        onClick = {  oExpanded= false
                            inputUnit = "Centimeters"
                            oconversionFactor.value = 0.01
                            convertUnits() })
                    DropdownMenuItem(text = { "Feets" },
                        onClick = { oExpanded= false
                            inputUnit = "Feets"
                            oconversionFactor.value = 0.3048
                            convertUnits() })
                    DropdownMenuItem(text = { "Meters" },
                        onClick = { oExpanded= false
                            inputUnit = "Meters"
                            oconversionFactor.value = 1.0
                            convertUnits() })
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Result Ba!",
            style = MaterialTheme.typography.headlineMedium)
        }

    }
}
