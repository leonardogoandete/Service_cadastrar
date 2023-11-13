package cadastro.repository;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import cadastro.model.CadastroPF;
import cadastro.model.CadastroPJ;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PFRepository implements PanacheRepository<CadastroPF> {
    
    public CadastroPF salvar(CadastroPF cadastroPF) {
        persist(cadastroPF);
        return cadastroPF;
    }
    
}
