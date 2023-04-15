package com.example.proyecto25febrero.Screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.proyecto25febrero.navigation.Cartas
import com.example.proyecto25febrero.navigation.CartasViewModel

@Composable
fun CartasCard(navController: NavController.Companion, cartas: Cartas,){

    var isExpanded by remember{ mutableStateOf(false)}
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(cartas.url)) }
    val surfaceColor by animateColorAsState(
        if(isExpanded) MaterialTheme.colors.primary else
            MaterialTheme.colors.surface
    )

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 2.dp)
    ) {
        AsyncImage(model = cartas.img,
            contentDescription = null,
            modifier= Modifier
                .clip(shape = RoundedCornerShape(2.dp))
                .clickable { isExpanded = !isExpanded }
                .fillMaxWidth()
                .size(290.dp)
            )
        Text(text =
        if (isExpanded) cartas.info
        else (""),
            color= Color.DarkGray,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                    .padding(3.dp)
       )
        Button(
            onClick = { context.startActivity(intent) },
            modifier = Modifier.width(250.dp),
        ) {
            Text(text = "Más Informacion")
       }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun AppCartas(navController: NavController, viewModel: CartasViewModel){
    //val logo
    Scaffold(
       topBar = {
           TopAppBar(){
               Text(text = "Yu gi Oh!")
           }
       } ,
       floatingActionButton = {
           FloatingActionButton(modifier = Modifier.size(32.dp),
           onClick = {/*TODO*/}) {
               Icon(
                   imageVector= Icons.Default.AddCircle,
                   contentDescription = "Agregar",
                   tint= Color.White
               )
           }
       }
    ){
        Box(modifier = Modifier.fillMaxSize()) {
            Column() {
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                ){
                    items(viewModel.cartas.value){carta->
                        CartasCard(NavController, carta)
                    }
                }
            }
        }
    }
}


