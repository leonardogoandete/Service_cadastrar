describe('template spec', () => {
  it('passes', () => {
    Cypress.Commands.add('disableDefaultErrorReporting', () => {
      cy.on('uncaught:exception', (err) => {
        // Check if the error message indicates the specific behavior you want to disable
        if (err.message.includes('This behavior is configurable, and you can choose to turn this off by listening to the `uncaught:exception` event')) {
          // Disable default error reporting
          Cypress.config('reporterOptions', {
            hideWarnings: true,
            reportRawErrors: false,
          });
    
          // Return false to prevent Cypress from throwing the error
          return false;
        }
      });
    });
    cy.visit('https://doasanguepoa-402142fbc80f.herokuapp.com/cadastro/usuario')

    /* ==== Generated with Cypress Studio ==== */

    /* ==== End Cypress Studio ==== */
    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(1) > .campo').clear().type('Hospital Conceicao');
    cy.get(':nth-child(2) > .campo').clear().type('Av Francisco Trein Filho 496');
    cy.get(':nth-child(3) > .campo').click().clear().type('22936441000132');
    cy.get(':nth-child(4) > .campo').clear().type('hospitalconceicao03@gmail.com');
    cy.get(':nth-child(5) > .campo').clear().type('123456789');
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
    cy.get('.Login-Btn').click();
    /* ==== End Cypress Studio ==== */
  })
})