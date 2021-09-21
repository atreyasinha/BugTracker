/// <reference types="cypress" />

describe('django localhost page', () => {
  it('displays default django server', () => {
    cy.visit('/')
    cy.get('.logo').should('have.text', '\n            django\n          ')
    cy.contains('The install worked successfully! Congratulations!')
  })
})
