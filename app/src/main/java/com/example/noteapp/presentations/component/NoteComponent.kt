package com.example.noteapp.presentations.component


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text

import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIconDefaults
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputTex(
    modifier: Modifier = Modifier,
    text : String,
    label : String,
    onTextChange : (String)->Unit,
    onImeAction: ()->Unit = {},
    maxLine : Int = 1
){
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent),
        maxLines = maxLine,
        label = { Text(text = label)},
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )



}
@Composable
fun NoteButton(
    modifier: Modifier= Modifier,
    text : String,
    onClick : ()->Unit,
    enabled:Boolean = true
){
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier

    ) {
        Text(text = text)


    }

}