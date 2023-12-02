describe('template spec', () => {
  it('passes', () => {
    cy.visit('https://doasanguepoa-402142fbc80f.herokuapp.com/cadastro/usuario')
    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(1) > .campo').type('Hospit');
    cy.get(':nth-child(1) > .campo').clear('Hospital');
    cy.get(':nth-child(1) > .campo').type('Hospital Femina');
    cy.get(':nth-child(2) > .campo').clear();
    cy.get(':nth-child(2) > .campo').type('Av Francisco Trein Filho 496');
    cy.get(':nth-child(3) > .campo').clear('9');
    cy.get(':nth-child(3) > .campo').type('08044673000155');
    cy.get(':nth-child(4) > .campo').clear('h');
    cy.get(':nth-child(4) > .campo').type('hospital@hotmail.com');
    cy.get(':nth-child(5) > .campo').clear();
    cy.get(':nth-child(5) > .campo').type('123456789');
    cy.get('.Login-Btn').click();
    cy.get(':nth-child(1) > .Login-Field').clear('0');
    cy.get(':nth-child(1) > .Login-Field').type('08044673000155');
    cy.get(':nth-child(2) > .Login-Field').clear('1');
    cy.get(':nth-child(2) > .Login-Field').type('123456789');
    cy.get('.Login-Button').click();
    /* ==== End Cypress Studio ==== */
  })
})