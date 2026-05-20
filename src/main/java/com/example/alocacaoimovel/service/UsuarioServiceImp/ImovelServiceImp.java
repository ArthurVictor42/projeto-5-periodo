package com.example.alocacaoimovel.service.UsuarioServiceImp;

import com.example.alocacaoimovel.dto.ImovelRequest;
import com.example.alocacaoimovel.model.Imovel;
import com.example.alocacaoimovel.repository.ImovelRepository;
import com.example.alocacaoimovel.service.ImovelService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ImovelServiceImp implements ImovelService {

    private final ImovelRepository imovelRepository;
    private static final int SCORE_MAXIMO = 115;

    public ImovelServiceImp(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    @Override
    public List<Imovel> buscarImovel(ImovelRequest buscar) {

        long inicioBusca = System.currentTimeMillis();

        List<Imovel> imoveis = imovelRepository.findAll();

        List<Imovel> recomendados = imoveis.stream()

                .filter(i ->
                        buscar.finalidade() == null ||
                                normalizar(i.getNegociacao().getFinalidade())
                                        .equals(normalizar(buscar.finalidade()))
                )

                .filter(i ->
                        buscar.cidade() == null ||
                                normalizar(i.getLocalizacao().getCidade())
                                        .equals(normalizar(buscar.cidade()))
                )

                .filter(i ->
                        buscar.tipoImovel() == null ||
                                normalizar(i.getCaracteristicaImovel().getTipoImovel())
                                        .equals(normalizar(buscar.tipoImovel()))
                )

                .filter(i ->
                        buscar.valor() == null ||
                                i.getNegociacao().getValor()
                                        <= buscar.valor()
                )

                .sorted(
                        Comparator.comparingInt(
                                (Imovel i) ->
                                        calcularPontuacao(i, buscar)
                        ).reversed()
                )

                .toList();

        long fimBusca = System.currentTimeMillis();

        imprimirMetricas(
                imoveis,
                recomendados,
                buscar,
                fimBusca - inicioBusca
        );

        return recomendados;
    }

    private int calcularPontuacao(Imovel imovel, ImovelRequest buscar) {

        int pontos = 0;

        if (buscar.cidade() != null &&
                normalizar(imovel.getLocalizacao().getCidade())
                        .equals(normalizar(buscar.cidade()))) {
            pontos += 10;
        }

        if (buscar.bairro() != null &&
                normalizar(imovel.getLocalizacao().getBairro())
                        .equals(normalizar(buscar.bairro()))) {
            pontos += 8;
        }

        if (buscar.regiao() != null &&
                normalizar(imovel.getLocalizacao().getRegiao())
                        .equals(normalizar(buscar.regiao()))) {
            pontos += 6;
        }

        if (buscar.proximidadePraia() != null &&
                normalizar(imovel.getLocalizacao().getProximidadepraia())
                        .equals(normalizar(buscar.proximidadePraia()))) {
            pontos += 5;
        }

        if (buscar.metragem() != null &&
                normalizar(imovel.getCaracteristicaImovel().getMetragem())
                        .equals(normalizar(buscar.metragem()))) {
            pontos += 5;
        }

        if (buscar.tipoImovel() != null &&
                normalizar(imovel.getCaracteristicaImovel().getTipoImovel())
                        .equals(normalizar(buscar.tipoImovel()))) {
            pontos += 10;
        }

        if (buscar.possuiPiscina() != null &&
                normalizar(imovel.getCaracteristicaImovel().getPossuiPiscina())
                        .equals(normalizar(buscar.possuiPiscina()))) {
            pontos += 5;
        }

        if (buscar.possuiAreaGourmet() != null &&
                normalizar(imovel.getCaracteristicaImovel().getPossuiAreaGourmet())
                        .equals(normalizar(buscar.possuiAreaGourmet()))) {
            pontos += 5;
        }

        if (buscar.numerosQuartos() != null &&
                imovel.getCaracteristicaImovel().getNumerosQuartos()
                        >= buscar.numerosQuartos()) {
            pontos += 7;
        }

        if (buscar.numerosBanheiros() != null &&
                imovel.getCaracteristicaImovel().getNumerosBanheiros()
                        >= buscar.numerosBanheiros()) {
            pontos += 5;
        }

        if (buscar.numerosSuite() != null &&
                imovel.getCaracteristicaImovel().getNumerosSuite()
                        >= buscar.numerosSuite()) {
            pontos += 5;
        }

        if (buscar.numerosSalas() != null &&
                imovel.getCaracteristicaImovel().getNumerosSalas()
                        >= buscar.numerosSalas()) {
            pontos += 4;
        }

        if (buscar.vagasGaragens() != null &&
                imovel.getCaracteristicaImovel().getVagasGaragens()
                        >= buscar.vagasGaragens()) {
            pontos += 5;
        }

        if (buscar.idadeImovel() != null &&
                imovel.getCaracteristicaImovel().getIdadeImovel()
                        <= buscar.idadeImovel()) {
            pontos += 4;
        }

        if (buscar.estadoConservacao() != null &&
                normalizar(imovel.getCaracteristicaImovel().getEstadoConservacao())
                        .equals(normalizar(buscar.estadoConservacao()))) {
            pontos += 6;
        }

        if (buscar.finalidade() != null &&
                normalizar(imovel.getNegociacao().getFinalidade())
                        .equals(normalizar(buscar.finalidade()))) {
            pontos += 10;
        }

        if (buscar.condominio() != null &&
                normalizar(imovel.getNegociacao().getCondominio())
                        .equals(normalizar(buscar.condominio()))) {
            pontos += 5;
        }

        if (buscar.valor() != null &&
                imovel.getNegociacao().getValor()
                        <= buscar.valor()) {
            pontos += 15;
        }

        return pontos;
    }

    private void imprimirMetricas(List<Imovel> total,  List<Imovel> recomendados, ImovelRequest buscar, long tempoBusca) {

        int totalImoveis = total.size();

        int encontrados = recomendados.size();

        double cobertura =
                totalImoveis == 0
                        ? 0
                        : (encontrados * 100.0)
                        / totalImoveis;

        double scoreMedio =
                recomendados.stream().mapToInt(i -> calcularPontuacao(i, buscar)).average().orElse(0);

        int melhorScore =
                recomendados.stream().mapToInt(i -> calcularPontuacao(i, buscar)).max().orElse(0);

        double compatibilidadeMedia =
                SCORE_MAXIMO == 0
                        ? 0
                        : (scoreMedio * 100)
                        / SCORE_MAXIMO;

        double melhorCompatibilidade =
                SCORE_MAXIMO == 0
                        ? 0
                        : (melhorScore * 100.0)
                        / SCORE_MAXIMO;

        System.out.println();

        System.out.println(
                "===== MÉTRICAS IA ====="
        );
        System.out.printf("Imóveis analisados: %d%n", totalImoveis);
        System.out.printf("Recomendados: %d%n", encontrados);
        System.out.printf("Cobertura: %.2f%%%n", cobertura);
        System.out.printf("Compatibilidade média: %.2f%%%n", compatibilidadeMedia);
        System.out.printf("Melhor compatibilidade: %.2f%%%n", melhorCompatibilidade);
        System.out.printf("Score médio: %.2f%n", scoreMedio);
        System.out.printf(
                "Melhor score: %d%n",
                melhorScore
        );
        System.out.printf(
                "Tempo busca: %d ms%n",
                tempoBusca
        );
        System.out.println(
                "======================="
        );
    }

    private String normalizar(String texto) {

        if (texto == null) {
            return "";
        }

        return java.text.Normalizer
                .normalize(
                        texto,
                        java.text.Normalizer.Form.NFD
                )
                .replaceAll(
                        "[^\\p{ASCII}]",
                        ""
                )
                .toUpperCase()
                .trim();
    }
}
