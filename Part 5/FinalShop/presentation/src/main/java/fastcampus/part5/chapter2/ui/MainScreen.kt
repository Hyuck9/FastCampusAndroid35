package fastcampus.part5.chapter2.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fastcampus.part5.chapter2.R
import fastcampus.part5.chapter2.ui.theme.FinalShopTheme

sealed class MainNavigationItem(var route: String, var name: String) {
	object Main : MainNavigationItem("Main", "Main")
	object Category : MainNavigationItem("Category", "Category")
	object MyPage : MainNavigationItem("MyPage", "MyPage")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	FinalShopTheme {
		MainScreen()
	}
}

@Composable
fun MainScreen() {
	val scaffoldState = rememberScaffoldState()
	val navController = rememberNavController()
	
	Scaffold(
		scaffoldState = scaffoldState,
		bottomBar = {
			MainBottomNavigationBar(navController)
		}
	) { paddingValues ->
		MainNavigationScreen(
			navController = navController,
			modifier = Modifier.padding(paddingValues)
		)
	}
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController) {
	val bottomNavigationItems = listOf(
		MainNavigationItem.Main,
		MainNavigationItem.Category,
		MainNavigationItem.MyPage
	)

	BottomNavigation(
		backgroundColor = Color(0xffff0000),
		contentColor = Color(0xff00ff00)
	) {
		val navBackStackEntry by navController.currentBackStackEntryAsState()
		val currentRoute = navBackStackEntry?.destination?.route

		bottomNavigationItems.forEach { item ->
			BottomNavigationItem(
				icon = {
					Icon(
						painter = painterResource(id = R.drawable.ic_launcher_foreground),
						contentDescription = item.route
					)
				},
				selected = currentRoute == item.route,
				onClick = {
					navController.navigate(item.route) {
						navController.graph.startDestinationRoute?.let {
							popUpTo(it) {
								saveState = true
							}
						}
						launchSingleTop = true
						restoreState = true
					}
				}
			)
		}
	}
}

@Composable
fun MainNavigationScreen(
	navController: NavHostController,
	modifier: Modifier = Modifier
) {
	NavHost(
		navController = navController,
		startDestination = MainNavigationItem.Main.route
	) {
		composable(MainNavigationItem.Main.route) {
			Text(text = "Hello Main")
		}
		composable(MainNavigationItem.Category.route) {
			Text(text = "Hello Category")
		}
		composable(MainNavigationItem.MyPage.route) {
			Text(text = "Hello MyPage")
		}
	}
}
