/*
 * Copyright (c) 2013 Development Gateway.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 */

package org.devgateway.eudevfin.financial;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.time.LocalDateTime;

public class CustomFinancialTransaction extends FinancialTransaction {
    private Boolean CPA;
    private Boolean projectCoFinanced;


    private BigMoney futureDebtPrincipal;
    private BigMoney futureDebtInterest;

    private LocalDateTime dataAsPerDate;
    private LocalDateTime phasingOutYear;

    private Organization firstCoFinancingAgency;
    private Organization secondCoFinancingAgency;
    private Organization thirdCoFinancingAgency;

    private CurrencyUnit firstAgencyCurrency;
    private CurrencyUnit secondAgencyCurrency;
    private CurrencyUnit thirdAgencyCurrency;

    private BigMoney firstAgencyAmount;
    private BigMoney secondAgencyAmount;
    private BigMoney thirdAgencyAmount;

    private Category rmnch;
    private Category recipientCode;
    private Category recipientPriority;

    private String budgetCode;
    private String budgetLine;
    private String budgetActivity;

    public LocalDateTime getDataAsPerDate() {
        return dataAsPerDate;
    }

    public void setDataAsPerDate(LocalDateTime dataAsPerDate) {
        this.dataAsPerDate = dataAsPerDate;
    }

    public Boolean getCPA() {
        return CPA;
    }

    public void setCPA(Boolean CPA) {
        this.CPA = CPA;
    }

    public BigMoney getFutureDebtPrincipal() {
        return futureDebtPrincipal;
    }

    public void setFutureDebtPrincipal(BigMoney futureDebtPrincipal) {
        this.futureDebtPrincipal = futureDebtPrincipal;
    }

    public BigMoney getFutureDebtInterest() {
        return futureDebtInterest;
    }

    public void setFutureDebtInterest(BigMoney futureDebtInterest) {
        this.futureDebtInterest = futureDebtInterest;
    }

    public Boolean getProjectCoFinanced() {
        return projectCoFinanced;
    }

    public void setProjectCoFinanced(Boolean projectCoFinanced) {
        this.projectCoFinanced = projectCoFinanced;
    }

    public Organization getFirstCoFinancingAgency() {
        return firstCoFinancingAgency;
    }

    public void setFirstCoFinancingAgency(Organization firstCoFinancingAgency) {
        this.firstCoFinancingAgency = firstCoFinancingAgency;
    }

    public Organization getSecondCoFinancingAgency() {
        return secondCoFinancingAgency;
    }

    public void setSecondCoFinancingAgency(Organization secondCoFinancingAgency) {
        this.secondCoFinancingAgency = secondCoFinancingAgency;
    }

    public Organization getThirdCoFinancingAgency() {
        return thirdCoFinancingAgency;
    }

    public void setThirdCoFinancingAgency(Organization thirdCoFinancingAgency) {
        this.thirdCoFinancingAgency = thirdCoFinancingAgency;
    }

    public CurrencyUnit getFirstAgencyCurrency() {
        return firstAgencyCurrency;
    }

    public void setFirstAgencyCurrency(CurrencyUnit firstAgencyCurrency) {
        this.firstAgencyCurrency = firstAgencyCurrency;
    }

    public CurrencyUnit getSecondAgencyCurrency() {
        return secondAgencyCurrency;
    }

    public void setSecondAgencyCurrency(CurrencyUnit secondAgencyCurrency) {
        this.secondAgencyCurrency = secondAgencyCurrency;
    }

    public CurrencyUnit getThirdAgencyCurrency() {
        return thirdAgencyCurrency;
    }

    public void setThirdAgencyCurrency(CurrencyUnit thirdAgencyCurrency) {
        this.thirdAgencyCurrency = thirdAgencyCurrency;
    }

    public BigMoney getFirstAgencyAmount() {
        return firstAgencyAmount;
    }

    public void setFirstAgencyAmount(BigMoney firstAgencyAmount) {
        this.firstAgencyAmount = firstAgencyAmount;
    }

    public BigMoney getSecondAgencyAmount() {
        return secondAgencyAmount;
    }

    public void setSecondAgencyAmount(BigMoney secondAgencyAmount) {
        this.secondAgencyAmount = secondAgencyAmount;
    }

    public BigMoney getThirdAgencyAmount() {
        return thirdAgencyAmount;
    }

    public void setThirdAgencyAmount(BigMoney thirdAgencyAmount) {
        this.thirdAgencyAmount = thirdAgencyAmount;
    }

    public Category getRmnch() {
        return rmnch;
    }

    public void setRmnch(Category rmnch) {
        this.rmnch = rmnch;
    }

    public String getBudgetCode() {
        return budgetCode;
    }

    public void setBudgetCode(String budgetCode) {
        this.budgetCode = budgetCode;
    }

    public String getBudgetLine() {
        return budgetLine;
    }

    public void setBudgetLine(String budgetLine) {
        this.budgetLine = budgetLine;
    }

    public String getBudgetActivity() {
        return budgetActivity;
    }

    public void setBudgetActivity(String budgetActivity) {
        this.budgetActivity = budgetActivity;
    }

    public Category getRecipientCode() {
        return recipientCode;
    }

    public void setRecipientCode(Category recipientCode) {
        this.recipientCode = recipientCode;
    }

    public Category getRecipientPriority() {
        return recipientPriority;
    }

    public void setRecipientPriority(Category recipientPriority) {
        this.recipientPriority = recipientPriority;
    }

    public LocalDateTime getPhasingOutYear() {
        return phasingOutYear;
    }

    public void setPhasingOutYear(LocalDateTime phasingOutYear) {
        this.phasingOutYear = phasingOutYear;
    }
}
