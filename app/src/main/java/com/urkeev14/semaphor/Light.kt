package com.urkeev14.semaphor

import androidx.annotation.ColorRes

class Light(
    // Determines if the light is active or not
    val isActive: Boolean,

    // Color of the light when it is on
    val color: Int,

    // Duration is seconds throughout which the light will be active
    val nextLightDuration: Int,
)