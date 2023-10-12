package com.wamcdevs.habitsappmvvm.authentication.presentation.singup.components

import android.widget.AutoCompleteTextView.OnDismissListener
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.wamcdevs.habitsappmvvm.R
import com.wamcdevs.habitsappmvvm.authentication.presentation.singup.SignupEvent
import com.wamcdevs.habitsappmvvm.core.LocalSpacing
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitButton
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitTitle

@Composable
fun SignupDialog(onDismissRequest: () -> Unit, onEvent: (SignupEvent) -> Unit) {

    val space = LocalSpacing.current

    Dialog(
        onDismissRequest = { onDismissRequest() }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
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

                HabitTitle(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.signup_dialog_title)
                )

                Spacer(modifier = Modifier.padding(space.spaceSmall))

                Text(
                    text = stringResource(id = R.string.signup_dialog_subtitle),
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(space.spaceSmall))

                HabitButton(modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.login_button),
                    onClick = { onEvent(SignupEvent.OnLogin) })

            }

        }

    }

}

@Preview
@Composable
fun SignupDialogPreview() {
    SignupDialog(onDismissRequest = {  }, onEvent = {})
}