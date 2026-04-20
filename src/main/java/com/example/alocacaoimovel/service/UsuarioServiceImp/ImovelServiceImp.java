package com.example.alocacaoimovel.service.UsuarioServiceImp;

import com.example.alocacaoimovel.dto.ImovelRequest;
import com.example.alocacaoimovel.model.Imovel;
import com.example.alocacaoimovel.repository.ImovelRepository;
import com.example.alocacaoimovel.service.ImovelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImovelServiceImp implements ImovelService {

    private final ImovelRepository imovelRepository;

    public ImovelServiceImp(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    @Override
    public List<Imovel> buscarImovel(ImovelRequest buscar) {
        List<Imovel> imoveis = imovelRepository.findAll();

        return imoveis.stream()

                .filter(i -> buscar.cidade() == null || i.getLocalizacao().getCidade().equalsIgnoreCase(buscar.cidade()))
                .filter(i -> buscar.bairro() == null || i.getLocalizacao().getBairro().equalsIgnoreCase(buscar.bairro()))
                .filter(i -> buscar.regiao() == null || i.getLocalizacao().getRegiao().equalsIgnoreCase(buscar.regiao()))
                .filter(i -> buscar.proximidadePraia() == null || i.getLocalizacao().getProximidadepraia().equalsIgnoreCase(buscar.proximidadePraia()))
                .filter(i -> buscar.vagasGaragens() == null || i.getCaracteristicaImovel().getVagasGaragens() == buscar.vagasGaragens())
                .filter(i -> buscar.numerosQuartos() == null || i.getCaracteristicaImovel().getNumerosQuartos() == buscar.numerosQuartos())
                .filter(i -> buscar.numerosBanheiros() == null || i.getCaracteristicaImovel().getNumerosBanheiros() == buscar.numerosBanheiros())
                .filter(i -> buscar.possuiPiscina() == null || i.getCaracteristicaImovel().getPossuiPiscina().equalsIgnoreCase(buscar.possuiPiscina()))
                .filter(i -> buscar.possuiAreaGourmet() == null || i.getCaracteristicaImovel().getPossuiAreaGourmet().equalsIgnoreCase(buscar.possuiAreaGourmet()))
                .filter(i -> buscar.tipoImovel() == null || i.getCaracteristicaImovel().getTipoImovel().equalsIgnoreCase(buscar.tipoImovel()))
                .filter(i -> buscar.finalidade() == null || i.getNegociacao().getFinalidade().equalsIgnoreCase(buscar.finalidade()))
                .filter(i -> buscar.condominio() == null || i.getNegociacao().getCondominio().equalsIgnoreCase(buscar.condominio()))
                .filter(i -> buscar.valor() == null || i.getNegociacao().getValor() <= buscar.valor())
                .toList();
    }
}
