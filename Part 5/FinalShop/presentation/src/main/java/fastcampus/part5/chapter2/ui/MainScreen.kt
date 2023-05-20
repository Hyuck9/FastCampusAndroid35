package fastcampus.part5.chapter2.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import fastcampus.part5.chapter2.ui.category.CategoryScreen
import fastcampus.part5.chapter2.ui.main.MainCategoryScreen
import fastcampus.part5.chapter2.ui.main.MainHomeScreen
import fastcampus.part5.chapter2.ui.theme.FinalShopTheme
import fastcampus.part5.chapter2.viewmodel.MainViewModel
import fastcampus.part5.domain.model.Category

//sealed class MainNavigationItem(var route: String, val icon: ImageVector, var name: String) {
//	object Main : MainNavigationItem("Main", Icons.Filled.Home, "Main")
//	object Category : MainNavigationItem("Category", Icons.Filled.Star, "Category")
//	object MyPage : MainNavigationItem("MyPage", Icons.Filled.AccountBox,"MyPage")
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	FinalShopTheme {
		MainScreen()
	}
}

@Composable
fun MainScreen() {
	val viewModel = hiltViewModel<MainViewModel>()
	val scaffoldState = rememberScaffoldState()
	val navController = rememberNavController()
	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route
	
	Scaffold(
		topBar = {
			Header(viewModel)
		},
		scaffoldState = scaffoldState,
		bottomBar = {
			if (NavigationItem.MainNav.isMainRoute(currentRoute)) {
				MainBottomNavigationBar(navController, currentRoute)
			}
		}
	) { paddingValues ->
		MainNavigationScreen(
			viewModel = viewModel,
			navController = navController,
			modifier = Modifier.padding(paddingValues)
		)
	}
}

@Composable
fun Header(viewModel: MainViewModel) {
	TopAppBar(
		title = { Text(text = "My App") },
		actions =  {
			IconButton(onClick = {
				viewModel.openSearchForm()
			}) {
				Icon(Icons.Filled.Search, "SearchIcon")
			}
		}
	)
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController, currentRoute: String?) {
	val bottomNavigationItems = listOf(
		NavigationItem.MainNav.Home,
		NavigationItem.MainNav.Category,
		NavigationItem.MainNav.MyPage,
	)

	BottomNavigation {
		bottomNavigationItems.forEach { item ->
			BottomNavigationItem(
				icon = {
					Icon(
						imageVector = item.icon,
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
	viewModel: MainViewModel,
	navController: NavHostController,
	modifier: Modifier = Modifier
) {
	NavHost(
		navController = navController,
		startDestination = NavigationRouteName.MAIN_HOME
	) {
		composable(NavigationRouteName.MAIN_HOME) {
			MainHomeScreen(viewModel)
		}
		composable(NavigationRouteName.MAIN_CATEGORY) {
			MainCategoryScreen(viewModel, navController)
		}
		composable(NavigationRouteName.MAIN_MY_PAGE) {
			Text(text = "Hello MyPage")
		}
		composable(
			route = "${NavigationRouteName.CATEGORY}/{category}",
			arguments = listOf(navArgument("category") { type = NavType.StringType })
		) {
			val categoryString = it.arguments?.getString("category")
			val category = Gson().fromJson(categoryString, Category::class.java)
			if (category != null) {
				CategoryScreen(category = category)
			}
		}
	}
}
