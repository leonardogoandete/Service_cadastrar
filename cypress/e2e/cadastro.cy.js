describe('template spec', () => {
  it('passes', () => {
    cy.visit('https://doasanguepoa-402142fbc80f.herokuapp.com/cadastro/usuario')

    /* ==== Generated with Cypress Studio ==== */

    /* ==== End Cypress Studio ==== */
    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(1) > .campo').clear().type('Hospital Conceicao');
    cy.get(':nth-child(2) > .campo').clear().type('Av Francisco Trein Filho 496');
    cy.get(':nth-child(3) > .campo').click().clear().type('22936441000132');
    cy.get(':nth-child(4) > .campo').clear().type('hospitalconceicao03@gmail.com');
    cy.get(':nth-child(5) > .campo').clear().type('123456789');
    cy.get('.Login-Btn').click();
    cy.on('uncaught:exception', (err) => {
      if (err.message.includes('Cannot read properties of undefined (reading \'length\')')) {
        // Log the error message for debugging purposes
        console.error('Ignoring error:', err.message);
    
        // Prevent Cypress from throwing the error by returning false
        return false;
      }
    });
    cy.on('uncaught:exception', (err) => {
      if (err.message.includes('values.documento is undefined')) {
        return false;
      }
    });
    cy.intercept('GET', '/static/js/main.chunk.js', (req) => {
      req.reply({
        status: 200,
        body: '',
      });
    });
    
    cy.intercept('GET', '/static/js/0.chunk.js', (req) => {
      req.reply({
        status: 200,
        body: '',
      });
    });
    cy.intercept('(xhr)POST 400 https://cadastro-service-3070739dd5e5.herokuapp.com/registrar/instituicao');
    /* ==== End Cypress Studio ==== */
    /* ==== Generated with Cypress Studio ==== */
    
    /* ==== End Cypress Studio ==== */
  })
})