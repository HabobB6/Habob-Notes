package com.example.note
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import note.composeapp.generated.resources.Res
import note.composeapp.generated.resources.compose_multiplatform
import note.composeapp.generated.resources.config // سيظهر خط أحمر حتى تضيف الصورة للمجلد
import note.composeapp.generated.resources.sun
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val myColor = lightColorScheme(
        primary = Color(0xFFFFFFFF),
        primaryContainer = Color(0xFF000000)
    )

    MaterialTheme(colorScheme = myColor) {
        var text by remember { mutableStateOf("") }
        val notes = remember { mutableStateListOf<String>() }

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            // 1. أيقونة الإعدادات (Config) في الزاوية العلوية اليمنى
            Image(
                painter = painterResource(Res.drawable.config),
                contentDescription = "Config",
                modifier = Modifier
                    .align(Alignment.TopEnd) // وضعها في الزاوية
                    .padding(20.dp)
                    .size(35.dp)
            )

            Column(
                modifier = Modifier
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(Res.drawable.sun),
                    contentDescription = "Sun",
                    modifier = Modifier.padding(top = 40.dp).size(80.dp)
                )

                Text(
                    text = "Habob Notes",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 30.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        placeholder = { Text("What U Think?...") },
                        modifier = Modifier.width(200.dp).height(56.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            if (text.isNotBlank()) {
                                notes.add(text)
                                text = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                    ) {
                        Text("+")
                    }
                }

                LazyColumn(
                    modifier = Modifier.width(280.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(notes) { note ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE2E2E2))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                // نص الملاحظة
                                Text(
                                    text = note,
                                    modifier = Modifier.weight(1f),
                                    fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                // زر الحذف
                                Button(
                                    onClick = {
                                        notes.remove(note)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                         containerColor = Color(0xFF6F3C3C)
                                    ),

                                ) {
                                    Text("x", color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

