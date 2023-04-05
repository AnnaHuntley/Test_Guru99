/// <reference types="Cypress"/>

describe('Hooks Test Case', () => {
  before(() => {
    cy.log('Inside Before Block');
  });

  beforeEach(() => {
    cy.log('Inside Before Each Block');
  });

  it('TestCase1', () => {
    const a = 'Hello World';
    cy.log('This is my first Test Case ' + a);
  });

  it('TestCase2', () => {
    cy.log('This is my Second Test Case');
  });

  it('TestCase4', () => {
    cy.log('This is my Third Test Case');
  });

  after(() => {
    cy.log('Inside After Block');
  });

  afterEach(() => {
    cy.log('Inside AfterEach Block');
  });
});
