package com.example.condosamvvm.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.condosamvvm.R
import com.example.condosamvvm.domain.model.Contrato
import com.example.condosamvvm.presentation.Screen
import com.example.condosamvvm.presentation.vistacontrato.ContratoList
import com.example.condosamvvm.presentation.vistacontrato.ContratoViewModel
import com.example.condosamvvm.presentation.vistacontrato.ContratosCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavHostController){

    Box(
        Modifier
            .fillMaxSize()
    ){
        //Fondo
        Image(
            painter = painterResource(id = R.drawable.wallapaper),
            contentDescription = null,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)

        Login(modifier = Modifier
            .align(Alignment.Center)
            .padding(32.dp),
            loginViewModel, navController)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(email :String, onTextChanged: (String) -> Unit){

    TextField(
        value = email, onValueChange = {onTextChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, CircleShape),
        placeholder = {Text(text = "Email/Dni")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
        singleLine = true,
        shape = CircleShape,
        colors = textFieldColors(
            containerColor = Color.Transparent,
            textColor = Color(0xFF636262),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Person, contentDescription = null)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password : String, onTextChanged: (String) -> Unit){
    var passwordVisibility by remember { mutableStateOf(false) }

    TextField(
        value = password, onValueChange = {onTextChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, CircleShape),
        placeholder = {Text(text = "Contraseña")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        colors = textFieldColors(
            containerColor =Color.Transparent,
            textColor = Color(0xFF636262),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent),
        trailingIcon   = {
            val imagen = if(passwordVisibility){
                Icons.Filled.VisibilityOff
            }else{
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = imagen, contentDescription = "Show password")
            }
        },
        visualTransformation = if(passwordVisibility){
            VisualTransformation.None
        }else{
            PasswordVisualTransformation()
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Key, contentDescription = null)
        }
    )
}

@Composable
fun ForgotPassword(modifier: Modifier){
    Text(
        text = "¿Olvidaste la contraseña?",
        modifier = modifier.clickable {  },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xff4EA8E9),
        textDecoration = TextDecoration.Underline
    )
}

@Composable
fun LoginButton(loginEnable : Boolean, onLoginSelected: () -> Unit){
    Button(
        onClick = { onLoginSelected() },
        enabled = loginEnable,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Iniciar sesión")
    }
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.condosa_1),
        contentDescription = "Header",
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Login(modifier: Modifier, loginViewModel: LoginViewModel, navController : NavHostController){

    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")

    val loginEnable: Boolean by loginViewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by loginViewModel.isLoading.observeAsState(initial = false)
    //val isLoggedIn: Boolean by loginViewModel.isLoggedIn.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    val isSelectedIndex: Int by loginViewModel.isSelectedIndex.observeAsState(initial = 0)
    val list = listOf("Contratos Pendientes", "Contratos Completados")
    val pagerState = rememberPagerState(0)

    var seleccion: Int = 0

    if(isLoading){
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
    else{

        Column(
            modifier
        ){
            //Spacer(Modifier.windowInsetsTopHeight(WindowInsets.systemBars))
            HeaderImage(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(height = 200.dp, width = 200.dp)
                    .padding(top = 50.dp)
            )
            Text(
                text = "CONDOSA",
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = 50.dp),
                fontSize = 32.sp,
                color = Color.White
            )

            LaunchedEffect(isSelectedIndex){
                pagerState.animateScrollToPage(isSelectedIndex)



            }
            LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress){
                if(!pagerState.isScrollInProgress){
                    loginViewModel.selectedIndexChanged(pagerState.currentPage)
                }
            }
            System.out.println("INDEX ESTATE: "+isSelectedIndex)
            System.out.println("PAGER ESTATE: "+pagerState.currentPage)


            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TabRow(
                    selectedTabIndex = isSelectedIndex,
                    containerColor = Color.Transparent,
                ) {
                    list.forEachIndexed { index, text ->
                        val selected = isSelectedIndex == index

                        Tab(
                            selected = selected,
                            onClick = { loginViewModel.selectedIndexChanged(index) },
                            modifier = if (selected) Modifier
                                .clip(RoundedCornerShape(15))
                                .background(Color(0xff062863))
                            else Modifier
                                .clip(RoundedCornerShape(15))
                                .background(Color(0xff607EC9)),
                            text = { Text(text = text, color = Color.White) }
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    pageCount = list.size,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xff9ab4ff)),
                        contentAlignment = Alignment.Center
                    ) {
                        if (index == 0) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                ) {
                                    EmailField(email) {
                                        loginViewModel.onLoginChanged(email = it, password = password)
                                    }
                                    Spacer(modifier = Modifier.padding(4.dp))
                                    PasswordField(password) {
                                        loginViewModel.onLoginChanged(email = email, password = it)
                                    }
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    ForgotPassword(modifier = Modifier.align(Alignment.End))
                                    Spacer(modifier = Modifier.padding(8.dp))

                                    LoginButton(loginEnable) {
                                        coroutineScope.launch {
                                            loginViewModel.onLoginSelected { navController.navigate(Screen.Empleado.route) }
                                        }
                                    }
                                }
                            }
                        } else {
                            LoginButton(true) {
                                navController.navigate(Screen.Empleado.route)
                            }
                        }
                    }
                }

            }
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
        }
    }
}