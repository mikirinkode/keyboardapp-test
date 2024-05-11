package app.keyboardly.addon.calculator

import app.keyboardly.addon.calculator.action.basic.CalculatorBasicActionView
import app.keyboardly.lib.DefaultClass
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.navigation.NavigationCallback
import app.keyboardly.lib.navigation.NavigationMenuModel
import timber.log.Timber

class CalculatorDefaultClass (
    dependency: KeyboardActionDependency
) : DefaultClass(dependency), NavigationCallback {

    private val basicCalculatorView = CalculatorBasicActionView(dependency)
    private var menu = mutableListOf<NavigationMenuModel>()

    override fun onCreate() {
        viewLayout = basicCalculatorView.getView()

        menu = mutableListOf()
        initMenuList()
    }

    private fun initMenuList() {
        menu.add(
            NavigationMenuModel(
                BASIC,
                nameString = "Basic",
                icon = R.drawable.calculator_ic_baseline_calculate_24,
            )
        )
    }

    override fun getSubmenus(): MutableList<NavigationMenuModel> {
        if (menu.isEmpty()) {
            onCreate()
        }
        dependency.setNavigationCallback(this)
        return menu
    }

    override fun onClickMenu(data: NavigationMenuModel) {
        val view = when (data.id) {
            BASIC -> basicCalculatorView
            else -> null
        }
        if (view != null) {
            val isTopView = data.topView
            Timber.d("topview=$isTopView / view=$view")
            if (isTopView !=null && isTopView){
                dependency.setTopActionView(view)
            } else {
                dependency.setActionView(view)
            }
        } else {
            if (!data.enable) {
                toast("Feature on development")
            } else {
                Timber.w("enable but nothing to parse")
            }
        }
    }
    companion object {
        private const val BASIC = 1
    }
}