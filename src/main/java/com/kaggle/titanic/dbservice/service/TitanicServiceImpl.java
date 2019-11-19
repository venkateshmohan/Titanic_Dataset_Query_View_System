package com.kaggle.titanic.dbservice.service;

import com.kaggle.titanic.dbservice.model.Titanic;
import com.kaggle.titanic.dbservice.repository.TitanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class TitanicServiceImpl implements TitanicService{
    @Autowired
    private TitanicRepository titanicRepository;

    @Override
    public List<Titanic> findByPagingCriteria(Integer age, Pageable pageable){
        Page page = titanicRepository.findAll(new Specification<Titanic>() {
            @Override
            public Predicate toPredicate(Root<Titanic> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(age!=null) {
                    predicates.add((Predicate) criteriaBuilder.and(criteriaBuilder.equal(root.get("age"), age)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);

        page.getTotalElements();        // get total elements
        page.getTotalPages();           // get total pages
        return page.getContent();       // get content(list) of titanic
    }
    @Override
    public List<Titanic> findByLikeAndBetweenCriteria(Integer ageStart, Integer ageEnd){
        return titanicRepository.findAll(new Specification<Titanic>() {
            @Override
            public Predicate toPredicate(Root<Titanic> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(ageStart!=null && ageEnd!=null){
                    predicates.add(criteriaBuilder.between(root.get("age"),ageStart,ageEnd));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }
}
