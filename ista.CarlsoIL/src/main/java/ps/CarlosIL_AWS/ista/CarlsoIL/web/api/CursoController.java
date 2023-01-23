package ps.CarlosIL_AWS.ista.CarlsoIL.web.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ps.CarlosIL_AWS.ista.CarlsoIL.models.Curso;
import ps.CarlosIL_AWS.ista.CarlsoIL.repository.CursoRepositoryo;
import ps.CarlosIL_AWS.ista.CarlsoIL.service.S3Service;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoRepositoryo cursoRepository;

    @Autowired
    private S3Service s3Service;

    @GetMapping
    List<Curso> getAll(){
        return cursoRepository.findAll()
                .stream()
                .peek(curso -> curso.setImagenUrl(s3Service.getObjectUrl(curso.getImagenPath())))
                .collect(Collectors.toList());
    }

    @PostMapping
    Curso create(@RequestBody Curso curso) {
        cursoRepository.save(curso);
        curso.setImagenUrl(s3Service.getObjectUrl(curso.getImagenPath()));
        curso.setCedulaUrl(s3Service.getObjectUrl(curso.getCedulaPath()));
        return curso;
    }

}
