package com.kaggle.titanic.dbservice.service;

import com.kaggle.titanic.dbservice.model.Titanic;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TitanicService {
    List<Titanic> findByPagingCriteria(Integer age, Pageable pageable);
    List<Titanic> findByLikeAndBetweenCriteria(Integer ageStart, Integer ageEnd);
}
