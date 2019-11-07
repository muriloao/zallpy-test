/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.zallpy.controllers;

import br.com.muriloao.zallpy.dto.ProposalDTO;
import br.com.muriloao.zallpy.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Murilo Oliveira
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class CreditController {

    @Autowired
    CreditService creditService;

    /**
     * Service to analyze a credit proposal request. Contains the logic of
     * approval and denial proposal
     *
     * @param proposalDto a proposal populaded
     * @return the proposal with status of the analyze
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<ProposalDTO> analyzeProposal(@RequestBody ProposalDTO proposalDto) {

        ProposalDTO analyzedProposalDto = this.creditService.analyzeProposal(proposalDto);

        return ResponseEntity.ok(analyzedProposalDto);
    }

}
