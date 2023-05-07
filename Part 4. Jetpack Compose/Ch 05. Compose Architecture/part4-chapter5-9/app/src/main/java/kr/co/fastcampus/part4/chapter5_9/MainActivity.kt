package kr.co.fastcampus.part4.chapter5_9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.co.fastcampus.part4.chapter5_9.screen.DetailScreen
import kr.co.fastcampus.part4.chapter5_9.screen.MainScreen
import kr.co.fastcampus.part4.chapter5_9.ui.theme.PokemonTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			PokemonTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					TopLevel()
				}
			}
		}
	}
}

@Composable
fun TopLevel(
	navController: NavHostController = rememberNavController(),
	modifier: Modifier = Modifier
) {

	NavHost(
		navController = navController,
		"Home",
		modifier = modifier
	) {
		composable("Home") {
			MainScreen(
				onPokemonClick = {
					val pokemonId = it.substringAfter("pokemon/")
						.substringBefore("/")
						.toInt()
					navController.navigate("Detail/${pokemonId}")
				}
			)
		}

		// 단계 3: arguments 파라미터를 설정하자.
		// ```
		// navArgument("pokemenId") {
		// type = NavType.IntType
		// }
		// ```
		// 리스트로 전달해야 한다.
		composable(
			"Detail/{pokemonId}",
		) {
			// 단계 4: `pokemonId`를 `Int`값으로 가져오자. (`arguments?.getInt`를 이용)
			val pokemonId = 0
			DetailScreen(
				pokemonId = pokemonId,
				onUpButtonClick = {
					navController.navigate("Home") {
						popUpTo("Home") {
							inclusive = true
						}
					}
				}
			)
		}
	}
}