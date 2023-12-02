import faker from 'faker-br';

describe('template spec', () => {
  it('passes', () => {
    const dadosSimulados = cy.dadosSimulados();
    cy.visit('https://doasanguepoa-402142fbc80f.herokuapp.com/cadastro/usuario')
    cy.get(':nth-child(1) > .campo').clear().type('Hospital Conceicao');
    cy.get(':nth-child(2) > .campo').clear().type('Av Francisco Trein Filho 496');
    const cnpj = dadosSimulados.cnpj;
    cy.get(':nth-child(3) > .campo').click().clear().type(cnpj);
    const email = dadosSimulados.email;
    cy.get(':nth-child(4) > .campo').clear().type(email);
    cy.get(':nth-child(5) > .campo').clear().type('123456789');
    cy.get('.Login-Btn').click();
    cy.get(':nth-child(1) > .Login-Field').type(cnpj);
    cy.get(':nth-child(2) > .Login-Field').type('123456789');
    cy.get('.Login-Button').click();
  })
})