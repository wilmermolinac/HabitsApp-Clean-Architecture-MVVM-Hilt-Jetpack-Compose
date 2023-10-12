package com.wamcdevs.habitsappmvvm.authentication.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.wamcdevs.habitsappmvvm.R
import com.wamcdevs.habitsappmvvm.authentication.presentation.login.LoginEvent
import com.wamcdevs.habitsappmvvm.authentication.presentation.login.LoginState
import com.wamcdevs.habitsappmvvm.core.LocalSpacing
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitButton
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitTextfield

@Composable
fun LoginForm(modifier: Modifier = Modifier, state: LoginState, onEvent: (LoginEvent) -> Unit) {

    val context = LocalContext.current
    val space = LocalSpacing.current

    // Paea poder usar los eventos de KeyboardAction debemos de llamar al LocalFocusManager
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(
                    topStart = space.spaceMiddleLarge, topEnd = space.spaceMiddleLarge
                )
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(space.spaceMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.login_form_title),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )

            Spacer(modifier = Modifier.height(space.spaceMedium))

            HabitTextfield(
                modifier = Modifier.fillMaxWidth(),
                value = state.email,
                onValueChange = {
                    onEvent(LoginEvent.EmailInput(it))
                },
                placeholder = stringResource(id = R.string.placeholder_email),
                contentDescription = stringResource(id = R.string.placeholder_email),
                errorMessage = state.emailMsgError?.asString(context), isEnabled = !state.isLoading,
                leadingIcon = Icons.Default.Email,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                ), keyboardActions = KeyboardActions(onNext = {
                    // y movemos al siguiente foco con el ImeiAction.Next
                    focusManager.moveFocus(FocusDirection.Next)
                })
            )

            Spacer(modifier = Modifier.height(space.spaceExtraSmall))

            HabitTextfield(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                onValueChange = { onEvent(LoginEvent.PasswordInput(it)) },
                placeholder = stringResource(id = R.string.placeholder_password),
                contentDescription = stringResource(id = R.string.placeholder_password),
                errorMessage = state.passwordMsgError?.asString(context),
                leadingIcon = Icons.Default.Lock,
                isPassword = true,
                isEnabled = !state.isLoading,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ), keyboardActions = KeyboardActions {
                    // Quitamos el foco
                    focusManager.clearFocus()

                    onEvent(LoginEvent.OnLogin)

                }
            )

            Spacer(modifier = Modifier.height(space.spaceMiddleLarge))

            HabitButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.login_button),
                isEnabled = !state.isLoading,
                onClick = {
                    onEvent(LoginEvent.OnLogin)
                })

            Spacer(modifier = Modifier.height(space.spaceSmall))

            TextButton(
                onClick = { onEvent(LoginEvent.OnForgotPassword) }
            ) {
                Text(
                    text = stringResource(id = R.string.login_forgot_button),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            TextButton(
                onClick = { onEvent(LoginEvent.OnSignUp) }
            ) {
                Text(

                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.login_signup_text_button) + " ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(stringResource(id = R.string.button_signup))
                        }
                    },
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Spacer(modifier = Modifier.height(space.spaceSmall))

        }


    }

}

@Preview()
@Composable
fun LoginFormPreview() {
    //LoginForm()
}