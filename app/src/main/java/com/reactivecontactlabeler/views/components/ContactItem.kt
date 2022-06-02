package com.reactivecontactlabeler.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.reactivecontactlabeler.data.mocks.mkContacts
import com.reactivecontactlabeler.models.Contact

@Composable
fun ContactItem(contact: Contact) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .fillMaxSize()
                    .clip(CircleShape),
                painter = rememberAsyncImagePainter(contact.profileImageURL),
                contentScale = ContentScale.Crop,
                contentDescription = "Contact profile image"
            )
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 8.dp)
            ) {
                Text(
                    text = contact.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Text(
                    text = contact.phoneNo,
                    fontSize = 14.sp,
                )
            }
        }
    }
}

@Preview
@Composable
fun ContactItemPreview() {
    ContactItem(mkContacts[0])
}