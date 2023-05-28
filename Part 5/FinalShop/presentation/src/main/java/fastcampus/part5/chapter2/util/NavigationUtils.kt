package fastcampus.part5.chapter2.util

import android.net.Uri
import android.os.Parcelable
import androidx.navigation.NavHostController
import com.google.gson.Gson
import fastcampus.part5.chapter2.ui.*
import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.model.Product

object NavigationUtils {

	fun navigate(
		controller: NavHostController,
		routeName: String,
		backStackRouteName: String? =null,
		isLaunchSingleTop: Boolean= true,
		needToRestoreState: Boolean= true
	) {
		controller.navigate(routeName) {
			if(backStackRouteName != null) {
				popUpTo(backStackRouteName) { saveState = true}
			}
			launchSingleTop = isLaunchSingleTop
			restoreState = needToRestoreState
		}
	}

	fun findDestination(route : String?) : Destination {
		return when(route) {
			NavigationRouteName.MAIN_MY_PAGE -> MainNav.MyPage
			NavigationRouteName.MAIN_LIKE -> MainNav.Like
			NavigationRouteName.MAIN_HOME -> MainNav.Home
			NavigationRouteName.MAIN_CATEGORY -> MainNav.Category
			NavigationRouteName.SEARCH -> SearchNav
			NavigationRouteName.BASKET -> BasketNav
			NavigationRouteName.PURCHASE_HISTORY -> PurchaseHistoryNav

			ProductDetailNav.routeWithArgName() -> ProductDetailNav
			CategoryNav.routeWithArgName() -> CategoryNav
			else -> MainNav.Home
		}
	}
}