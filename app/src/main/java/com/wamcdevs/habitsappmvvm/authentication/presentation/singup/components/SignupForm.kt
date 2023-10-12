package com.wamcdevs.habitsappmvvm.authentication.presentation.singup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
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
import com.wamcdevs.habitsappmvvm.authentication.presentation.singup.SignupEvent
import com.wamcdevs.habitsappmvvm.authentication.presentation.singup.SignupState
import com.wamcdevs.habitsappmvvm.core.LocalSpacing
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitButton
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitTextfield
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitTitle

@Composable
fun SignupForm(modifier: Modifier = Modifier, state: SignupState, onEvent: (SignupEvent) -> Unit) {

    val context = LocalContext.current
    val space = LocalSpacing.current

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
                .padding(space.spaceMedium)
        ) {

            Spacer(modifier = Modifier.height(space.spaceMedium))
            HabitTitle(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.signup_title)
            )

            Spacer(modifier = Modifier.height(space.spaceMiddleLarge))

            HabitTextfield(
                modifier = Modifier.fillMaxWidth(),
                value = state.email,
                onValueChange = {
                    onEvent(SignupEvent.EmailInput(it))
                },
                placeholder = stringResource(id = R.string.placeholder_email),
                contentDescription = stringResource(id = R.string.login_description_email),
                leadingIcon = Icons.Default.Email,
                errorMessage = state.emailMsgError?.asString(context = context),
                isEnabled = !state.isLoading,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                })
            )

            HabitTextfield(
                modifier = Modifier.fillMaxWidth(),
                value = state.password,
                onValueChange = {
                    onEvent(SignupEvent.PasswordInput(it))
                },
                placeholder = stringResource(id = R.string.placeholder_password),
                contentDescription = stringResource(id = R.string.login_description_password),
                leadingIcon = Icons.Default.Lock,
                errorMessage = state.passwordMsgError?.asString(context = context),
                isEnabled = !state.isLoading,
                isPassword = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                })
            )

            HabitTextfield(
                modifier = Modifier.fillMaxWidth(),
                value = state.passwordConfirm,
                onValueChange = {
                    onEvent(SignupEvent.PasswordConfirmInput(it))
                },
                placeholder = stringResource(id = R.string.placeholder_confirm_password),
                contentDescription = stringResource(id = R.string.login_description_password_confirm),
                leadingIcon = Icons.Outlined.Lock,
                errorMessage = state.passwordConfirmMsgError?.asString(context = context),
                isEnabled = !state.isLoading,
                isPassword = true,
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    onEvent(SignupEvent.OnSignup)
                })
            )

            Spacer(modifier = Modifier.height(space.spaceMedium))

            HabitButton(modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.button_signup),
                onClick = {
                    onEvent(SignupEvent.OnSignup)
                })

            Spacer(modifier = Modifier.height(space.spaceMedium))

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onEvent(SignupEvent.OnSignin) }) {

                Text(text = buildAnnotatedString {
                    append(stringResource(id = R.string.signup_signin_text_button) + " ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(id = R.string.signup_signin_button))
                    }
                })

            }
        }


    }
}


@Preview
@Composable
fun SignupFormPreview() {
    SignupForm(state = SignupState(), onEvent = {})
}


