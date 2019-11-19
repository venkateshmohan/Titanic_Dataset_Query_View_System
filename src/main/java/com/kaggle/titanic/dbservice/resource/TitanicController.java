package com.kaggle.titanic.dbservice.resource;

import com.kaggle.titanic.dbservice.model.Titanic;
import com.kaggle.titanic.dbservice.repository.TitanicRepository;
import com.kaggle.titanic.dbservice.service.TitanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/titanic")
public class TitanicController {
    @Autowired
    private TitanicRepository titanicRepository;

    @Autowired
    private TitanicService titanicService;
    //@CrossOrigin(origins = "*")
    @GetMapping
    public String connect(){
        return "Welcome";
    }
    @GetMapping(value = "/retrieveAll")
    public List<Titanic> getAlldetails(){
        return titanicRepository.findAll();
    }
    @GetMapping(value = "/retrieve/{pid}")
    public Titanic getDetails(@PathVariable("pid") final int passenger){
             return titanicRepository.findById(passenger);
    }
    /*
    @GetMapping(value = "/paging/{number}")
    public Page<Titanic> getDetailPages(@PathVariable("number") int number, @PathVariable("search") String search){
        Pageable pageable= PageRequest.of(number,20, Sort.by("name"));
        return titanicRepository.findAll(pageable);
    }*/
    @GetMapping(value= "/filtering/{age}/{number}/{limit}")
    public List<Titanic> filterByName(@PathVariable("age") Integer age,@PathVariable("number") int number,@PathVariable("limit") int limit){
        return titanicService.findByPagingCriteria(age,PageRequest.of(number,limit,Sort.by("fare")));
    }
    @GetMapping(value= "/range/{ageStart}/{ageEnd}")
    public List<Titanic> filterByName(@PathVariable("ageStart") Integer ageStart,@PathVariable("ageEnd") int ageEnd){
        return titanicService.findByLikeAndBetweenCriteria(ageStart, ageEnd);
    }
    //@CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/survived/{alive}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Titanic> getSurvivals(@PathVariable("alive") final int alive){
          return titanicRepository.findBySurvival(alive);
    }
    //@CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void putDetails(@RequestBody final Titanic titanic){
        titanicRepository.save(titanic);
        //return titanicRepository.findById(titanic.getPassenger());
    }
    //@CrossOrigin(origins = "http://localhost:8080")
    @PutMapping(value = "/update/{pid}/{pclass}")
    public void updateDetails(@PathVariable("pclass") final int pclass, @PathVariable("pid") final int passenger){
         titanicRepository.updatePClassById(pclass,passenger);
    }
    //@CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping(value = "/delete/{pid}")
    public Titanic deleteDetails(@PathVariable("pid") final int passenger){
        Titanic t= titanicRepository.findById(passenger);
        titanicRepository.delete(t);
        return titanicRepository.findById(passenger);
    }
}
