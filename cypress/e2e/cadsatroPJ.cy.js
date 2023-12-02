describe('template spec', () => {
  it('passes', () => {
    cy.visit('https://doasanguepoa-402142fbc80f.herokuapp.com/')
    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(1) > .Login-Field').clear('5');
    cy.get(':nth-child(1) > .Login-Field').type('22936441000132');
    cy.get(':nth-child(2) > .Login-Field').clear('1');
    cy.get(':nth-child(2) > .Login-Field').type('123456789');
    cy.get('.Login-Button').click();
    /* ==== End Cypress Studio ==== */
  })
})