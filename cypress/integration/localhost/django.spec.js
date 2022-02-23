/// <reference types="cypress" />

describe('django localhost page', () => {
  it('displays default django server', () => {
    cy.visit('/')
    cy.get('.logo').should('have.text', '\n            django\n          ')
    cy.contains('The install worked successfully! Congratulations!')
  })

//   it('displays hero text', () => {
//     cy.visit('/')
//     cy.contains('Big ideas come with small bugs')
//     cy.contains('Buggerfly is a fundamentally oriented investment group that identifies and captures compelling long-term investment opportunities presented by market inefficiencies')
//     cy.contains('Affordable Pricing')
//   })
})
