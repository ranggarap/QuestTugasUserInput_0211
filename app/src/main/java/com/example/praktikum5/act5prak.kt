package com.example.praktikum5

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormDataDiri(modifier: Modifier = Modifier) {
    var textNamaBelakang by remember { mutableStateOf("") }
    var experienceExpanded by remember { mutableStateOf(false) }
    var textNamaDepan by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    val sectionSpacing = dimensionResource(R.dimen.section_spacing)
    var showDatePicker by remember { mutableStateOf(false) }
    var androidChecked by remember { mutableStateOf(false) }
    var uiuxChecked by remember { mutableStateOf(false) }
    var webChecked by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val experienceOptions = stringArrayResource(R.array.experience_options)
    val headerHeight = dimensionResource(R.dimen.header_height)
    val fieldShape = RoundedCornerShape(dimensionResource(R.dimen.field_corner_radius))
    val fieldSpacing = dimensionResource(R.dimen.field_spacing)
    val titleSpacing = dimensionResource(R.dimen.title_spacing)
    val datePattern = stringResource(R.string.date_pattern)
    val dateFormat = remember(datePattern) {
        SimpleDateFormat(datePattern, Locale.getDefault())
    }

    var namaD by remember { mutableStateOf("") }
    var namaB by remember { mutableStateOf("") }
    var mail by remember { mutableStateOf("") }
    var xperience by remember { mutableStateOf("") }
    var birth by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.logo_umy), // Pastikan file gambar Anda bernama 'logo_umy.jpg' (huruf kecil semua)
            contentDescription = stringResource(R.string.content_desc_background),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight)
        )

        Scaffold(containerColor = Color.Transparent) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Spacer(modifier = Modifier.height(headerHeight))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(fieldSpacing)
                ) {
                    OutlinedTextField(
                        value = textNamaDepan,
                        onValueChange = { textNamaDepan = it },
                        label = { Text(stringResource(R.string.label_first_name)) },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = textNamaBelakang,
                        onValueChange = { textNamaBelakang = it },
                        label = { Text(stringResource(R.string.label_last_name)) },
                        singleLine = true,

                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(fieldSpacing))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(stringResource(R.string.label_email)) },
                    singleLine = true,
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    shape = fieldShape,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(fieldSpacing))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(fieldSpacing)
                ) {
                    OutlinedTextField(
                        value = birthDate,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text(stringResource(R.string.label_birth_date)) },
                        singleLine = true,
                        trailingIcon = {
                            IconButton(onClick = { showDatePicker = true }) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = stringResource(R.string.content_desc_pick_date)
                                )
                            }
                        },
                        shape = fieldShape,
                        modifier = Modifier.weight(1f)
                    )

                    ExposedDropdownMenuBox(
                        expanded = experienceExpanded,
                        onExpandedChange = { experienceExpanded = it },
                        modifier = Modifier.weight(1f)
                    ) {
                        OutlinedTextField(
                            value = experience,
                            onValueChange = { },
                            readOnly = true,
                            label = { Text(stringResource(R.string.label_experience)) },
                            singleLine = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = experienceExpanded)
                            },
                            shape = fieldShape,
                            modifier = Modifier
                                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            expanded = experienceExpanded,
                            onDismissRequest = { experienceExpanded = false }
                        ) {
                            experienceOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        experience = option
                                        experienceExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(titleSpacing))

                Text(
                    text = stringResource(R.string.label_skill),
                    color = colorResource(R.color.white),
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(R.dimen.label_text_size).value.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SkillCheckbox(
                        label = stringResource(R.string.skill_android),
                        checked = androidChecked,
                        onCheckedChange = { androidChecked = it }
                    )
                    SkillCheckbox(
                        label = stringResource(R.string.skill_uiux),
                        checked = uiuxChecked,
                        onCheckedChange = { uiuxChecked = it }
                    )
                    SkillCheckbox(
                        label = stringResource(R.string.skill_web),
                        checked = webChecked,
                        onCheckedChange = { webChecked = it }
                    )
                }

                Spacer(modifier = Modifier.height(sectionSpacing))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    enabled = textNamaDepan.isNotEmpty() && textNamaBelakang.isNotEmpty() && email.isNotEmpty() && experience.isNotEmpty() && birthDate.isNotEmpty(),
                    onClick = {
                        namaD = textNamaDepan
                        namaB = textNamaBelakang
                        mail = email
                        birth = birthDate
                        xperience = experience
                        showDatePicker = false
                    }
                ) {
                    Text(text = "Proceed")
                }
            }
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        birthDate = dateFormat.format(Date(millis))
                    }
                    showDatePicker = false
                }) {
                    Text(stringResource(R.string.dialog_ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(stringResource(R.string.dialog_cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Composable
fun SkillCheckbox(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(R.color.purple_primary)
            )
        )
        Text(
            text = label,
            color = colorResource(R.color.text_secondary),
            fontSize = dimensionResource(R.dimen.label_text_size).value.sp
        )
    }
}