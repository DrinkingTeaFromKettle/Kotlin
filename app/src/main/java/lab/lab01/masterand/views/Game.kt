package lab.lab01.masterand.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import lab.lab01.masterand.navigation.Screen
import lab.lab01.masterand.viewModels.AppViewModelProvider
import lab.lab01.masterand.viewModels.GameViewModel
import lab.lab01.masterand.viewModels.ResultsViewModel
import kotlin.random.Random



@Composable
fun GameScreen(navController: NavController, number: String,  viewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory)) {


    var  infoColors = remember { mutableListOf(List(4) { Color.White }) };

    viewModel.availableColors = viewModel.colList.subList(0, number.toInt())
    if(viewModel.rightColors.isEmpty()) {
        viewModel.rightColors = selectRandomColors(viewModel.availableColors)
    }



    Column {
        Text(
            text = "MasterAnd",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        Text(
            text = "Score:" + viewModel.score.value.toString(),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 48.dp)
        )



    Box{
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(viewModel.rowData.value.size) { rowNumber ->
                GameRow(chosenColors = viewModel.rowData.value[rowNumber], infoColors = infoColors.get(rowNumber), clickable = !viewModel.gameEnd.value,
                    onSelectColorCLick = {
                        number: Int ->
                        val nextColor = selectNextAvailableColor(viewModel.availableColors, viewModel.rowData.value[rowNumber], number)
                        val newRow = viewModel.rowData.value[rowNumber].toMutableList()
                        newRow[number] = nextColor
                        viewModel.rowData.value =  viewModel.rowData.value.toMutableList().apply {
                            set(rowNumber, newRow.toList())
                        }
                    }
                    , onCheckClick = {
                        infoColors += checkColors(viewModel.rowData.value.last(), viewModel.rightColors, Color.Gray);
                        viewModel.score.value += 1

                        if (viewModel.rowData.value.last() == viewModel.rightColors) {
                            viewModel.gameEnd.value = true
                            return@GameRow
                        }
                        viewModel.rowData.value += listOf(List(4) { Color.White })
                    });
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
    }
    Box {
        Column {

            Button(
                onClick = {
                    navController.navigate(route = Screen.Results.route+"/"+viewModel.score.value + "/" + number)
                }
                //,enabled = viewModel.gameEnd.value,

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
fun circularButton(onCLick: () -> Unit ,color: Color) {
    Button(
        onClick = onCLick,
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
        circularButton( {  onCLick(0) },colorList[0])
        Spacer(modifier = Modifier.width(2.dp))
        circularButton({  onCLick(1) },colorList[1])
        Spacer(modifier = Modifier.width(2.dp))
        circularButton({  onCLick(2) },colorList[2])
        Spacer(modifier = Modifier.width(2.dp))
        circularButton({  onCLick(3) },colorList[3])
        Spacer(modifier = Modifier.width(2.dp))
    }
}



@Composable
fun FeedbackCircles(colorList: List<Color>){
    Column(modifier = Modifier.padding(2.dp)

    ){
        Row(){
            SmallCircle(colorList[0])
            SmallCircle(colorList[1])
        }
        Row(){
            SmallCircle(colorList[2])
            SmallCircle(colorList[3])
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
    var temp = 0
    if(chosenColors[number] != Color.White) {
         temp = availableColors.indexOf(chosenColors[number])
    }

    while(chosenColors.contains(availableColors[temp])) {
        temp += 1;
        if(temp == availableColors.size)
            temp = 0
    }

    return availableColors[temp]
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

fun checkColors(chosenColors: List<Color>, rightColors: List<Color>, notFound: Color): ArrayList<Color>{
    var list = ArrayList<Color>()
    for( i in rightColors.indices){
        if(chosenColors[i] == rightColors[i])
            list.add(Color.Red);
        else if(rightColors.contains(chosenColors[i]))
            list.add(Color.Yellow)
        else list.add(notFound);
    }
    return list
}

//@Preview(showBackground = true)
//@Composable
//fun ShowGamePrev() {
//    MasterAndTheme {
//        GameScreen()
//    }
//}