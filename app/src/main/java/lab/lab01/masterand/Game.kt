package lab.lab01.masterand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import lab.lab01.masterand.ui.theme.MasterAndTheme
import kotlin.random.Random



@Composable
fun GameScreen(navController: NavController, number: String) {
    var  colList= mutableListOf<Color>();
    colList.add(Color.Cyan);
    colList.add(Color.Red);
    colList.add(Color.Blue);
    colList.add(Color.Yellow);
    colList.add(Color.Green);
    colList.add(Color.Magenta);
    colList.add(Color.Black);
    colList.add(Color.Gray);
    colList.add(Color(0xffffa500));
    colList.add(Color(0xffbd3aff));
    var  infoColors= mutableListOf<Color>();
    infoColors.add(Color.Red);
    infoColors.add(Color.Yellow);
    infoColors.add(Color.Transparent);

    var rightColors = selectRandomColors(colList)
    var chosenColors = rememberSaveable { mutableListOf<Color>() }
    var rowData =

    Column {
        Text(
            text = "MasterAnd",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        Text(
            text = "Score:",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 48.dp)
        )



    var chosenColors = colList
    //lista wierszy
    Box{
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            items(rowData.size) { rowNumber ->
//                GameRow(chosenColors = chosenColors, infoColors = chosenColors, clickable = true,
//                    onSelectColorCLick = {number: Int ->  }, onCheckClick = {});
//                Spacer(modifier = Modifier.height(8.dp))
//            }
            Button(
                onClick = {
                    navController.navigate(route = Screen.Results.route+"/4")
                },

                ) {
                Text(
                    "High score table"
                )
            }
            Button(
                onClick = {
                    navController.navigate(route = Screen.Entry.route)
                },

                ) {
                Text(
                    "Logout"
                )
            }
        }


    }


    }

}



@Composable
fun circularButton(onCLick: (numer: Int) -> Unit ,color: Color) {
    Button(
        onClick = {
            onCLick
                  },
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .size(50.dp) ,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
        colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = MaterialTheme.colorScheme.onBackground)
        ) {

    }
}

@Composable
fun SelectableColorRow(colorList: List<Color>, onCLick: (numer: Int) -> Unit){
    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)){
        circularButton(onCLick,colorList[0])
        Spacer(modifier = Modifier.width(2.dp))
        circularButton(onCLick,colorList[1])
        Spacer(modifier = Modifier.width(2.dp))
        circularButton(onCLick,colorList[2])
        Spacer(modifier = Modifier.width(2.dp))
        circularButton(onCLick,colorList[3])
        Spacer(modifier = Modifier.width(2.dp))
    }
}



@Composable
fun FeedbackCircles(colorList: List<Color>){
    Column(modifier = Modifier.padding(2.dp)

    ){
        Row(){
            SmallCircle(colorList[1])
            SmallCircle(colorList[2])
        }
        Row(){
            SmallCircle(colorList[3])
            SmallCircle(colorList[4])
        }
    }
}

@Composable
fun SmallCircle(color: Color){
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color)
            .size(25.dp)
            .border(2.dp, MaterialTheme.colorScheme.outline, CircleShape)
    ){


    }
}

@Composable
fun GameRow(chosenColors: List<Color>, infoColors: List<Color>, clickable : Boolean,  onSelectColorCLick: (number: Int) -> Unit, onCheckClick: () -> Unit){
    Row {
        SelectableColorRow(chosenColors, onSelectColorCLick);
        //circularButton( ,Color.Red);
        IconButton(

            onClick = onCheckClick,
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
                .background(Color.Gray, CircleShape),
            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.LightGray, contentColor = MaterialTheme.colorScheme.onBackground),
            enabled = clickable
        ) {
            Icon(Icons.Filled.Check, "Check", tint = Color.Black)
        }
        Spacer(modifier = Modifier.width(2.dp))
        FeedbackCircles(infoColors);
    }

}

fun selectNextAvailableColor(availableColors : List<Color>, chosenColors: List<Color>, number: Int): Color{
    for ( col in availableColors)
        if(!chosenColors.contains(col))
            return col
    return availableColors.last()
}

fun selectRandomColors(availableColors : List<Color>): ArrayList<Color> {
    var list = ArrayList<Color>()
    var i = 0
    while(i < 4){
        val color = availableColors[Random.nextInt(0, availableColors.size)];
        if(!list.contains(color)) {
            list.add(color);
            i++;
        }
    }
    return list;
}

fun checkColors(chosenColors: List<Color>, rightColors: List<Color>, notFound: Color){
    var list = ArrayList<Color>()
    for( i in 0 until rightColors.size){
        if(chosenColors[i] == rightColors[i])
            list.add(Color.Red);
        else if(rightColors.contains(chosenColors[i]))
            list.add(Color.Yellow)
        else list.add(notFound);
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ShowGamePrev() {
//    MasterAndTheme {
//        GameScreen()
//    }
//}