package com.example.proyecto25febrero.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyecto25febrero.R
import com.example.proyecto25febrero.navigation.Cartas


@Composable
fun LoginScreen(
    navController: NavHostController,
    isLoading: Boolean,
    onLoginClick: () -> Unit,
    cartas: Cartas

    ) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(stringResource(R.string.Login_Tittle),
            style = MaterialTheme.typography.h5
        )
        Image(
            modifier = Modifier
                .height(300.dp)
                .width(400.dp),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(R.drawable.yugi2), contentDescription = "")
        Text(stringResource(R.string.Sub_Tittle),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h2,
            fontWeight = FontWeight.Bold,
        )
        if(isLoading) {
            CircularProgressIndicator()
        }else { Button(
            onClick = onLoginClick,
            ){
            Text(stringResource(R.string.Login_Cta))
        }
        } //fin del boton
        LegalText()
    }//fin de la columna
}//fin de la funcion
@Composable
fun LegalText(){
    val anottatedString = buildAnnotatedString {
        append(stringResource(R.string.Text_Legal1))
        append(" ")
        pushStringAnnotation(tag = "URL", annotation = "app://terms")
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.secondary
            )
        ){
            append(stringResource(R.string.Text_Legal2))
        }//fin de withstyle
        append(" ")
        pop()
        append(stringResource(R.string.Text_Legal3))
        append(" ")
        append(stringResource(R.string.Text_Legal4))
    }
    Box(contentAlignment = Alignment.Center){
        ClickableText(
            modifier = Modifier.padding(24.dp),
            text = anottatedString){
            offset -> anottatedString.getStringAnnotations(
            "URL" , offset, offset
            ).
            firstOrNull()?.let{
                tag -> Log.d("App", "Ha dado click en ${tag.item}")
            }
        }//fin del texto
    }//fin del box
}//fin de la funcion legalText
