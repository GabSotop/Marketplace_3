package com.evalenzuela.navigation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.evalenzuela.navigation.data.model.Post
import com.evalenzuela.navigation.data.repository.PostRepositoryInterface
import com.evalenzuela.navigation.ui.screens.PostScreen
import com.evalenzuela.navigation.ui.viewmodel.PostViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class FakePostRepositoryForUI : PostRepositoryInterface {
    override suspend fun getPosts(): List<Post> {
        return listOf(

            Post(1, 1, "Raw Title 1", "Raw Body 1"),
            Post(2, 2, "Raw Title 2", "Raw Body 2")
        )
    }
}

@RunWith(AndroidJUnit4::class)
class PostScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun muestraElTituloYLosPosts() {
        val fakeRepo = FakePostRepositoryForUI()
        val fakeViewModel = PostViewModel(fakeRepo)


        composeTestRule.setContent {
            PostScreen(viewModel = fakeViewModel)
        }



        composeTestRule.onNodeWithText("Laptop Gamer: ¡Corre todo en Ultra!").assertIsDisplayed()
    }
}