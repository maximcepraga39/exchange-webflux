package md.orange.exchangeapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.orange.exchangeapp.entity.RapoarteZilnice;
import md.orange.exchangeapp.repository.RapoarteZilniceRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final RapoarteZilniceRepository rapoarteZilniceRepository;
    private final ExchangeService exchangeService;

    public Flux<RapoarteZilnice> getReportForDate(LocalDate dataRaport) {
        return rapoarteZilniceRepository.findByDate(dataRaport);
    }

    @Scheduled(cron = "${reports.cron}")
    @Transactional
    // this shouldn't return anything. I did it for testing purposes
    public Flux<RapoarteZilnice> generateReport() {
        return exchangeService.getExchangesForDate(LocalDate.now())
                .groupBy(exchange -> exchange.getValutaId() + "-" + exchange.getCasaDeSchimbId())
                .flatMap(groupedFlux -> groupedFlux
                        .reduce((exchange1, exchange2) -> {
                            exchange1.setSumaPrimita(exchange1.getSumaPrimita() + exchange2.getSumaPrimita());
                            exchange1.setSumaEliberata(exchange1.getSumaEliberata() + exchange2.getSumaEliberata());
                            return exchange1;
                        }))
                .flatMap(exchange -> {
                    var rapoarteZilnice = new RapoarteZilnice();
                    rapoarteZilnice.setCasaDeSchimbId(exchange.getCasaDeSchimbId());
                    rapoarteZilnice.setValutaId(exchange.getValutaId());
                    rapoarteZilnice.setTotalTranzactii(10);
                    rapoarteZilnice.setSumaPrimita(exchange.getSumaPrimita());
                    rapoarteZilnice.setSumaEliberata(exchange.getSumaEliberata());
                    rapoarteZilnice.setDataRaport(exchange.getDataSchimb());

                    return rapoarteZilniceRepository.save(rapoarteZilnice);
                });
    }
}
