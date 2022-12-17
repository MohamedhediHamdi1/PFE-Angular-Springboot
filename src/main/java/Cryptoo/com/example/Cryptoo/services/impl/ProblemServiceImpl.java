package Cryptoo.com.example.Cryptoo.services.impl;

import Cryptoo.com.example.Cryptoo.entities.ProblemEntity;
import Cryptoo.com.example.Cryptoo.repositories.ProblemRepository;
import Cryptoo.com.example.Cryptoo.services.ProblemService;
import Cryptoo.com.example.Cryptoo.shared.Utils;
import Cryptoo.com.example.Cryptoo.shared.dto.ProblemDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    Utils util;

    @Override
    public ProblemDto createproblem(ProblemDto problem) {


        ModelMapper modelMapper = new ModelMapper();

        ProblemEntity problemEntity = modelMapper.map(problem, ProblemEntity.class);
        problemEntity.setId(util.generateStringId(10));
        problemEntity.setTitle(problem.getTitle());
        problemEntity.setEmail(problem.getEmail());
        problemEntity.setProblem(problem.getProblem());
        problemEntity.setUser_id(problem.getUser_id());


        ProblemEntity newProblem = problemRepository.save(problemEntity);


        ProblemDto problemDto = modelMapper.map(newProblem, ProblemDto.class);

        return problemDto;
    }
    @Override
    public void deleteProblem(String id) {
        ProblemEntity problemEntity = problemRepository.findById(id).get();
        if (problemEntity == null)
            throw new UsernameNotFoundException(id);
        problemRepository.delete(problemEntity);

    }


}
