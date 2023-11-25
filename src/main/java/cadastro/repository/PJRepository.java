package cadastro.repository;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.google.common.base.Optional;

import cadastro.model.CadastroPJ;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PJRepository implements PanacheRepository<CadastroPJ> {

    public CadastroPJ salvar(CadastroPJ cadastroPJ) {
        persist(cadastroPJ);
        return cadastroPJ;
    }

    public CadastroPJ findCnpj(String cnpj) {
        return find("cnpj", cnpj).firstResult();
    }

}
