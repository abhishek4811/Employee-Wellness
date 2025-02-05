package com.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.entity.Challenge;
import com.wipro.exception.GlobalExceptionHandler;
import com.wipro.repository.ChallengeRepository;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    public Challenge getChallengeById(Long id) throws GlobalExceptionHandler{
        return challengeRepository.findById(id)
        		.orElseThrow(() -> new GlobalExceptionHandler("challenge not found"));
    }

    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public Challenge updateChallenge(Long id, Challenge challenge) {
        Challenge existing = challengeRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setChallengeName(challenge.getChallengeName());
            existing.setDescription(challenge.getDescription());
            existing.setStartDate(challenge.getStartDate());
            existing.setEndDate(challenge.getEndDate());
            return challengeRepository.save(existing);
        }
        return null;
    }

    public boolean deleteChallenge(Long id) {
        challengeRepository.deleteById(id);
        return true;
    }
    
    public List<Challenge> getUserChallenges(Long id) {
        return challengeRepository.findByEmployeeId(id);
    }
}
