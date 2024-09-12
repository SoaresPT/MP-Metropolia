package com.soarespt.memberofparliament

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soarespt.memberofparliament.ui.theme.MemberOfParliamentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemberOfParliamentTheme {
                Surface(
                     modifier = Modifier.fillMaxSize()
                ) {
                    MemberApp()
                }
            }
        }
    }
}

@Composable
fun MemberApp(modifier: Modifier = Modifier) {
    var currentMember by remember { mutableStateOf(ParliamentMembersData.members.random()) }
    val partyFlags = mapOf(
        "kesk" to R.drawable.keskusta,
        "ps" to R.drawable.perussuomalaiset,
        "sd" to R.drawable.sdp,
        "kok" to R.drawable.kok,
        "r" to R.drawable.r,
        "vas" to R.drawable.vas,
        "vihr" to R.drawable.vih,
        "kd" to R.drawable.kd,
        "liik" to R.drawable.liik
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Picture
        Image(
            painter = painterResource(R.drawable.portrait_placeholder),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Display member info
        Text(
            text = "${currentMember.firstname} ${currentMember.lastname}",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        //Aligned text rows
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val partyFlag = partyFlags[currentMember.party]
            if (partyFlag != null) {
                Image(
                    painter = painterResource(partyFlag),
                    contentDescription = "Party Flag",
                    modifier = Modifier.size(80.dp)
                )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Minister:", Modifier.width(100.dp), fontWeight = FontWeight.Bold)
            Text(text = if (currentMember.minister) "✅" else "❌")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Heteka Id:", Modifier.width(100.dp), fontWeight = FontWeight.Bold)
            Text(text = currentMember.hetekaId.toString())
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Seat #:", Modifier.width(95.dp), fontWeight = FontWeight.Bold)
            Text(text = currentMember.seatNumber.toString())
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Button to get the next random member
        Button(onClick = {
            currentMember = ParliamentMembersData.members.random()
            Log.d("Member", currentMember.firstname)
        }) {
            Text(text = "Random")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemberAppPreview() {
    MemberApp(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}