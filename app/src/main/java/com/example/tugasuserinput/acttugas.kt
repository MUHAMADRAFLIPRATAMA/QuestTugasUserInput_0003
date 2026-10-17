package com.example.tugasuserinput

import androidx.annotation.DimenRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.material3.AlertDialog

@Composable
fun spResource(@DimenRes id: Int): TextUnit = dimensionResource(id).value.sp

@Composable
fun floatResource(@DimenRes id: Int): Float =
    ResourcesCompat.getFloat(LocalContext.current.resources, id)

@Composable
fun FormBiodataScreen() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var skillAndroid by remember { mutableStateOf(false) }
    var skillUiux by remember { mutableStateOf(false) }
    var skillWeb by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf(false) }

    val isValid = firstName.isNotBlank() && lastName.isNotBlank() &&
            email.isNotBlank() && birthDate.isNotBlank() &&
            experience.isNotBlank() && (skillAndroid || skillUiux || skillWeb)

    val cardShape = RoundedCornerShape(
        topStart = dimensionResource(R.dimen.card_corner),
        topEnd = dimensionResource(R.dimen.card_corner)
    )

    Scaffold(containerColor = colorResource(R.color.screen_background)) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Image(
                painter = painterResource(R.drawable.prabowo),
                contentDescription = stringResource(R.string.header_desc),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.header_height))
            )
            PageIndicator(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(dimensionResource(R.dimen.indicator_padding))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.card_top_offset))
                    .shadow(
                        elevation = dimensionResource(R.dimen.card_elevation),
                        shape = cardShape
                    )
                    .background(
                        color = colorResource(R.color.card_background),
                        shape = cardShape
                    )
                    .padding(dimensionResource(R.dimen.card_padding))
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        text = stringResource(R.string.form_title),
                        color = colorResource(R.color.form_title),
                        fontSize = spResource(R.dimen.text_title),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = stringResource(R.string.form_subtitle),
                        color = colorResource(R.color.form_subtitle),
                        fontSize = spResource(R.dimen.text_subtitle),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = dimensionResource(R.dimen.subtitle_gap),
                                bottom = dimensionResource(R.dimen.section_gap)
                            )
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.field_gap))) {
                        FormField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            label = stringResource(R.string.first_name),
                            modifier = Modifier.weight(floatResource(R.dimen.weight_equal))
                        )
                        FormField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = stringResource(R.string.last_name),
                            modifier = Modifier.weight(floatResource(R.dimen.weight_equal))
                        )
                    }

                    Spacer(Modifier.height(dimensionResource(R.dimen.section_gap)))
                    FormField(
                        value = email,
                        onValueChange = { email = it },
                        label = stringResource(R.string.email),
                        keyboardType = KeyboardType.Email,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(dimensionResource(R.dimen.section_gap)))
                    Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.field_gap))) {
                        DateField(
                            value = birthDate,
                            onDateSelected = { birthDate = it },
                            modifier = Modifier.weight(floatResource(R.dimen.weight_equal))
                        )
                        ExperienceDropdown(
                            value = experience,
                            onSelected = { experience = it },
                            modifier = Modifier.weight(floatResource(R.dimen.weight_equal))
                        )
                    }

                    Text(
                        text = stringResource(R.string.skill_label),
                        color = colorResource(R.color.form_text),
                        fontSize = spResource(R.dimen.text_label),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.section_gap))
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(R.dimen.skill_gap_top)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SkillCheck(stringResource(R.string.skill_android), skillAndroid) { skillAndroid = it }
                        SkillCheck(stringResource(R.string.skill_uiux), skillUiux) { skillUiux = it }
                        SkillCheck(stringResource(R.string.skill_web), skillWeb) { skillWeb = it }
                    }

                    Button(
                        onClick = { showResult = true },
                        enabled = isValid,
                        shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.button_enabled),
                            contentColor = colorResource(R.color.button_text),
                            disabledContainerColor = colorResource(R.color.button_disabled),
                            disabledContentColor = colorResource(R.color.button_text_disabled)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(R.dimen.button_gap_top))
                            .height(dimensionResource(R.dimen.button_height))
                    ) {
                        Text(
                            text = stringResource(R.string.proceed),
                            fontSize = spResource(R.dimen.text_button),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
    if (showResult) {
        val skills = buildList {
            if (skillAndroid) add(stringResource(R.string.skill_android))
            if (skillUiux) add(stringResource(R.string.skill_uiux))
            if (skillWeb) add(stringResource(R.string.skill_web))
        }.joinToString(stringResource(R.string.skill_separator))

        AlertDialog(
            onDismissRequest = { showResult = false },
            title = { Text(stringResource(R.string.result_title)) },
            text = {
                Text(
                    stringResource(
                        R.string.result_message,
                        firstName, lastName, email, birthDate, experience, skills
                    )
                )
            },
            confirmButton = {
                TextButton(onClick = { showResult = false }) {
                    Text(stringResource(R.string.ok))
                }
            }
        )
    }
}

@Composable
private fun PageIndicator(modifier: Modifier = Modifier) {
    val total = integerResource(R.integer.indicator_count)
    val active = integerResource(R.integer.indicator_active_index)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.indicator_gap))
    ) {
        repeat(total) { index ->
            Box(
                modifier = Modifier
                    .size(dimensionResource(R.dimen.indicator_size))
                    .clip(CircleShape)
                    .background(
                        colorResource(
                            if (index == active) R.color.dot_active else R.color.dot_inactive
                        )
                    )
            )
        }
    }
}

@Composable
private fun fieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = colorResource(R.color.form_title),
    unfocusedBorderColor = colorResource(R.color.form_border),
    focusedTextColor = colorResource(R.color.form_text),
    unfocusedTextColor = colorResource(R.color.form_text),
    focusedLabelColor = colorResource(R.color.form_title),
    unfocusedLabelColor = colorResource(R.color.form_text)
)

@Composable
private fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontSize = spResource(R.dimen.text_field)) },
        singleLine = true,
        shape = RoundedCornerShape(dimensionResource(R.dimen.field_corner)),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = fieldColors(),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateField(
    value: String,
    onDateSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    val pattern = stringResource(R.string.date_format)

    OutlinedTextField(
        value = value,
        onValueChange = {},
        readOnly = true,
        singleLine = true,
        label = { Text(stringResource(R.string.birth_date), fontSize = spResource(R.dimen.text_field)) },
        trailingIcon = {
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = stringResource(R.string.icon_calendar)
                )
            }
        },
        shape = RoundedCornerShape(dimensionResource(R.dimen.field_corner)),
        colors = fieldColors(),
        modifier = modifier
    )

    