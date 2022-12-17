package Cryptoo.com.example.Cryptoo.services;

import Cryptoo.com.example.Cryptoo.shared.dto.ProblemDto;

public interface ProblemService {

   // ProblemDto createproblem(String problem);

    ProblemDto createproblem(ProblemDto problem);

    void deleteProblem(String id);
}
