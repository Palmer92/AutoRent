package com.autorent.main.controller;

import com.autorent.main.model.Vehiculo;
import com.autorent.main.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("vehiculos")
public class VehiculoController {

    @Autowired
    VehiculoRepository vehiculoRepository;

    @GetMapping("registro")
    String nuevoVehiculo(Model model)
    {
        model.addAttribute("vehiculo", new Vehiculo());
        return "vehiculos/registrar";

    }

    @PostMapping("registro")
    String registrarVehiculo(Model model, Vehiculo vehiculo)
    {
        vehiculoRepository.save(vehiculo);
        return "redirect:/vehiculos/registrar";
    }
}
