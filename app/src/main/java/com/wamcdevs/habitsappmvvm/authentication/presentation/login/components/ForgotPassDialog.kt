package com.wamcdevs.habitsappmvvm.authentication.presentation.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.wamcdevs.habitsappmvvm.R
import com.wamcdevs.habitsappmvvm.authentication.presentation.login.LoginEvent
import com.wamcdevs.habitsappmvvm.authentication.presentation.login.LoginState
import com.wamcdevs.habitsappmvvm.core.LocalSpacing
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitButton
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitTextfield
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitTitle

@Composable
fun ForgotPassDialog(
    state: LoginState, onEvent: (LoginEvent) -> Unit, onDismissRequest: () -> Unit
) {

    val space = LocalSpacing.current
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Dialog(
        onDismissRequest = { onDismissRequest() }, properties = DialogProperties(
            dismissOnBackPress = true, dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = space.spaceExtraSmall)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(space.spaceMedium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                HabitTitle(text = stringResource(id = R.string.login_dialog_forgotpassword_title))

                Spacer(modifier = Modifier.height(space.spaceSmall))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.login_dialog_forgotpassword_subtitle),
                    style = MaterialTheme.typography.titleSmall, textAlign = TextAlign.Start
                )

                HabitTextfield(
                    value = state.forgotPassword,
                    onValueChange = {
                        onEvent(LoginEvent.ForgotPasswordInput(it))
                    },
                    placeholder = stringResource(id = R.string.placeholder_email),
                    contentDescription = stringResource(id = R.string.login_description_email),
                    leadingIcon = Icons.Default.Email,
                    errorMessage = state.forgotPasswordMsgError?.asString(context),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                        onEvent(LoginEvent.OnForgotPassword)
                    })
                )

                Text(
                    text = stringResource(id = R.string.login_dialog_forgotpassword_subtitle2),
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(space.spaceMedium))

                HabitButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.button_confirm),
                    onClick = {
                        onEvent(LoginEvent.OnForgotPasswordBtnConfirm)
                    })
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ForgotPassDialogPreview() {
    ForgotPassDialog(state = LoginState(), onEvent = {}, onDismissRequest = {})
}