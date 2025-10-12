package com.autorent.main.controller;

import com.autorent.main.model.Vehiculo;
import com.autorent.main.repository.VehiculoRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("vehiculos")
public class VehiculoController {

    @Autowired
    Cloudinary cloudinary;
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
        MultipartFile archivo = vehiculo.getArchivoFoto();

        // 1. Verifica que se haya subido un archivo
        if (!archivo.isEmpty()) {
            try {
                // 2. Sube el archivo a Cloudinary
                Map uploadResult = cloudinary.uploader().upload(
                        archivo.getBytes(), // El contenido binario del archivo
                        ObjectUtils.asMap(
                                "folder", "autoventas_vehiculos" // Opcional: define una carpeta en Cloudinary
                        )
                );

                // 3. Obtiene la URL pública del resultado
                String urlFoto = uploadResult.get("secure_url").toString();

                // 4. Establece la URL en el campo 'foto' (el que va a la BD)
                vehiculo.setFoto(urlFoto);

            } catch (Exception e) {
                // Manejo de errores de Cloudinary (ej. clave incorrecta, fallo de red)
                System.err.println("Error al subir a Cloudinary: " + e.getMessage());
                // Puedes retornar una vista de error o manejarlo de otra forma
            }
        }
        return "redirect:/vehiculos/registrar";
    }
}
