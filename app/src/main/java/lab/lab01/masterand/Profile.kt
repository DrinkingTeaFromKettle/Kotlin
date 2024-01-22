package lab.lab01.masterand

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Entity
import androidx.room.PrimaryKey
import lab.lab01.masterand.R

@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val login: String,
    val description: String
)

@Composable
fun ProfileCard(profile:Profile){
    Column {
        Row {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_baseline_question_mark_24),
                contentDescription = "Profile photo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = profile.login,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Text(
                    text = profile.description
                )
            }
        }
        Button(onClick = {
            
        },
            modifier = Modifier.fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        ) {
            Text("Przycisk")
        }
    }



}




@Preview
@Composable
fun PreviewProfileCard(){
    val profile: Profile = Profile("Login",  "Fusce id malesuada ante, sit amet dapibus dolor. Aenean a felis ex. Praesent sagittis interdum enim.")
    ProfileCard(profile);
}