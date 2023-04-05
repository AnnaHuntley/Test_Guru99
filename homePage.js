/// <reference types="Cypress"/>

export default class HomePage1 {
  nameInputBox = 'input[name="name"]:nth-child(2)';
  selectGenderDropdown = '#exampleFormControlSelect1';
  dataBindingTextBox = 'input[name="name"]:nth-child(1)';
  enterpreneurRadioButton = '#inlineRadio3';

  openHomePage() {
    cy.visit('/angularpractice/');
  }

  typeNameInTextBox() {
    cy.get(this.nameInputBox).type('David');
  }

  selectGender() {
    cy.get(this.selectGenderDropdown).select('Male');
  }

  verifyDataBindingTextBox() {
    cy.get(this.dataBindingTextBox).should('have.value', 'David');
  }

  verifyMinimumLengthofTextBox() {
    cy.get(this.nameInputBox).should('have.attr', 'minLength', 2);
  }

  verifyCheckboxIsDisabled() {
    cy.get(this.enterpreneurRadioButton).should('be.disabled');
  }
}
