import faker from 'faker-br';

describe('template spec', () => {
  it('passes', () => {
    cy.visit('https://doasanguepoa-402142fbc80f.herokuapp.com/cadastro/usuario')
    cy.get(':nth-child(1) > .campo').clear().type('Jose Alberto');
    cy.get(':nth-child(2) > .campo').clear().type('Nilo Pecanha 1500');
    const cpf= faker.br.cpf();
    cy.get(':nth-child(3) > .campo').click().clear().type(cpf);
    const email = faker.internet.email();
    cy.get(':nth-child(4) > .campo').clear().type(email);
    cy.get(':nth-child(5) > .campo').clear().type('123456789');
    cy.get('.Login-Btn').click();
    cy.get(':nth-child(1) > .Login-Field').type(cpf);
    cy.get(':nth-child(2) > .Login-Field').type('123456789');
    cy.get('.Login-Button').click();
  })
})