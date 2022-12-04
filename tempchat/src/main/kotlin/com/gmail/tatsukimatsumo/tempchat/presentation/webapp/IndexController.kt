package com.gmail.tatsukimatsumo.tempchat.presentation.webapp

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {
    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        return "index"
    }
}