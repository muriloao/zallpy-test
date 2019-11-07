/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.zallpy.services;

import br.com.muriloao.zallpy.dto.ProposalDTO;
import br.com.muriloao.zallpy.enuns.Status;
import org.springframework.stereotype.Service;

/**
 *
 * @author Murilo Oliveira
 */
@Service
public class CreditService {

    public ProposalDTO analyzeProposal(ProposalDTO proposalDto) {

        // add myself
        final double dependents = proposalDto.getDependents() + 1;
        double ratio = proposalDto.getIncome() / dependents;

        if (proposalDto.getIncome() <= 500) {
            proposalDto.setStatus(Status.DENIED);
            proposalDto.setReason("Renda baixa");
            return proposalDto;
        }
        if (proposalDto.getAge() > 35 && ratio < 1000) {
            proposalDto.setStatus(Status.DENIED);
            proposalDto.setReason("Reprovado pela política de crédito");
            return proposalDto;
        }

        if (proposalDto.getDependents() == 1 && proposalDto.getIncome() <= 1000) {
            proposalDto.setStatus(Status.APPROVED);
            proposalDto.setMinLimit(100D);
            proposalDto.setMaxLimit(500D);
        }
        if (ratio > 2500) {
            proposalDto.setStatus(Status.APPROVED);
            proposalDto.setMinLimit(2000D);
        }
        if (ratio <= 1000) {
            proposalDto.setStatus(Status.APPROVED);
            proposalDto.setMinLimit(100D);
            proposalDto.setMaxLimit(500D);
        }
        if (ratio <= 2000) {
            proposalDto.setMinLimit(1000D);
            proposalDto.setMaxLimit(1500D);
        }
        if (ratio <= 2500) {
            proposalDto.setMinLimit(1500D);
            proposalDto.setMaxLimit(2000D);
        }
        if (proposalDto.getStatus() == null) {
            proposalDto.setStatus(Status.DENIED);
            proposalDto.setReason("reprovado pela política de crédito");
        }
        System.out.println("ANALIZE " + proposalDto.getStatus() + proposalDto.getReason());

        return proposalDto;
    }
}
