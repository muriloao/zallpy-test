/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.zallpy.dto;

import br.com.muriloao.zallpy.enuns.MaritalStatus;
import br.com.muriloao.zallpy.enuns.Status;
import br.com.muriloao.zallpy.models.Proposal;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Murilo Oliveira
 */
public class ProposalDTO implements Serializable {

    Long id;

    @NotNull(message = "Informe o usuario")
    private CustomerDTO customer;

    @NotNull(message = "Informe sua idade")
    private Integer age;

    @NotNull(message = "Informe o estado civil")
    private MaritalStatus maritalStatus;

    @NotNull(message = "Informe a quantidade de dependentes")
    private int dependents;

    @NotNull(message = "Informe sua renda")
    private Double income;

    private Status status = Status.PROCESSING;

    private Double minLimit;

    private Double maxLimit;

    private String reason;

    public Proposal toModel() {
        return new Proposal(this.id, this.customer != null ? this.customer.toModel() : null, this.age, this.maritalStatus, this.dependents, this.income, this.status, this.minLimit, this.maxLimit, this.reason);
    }

    @Override
    public String toString() {
        return "PropostaDTO (customer: " + this.customer.toString() + ", age: " + this.age + ", marialStatus :" + this.maritalStatus + ", dependents: " + this.dependents + ", income: " + this.income + ")";
    }

    public ProposalDTO() {
    }

    public ProposalDTO(Long id, CustomerDTO customer, Integer age, MaritalStatus maritalStatus, int dependents, Double income, Status status, Double minLimit, Double maxLimit, String reason) {
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
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

}
