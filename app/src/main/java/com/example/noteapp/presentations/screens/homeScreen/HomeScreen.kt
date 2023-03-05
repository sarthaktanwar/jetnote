package com.example.noteapp.presentations.screens.homeScreen




import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.data.NoteDataSource
import com.example.noteapp.model.Note
import com.example.noteapp.presentations.component.NoteButton
import com.example.noteapp.presentations.component.NoteInputTex
import com.example.util.formatdate
import java.time.format.DateTimeFormatter


@Composable
fun HomeScreen(
    notes: List<Note>,
    onAddNotes:(Note)->Unit,
    onRemoveNote:(Note)->Unit
){

    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Top App Bar")
                },
                actions = {
                          Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "notif")
                },
                backgroundColor = Color(0xFFF4DFB4),
                elevation = 10.dp
            )
        }, content = {
            Column(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoteInputTex(
                    modifier = Modifier.padding(top = 9.dp),
                    text = title, label = "title",
                    onTextChange ={
                        if(it.all {char->
                                char.isLetter() || char.isWhitespace()
                            })title = it
                    }
                )
                NoteInputTex(
                    modifier = Modifier.padding(top = 9.dp),
                    text = description, label = "description", onTextChange ={
                        if(it.all {char->
                                char.isLetter() || char.isWhitespace()
                            })description = it
                    } )
                NoteButton(
                    modifier = Modifier.padding(top = 9.dp),
                    text = "Save", onClick = {
                        if(title.isNotEmpty() && description.isNotEmpty()){
                            onAddNotes(Note(title = title, description = description))
                            title = ""
                            description = ""
                            Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
                        }
                    })
                Divider(modifier = Modifier.padding(10.dp))
                LazyColumn(modifier = Modifier.align(alignment = Alignment.Start)){
                    items(notes){note->
                        NoteRow(onClick ={
                                         onRemoveNote(note)
                        } , note = note)
                    }
                }

            }

        })
}
@Composable
fun NoteRow(
    modifier: Modifier= Modifier,
    onClick : (Note)->Unit,
    note: Note
){
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFF4DFB4)
    ) {
        Column(
            modifier
                .clickable {onClick(note) }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1
            )
            Text(text = formatdate(note.entryDate.time),
                style = MaterialTheme.typography.caption
            )

        }

    }

}

@Preview
@Composable
fun Prev(){
    HomeScreen(notes = NoteDataSource().loadNotes(), onAddNotes = {}, onRemoveNote  = {})
}

