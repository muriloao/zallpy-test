/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.zallpy.models;

import br.com.muriloao.zallpy.dto.ProposalDTO;
import br.com.muriloao.zallpy.enuns.MaritalStatus;
import br.com.muriloao.zallpy.enuns.Status;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Murilo Oliveira
 */
@Entity
@Table(name = "proposal")
public class Proposal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull(message = "Informe o cliente relacionado")
    private Customer customer;

    @NotNull(message = "Informe sua idade")
    private Integer age;

    @NotNull(message = "Informe o estado civil")
    private MaritalStatus maritalStatus;

    @NotNull(message = "Informe a quantidade de dependentes")
    private int dependents;

    @NotNull(message = "Informe sua renda")
    private Double income;

    private Status status;

    private Double minLimit;

    private Double maxLimit;

    private String reason;

    public ProposalDTO toDto() {
        return new ProposalDTO(this.id, this.customer != null ? this.customer.toDto() : null, this.age, this.maritalStatus, this.dependents, income, status, minLimit, maxLimit, this.reason);
    }

    public Proposal() {
    }

    public Proposal(Long id, Customer customer, Integer age, MaritalStatus maritalStatus, int dependents, Double income, Status status, Double minLimit, Double maxLimit, String reason) {
        this.id = id;
        this.customer = customer;
        this.age = age;
        this.maritalStatus = maritalStatus;
        this.dependents = dependents;
        this.income = income;
        this.status = status;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getDependents() {
        return dependents;
    }

    public void setDependents(int dependents) {
        this.dependents = dependents;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(Double minLimit) {
        this.minLimit = minLimit;
    }

    public Double getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(Double maxLimit) {
        this.maxLimit = maxLimit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proposal other = (Proposal) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
