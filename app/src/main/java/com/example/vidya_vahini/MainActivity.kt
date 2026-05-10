package com.example.vidya_vahini

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VidyaVahiniApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VidyaVahiniApp() {

    val context = LocalContext.current

    val routes = listOf(
        "Rural Route 42: Village to College",
        "Express Van: Forest Settlement to School"
    )

    val stops = listOf(
        "Village Square",
        "Highway Junction",
        "Bridge Street",
        "Old Temple",
        "College Main Gate"
    )

    var selectedRoute by remember {
        mutableStateOf("Select your commute route...")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    var currentStopIndex by remember {
        mutableIntStateOf(0)
    }

    var isBreakdown by remember {
        mutableStateOf(false)
    }

    MaterialTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F5F7))
        ) {

            // TOP BAR

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(
                            Color(0xFFFF6B00),
                            RoundedCornerShape(14.dp)
                        ),

                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.DirectionsBus,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {

                    Text(
                        text = "Vidya-Vahini",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "LIVE TRACKER",
                        color = Color(0xFFFF6B00),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Text(
                    text = "YOUR DAILY ROUTE",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // DROPDOWN

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {

                    OutlinedTextField(
                        value = selectedRoute,
                        onValueChange = {},
                        readOnly = true,

                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),

                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        },

                        shape = RoundedCornerShape(20.dp)
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {

                        routes.forEach { route ->

                            DropdownMenuItem(
                                text = {
                                    Text(route)
                                },

                                onClick = {

                                    selectedRoute = route
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                if (selectedRoute.contains("Select")) {

                    // EMPTY SCREEN

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFF6EF)
                        )
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(40.dp),

                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = Color(0xFFFFC27A),
                                modifier = Modifier.size(60.dp)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = "Please select a route to see real-time updates and pings.",
                                color = Color(0xFFC07A5B),
                                fontSize = 18.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(50.dp))

                    Text(
                        text = "DESIGNED FOR EDUCATION ACCESS",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                }

                else {

                    val currentStop = stops[currentStopIndex]

                    // STATUS CARD

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor =
                                if (isBreakdown)
                                    Color(0xFFFFE3E3)
                                else
                                    Color(0xFFDFF6E5)
                        )
                    ) {

                        Row(
                            modifier = Modifier.padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .background(
                                        if (isBreakdown)
                                            Color(0xFFFFCACA)
                                        else
                                            Color(0xFFB8F1C5),

                                        CircleShape
                                    ),

                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    imageVector =
                                        if (isBreakdown)
                                            Icons.Default.Warning
                                        else
                                            Icons.Default.DirectionsBus,

                                    contentDescription = null,

                                    tint =
                                        if (isBreakdown)
                                            Color.Red
                                        else
                                            Color(0xFF146C2E),

                                    modifier = Modifier.size(32.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column {

                                Text(
                                    text =
                                        if (isBreakdown)
                                            "Bus Breakdown Reported"
                                        else
                                            "Bus is Moving",

                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,

                                    color =
                                        if (isBreakdown)
                                            Color(0xFF8B0000)
                                        else
                                            Color(0xFF005B2E)
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Last seen at $currentStop",
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // LIVE JOURNEY MAP

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(
                                    text = "Live Journey Map",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Icon(
                                        imageVector = Icons.Default.Notifications,
                                        contentDescription = null,
                                        tint = Color.Gray
                                    )

                                    Spacer(modifier = Modifier.width(6.dp))

                                    Text(
                                        text = "Updates instantly",
                                        color = Color.Gray
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(40.dp))

                            Row(
                                modifier = Modifier.horizontalScroll(
                                    rememberScrollState()
                                ),

                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                stops.forEachIndexed { index, stop ->

                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        Box(
                                            modifier = Modifier
                                                .size(
                                                    if (index == currentStopIndex)
                                                        60.dp
                                                    else
                                                        22.dp
                                                )
                                                .background(
                                                    if (index == currentStopIndex)
                                                        Color(0xFFFF6B00)
                                                    else
                                                        Color.LightGray,

                                                    CircleShape
                                                ),

                                            contentAlignment = Alignment.Center
                                        ) {

                                            if (index == currentStopIndex) {

                                                Icon(
                                                    imageVector = Icons.Default.DirectionsBus,
                                                    contentDescription = null,
                                                    tint = Color.White
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = stop,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(40.dp))
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // UPDATE STATUS

                    Text(
                        text = "UPDATE CURRENT STATUS",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.horizontalScroll(
                            rememberScrollState()
                        )
                    ) {

                        stops.forEachIndexed { index, stop ->

                            Card(
                                modifier = Modifier
                                    .width(150.dp)
                                    .padding(end = 10.dp),

                                colors = CardDefaults.cardColors(
                                    containerColor =
                                        if (index == currentStopIndex)
                                            Color(0xFFFF6B00)
                                        else
                                            Color.White
                                )
                            ) {

                                Column(
                                    modifier = Modifier.padding(18.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text =
                                            if (index == stops.lastIndex)
                                                "END"
                                            else
                                                "STOP",

                                        color =
                                            if (index == currentStopIndex)
                                                Color.White
                                            else
                                                Color.Gray
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        text = stop,
                                        fontWeight = FontWeight.Bold,

                                        color =
                                            if (index == currentStopIndex)
                                                Color.White
                                            else
                                                Color(0xFF243B64)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    // ACTION CARDS

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .height(140.dp),

                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFEFEF)
                            )
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),

                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = Color.Red,
                                    modifier = Modifier.size(40.dp)
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Report Breakdown",
                                    color = Color.Red,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .height(140.dp),

                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFEAF2FF)
                            )
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),

                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {

                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = Color.Blue,
                                    modifier = Modifier.size(40.dp)
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "I'm Safe / Reached",
                                    color = Color.Blue,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // PING NEXT STOP BUTTON

                    Button(
                        onClick = {

                            if (currentStopIndex < stops.lastIndex) {

                                currentStopIndex++
                                isBreakdown = false

                                Toast.makeText(
                                    context,
                                    "Pinged at ${stops[currentStopIndex]}!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },

                        modifier = Modifier.fillMaxWidth(),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF6B00)
                        )
                    ) {

                        Text(
                            text = "PING NEXT STOP",
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // BREAKDOWN BUTTON

                    OutlinedButton(
                        onClick = {

                            isBreakdown = !isBreakdown

                            Toast.makeText(
                                context,
                                if (isBreakdown)
                                    "Breakdown Reported!"
                                else
                                    "Bus Moving Again!",
                                Toast.LENGTH_SHORT
                            ).show()
                        },

                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "TOGGLE BREAKDOWN STATUS",
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // SAFE REACH BUTTON

                    Button(
                        onClick = {

                            Toast.makeText(
                                context,
                                "Safe Reach signal sent to parents & school!",
                                Toast.LENGTH_LONG
                            ).show()
                        },

                        modifier = Modifier.fillMaxWidth(),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00B140)
                        )
                    ) {

                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "I'M SAFE / REACHED",
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // INFO CARD

                    Card(
                        modifier = Modifier.fillMaxWidth(),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF07152F)
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {

                            Text(
                                text = "CROWDSOURCING MATTERS",
                                color = Color(0xFFFFA500),
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Seen the bus? Tap the stop above! Your ping helps other students waiting at lonely stops manage their time better.",
                                color = Color.White,
                                lineHeight = 24.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}