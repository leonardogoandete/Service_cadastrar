describe('template spec', () => {
  it('passes', () => {
    cy.visit('https://doasanguepoa-402142fbc80f.herokuapp.com/cadastro/usuario')

    /* ==== Generated with Cypress Studio ==== */

    /* ==== End Cypress Studio ==== */
    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(1) > .campo').clear('H');
    cy.get(':nth-child(1) > .campo').type('Hospital Conceicao');
    cy.get(':nth-child(2) > .campo').clear('A');
    cy.get(':nth-child(2) > .campo').type('Av Francisco Trein Filho 496');
    cy.get(':nth-child(3) > .campo').click();
    cy.get(':nth-child(3) > .campo').clear('5');
    cy.get(':nth-child(3) > .campo').type('22936441000132');
    cy.get(':nth-child(4) > .campo').clear('h');
    cy.get(':nth-child(4) > .campo').type('hospitalconceicao03@gmail.com');
    cy.get(':nth-child(5) > .campo').clear('1');
    cy.get(':nth-child(5) > .campo').type('123456789');
    /* ==== End Cypress Studio ==== */
    /* ==== Generated with Cypress Studio ==== */
    cy.get('.Login-Btn').click();
    /* ==== End Cypress Studio ==== */
  })
})